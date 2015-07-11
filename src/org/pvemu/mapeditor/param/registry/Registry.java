/* 
 * Copyright (C) 2014 Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pvemu.mapeditor.param.registry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.param.registry.data.RegistryDAO;
import org.pvemu.mapeditor.param.registry.data.RegistryModel;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Registry extends AbstractParametersHandler<RegistryModel>{
    final private RegistryModel root;
    final private RegistryDAO dao;
    
    public Registry(SQLiteConnexion connection) throws SQLException {
        dao = new RegistryDAO(connection, Constants.REGISTRY_TABLE);
        root = dao.getRoot();
    }

    private Registry(RegistryModel root, RegistryDAO dao) {
        this.root = root;
        this.dao = dao;
    }

    @Override
    RegistryModel getParameter(String name) {
        RegistryModel registry = root;
        
        for(String key : name.split(":")){
            if(key.isEmpty())
                continue;
            
            RegistryModel child = registry.get(key);
            
            if(child == null)
                return null;
            
            registry = child;
        }
        
        return registry;
    }

    @Override
    RegistryModel createParameter(String name, ParameterType type, Object value) {
        RegistryModel registry = root;
        String[] keys = name.split(":");
        
        for(int i = 0; i < keys.length - 1; ++i){
            if(keys[i].isEmpty())
                continue;
            
            RegistryModel child = registry.get(keys[i]);
            
            if(child == null){
                child = createChild(keys[i], registry, ParameterType.NULL, null);
            }
            
            registry = child;
        }
        
        return createChild(keys[keys.length - 1], registry, type, value);
    }
    
    private RegistryModel createChild(String name, RegistryModel parent, ParameterType type, Object value){
        if(parent.get(name) != null)
            throw new RuntimeException("Child already exists !");
        
        RegistryModel child;
        try{
            child = dao.create(name, parent, type, value);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        parent.add(child);
        
        return child;
    }

    @Override
    void updateParameter(RegistryModel param, ParameterType type, Object value) {
        value = type.getValue(value);
        param.setValue(value);
        param.setType(type);
        
        try{
            dao.update(param);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    void removeParameter(RegistryModel param) {
        if(param.getParent() == null)
            throw new RuntimeException("Cannot remove root node !");
        
        removeChildren(param);
        param.getParent().remove(param.getName());
    }
    
    private void removeChildren(RegistryModel registry){
        for(RegistryModel child : registry.getChildren()){
            removeChildren(child);
        }
        
        registry.clear();
        
        try{
            dao.delete(registry);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public Registry getRegistry(String name){
        RegistryModel registry = getParameter(name);
        
        if(registry == null)
            throw new RuntimeException("Cannot found registry " + name);
        
        return new Registry(registry, dao);
    }
    
    public List<Registry> getChildren(){
        List<Registry> children = new ArrayList<>();
        
        for(RegistryModel child : root.getChildren()){
            children.add(new Registry(child, dao));
        }
        
        return children;
    }
    
}
