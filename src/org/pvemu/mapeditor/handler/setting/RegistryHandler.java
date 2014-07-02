/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.setting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.dao.JMERegistryDAO;
import org.pvemu.mapeditor.data.db.model.JMERegistry;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RegistryHandler extends AbstractParametersHandler<JMERegistry>{
    final private JMERegistry root;
    final private JMERegistryDAO dao;
    
    public RegistryHandler(SQLiteConnection connection) throws SQLException {
        dao = new JMERegistryDAO(connection);
        root = dao.getRoot();
    }

    private RegistryHandler(JMERegistry root, JMERegistryDAO dao) {
        this.root = root;
        this.dao = dao;
    }

    @Override
    JMERegistry getParameter(String name) {
        JMERegistry registry = root;
        
        for(String key : name.split(":")){
            if(key.isEmpty())
                continue;
            
            JMERegistry child = registry.get(key);
            
            if(child == null)
                return null;
            
            registry = child;
        }
        
        return registry;
    }

    @Override
    JMERegistry createParameter(String name, ParameterType type, Object value) {
        JMERegistry registry = root;
        String[] keys = name.split(":");
        
        for(int i = 0; i < keys.length - 1; ++i){
            if(keys[i].isEmpty())
                continue;
            
            JMERegistry child = registry.get(keys[i]);
            
            if(child == null){
                child = createChild(keys[i], registry, ParameterType.NULL, null);
            }
            
            registry = child;
        }
        
        return createChild(keys[keys.length - 1], registry, type, value);
    }
    
    private JMERegistry createChild(String name, JMERegistry parent, ParameterType type, Object value){
        if(parent.get(name) != null)
            throw new RuntimeException("Child already exists !");
        
        JMERegistry child;
        try{
            child = dao.create(name, parent, type, value);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        parent.add(child);
        
        return child;
    }

    @Override
    void updateParameter(JMERegistry param, ParameterType type, Object value) {
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
    void removeParameter(JMERegistry param) {
        if(param.getParent() == null)
            throw new RuntimeException("Cannot remove root node !");
        
        removeChildren(param);
        param.getParent().remove(param.getName());
    }
    
    private void removeChildren(JMERegistry registry){
        for(JMERegistry child : registry.getChildren()){
            removeChildren(child);
        }
        
        registry.clear();
        
        try{
            dao.delete(registry);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public RegistryHandler getRegistry(String name){
        JMERegistry registry = getParameter(name);
        
        if(registry == null)
            throw new RuntimeException("Cannot found registry " + name);
        
        return new RegistryHandler(registry, dao);
    }
    
    public List<RegistryHandler> getChildren(){
        List<RegistryHandler> children = new ArrayList<>();
        
        for(JMERegistry child : root.getChildren()){
            children.add(new RegistryHandler(child, dao));
        }
        
        return children;
    }
    
    public ImmutableParameter getData(){
        return new ImmutableParameter(root);
    }
    
}
