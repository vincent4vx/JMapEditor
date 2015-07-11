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
package org.pvemu.mapeditor.param.registry.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.pvemu.mapeditor.param.registry.Parameter;
import org.pvemu.mapeditor.param.registry.ParameterType;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RegistryModel implements Parameter{
    final private int id;
    final private String name;
    final private RegistryModel parent;
    private ParameterType type;
    private Object value;
    final private Map<String, RegistryModel> children = new HashMap<>();

    public RegistryModel(int id, String name, RegistryModel parent, ParameterType type, Object value) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public RegistryModel getParent() {
        return parent;
    }

    @Override
    public ParameterType getType() {
        return type;
    }

    @Override
    public void setType(ParameterType type) {
        this.type = type;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public Collection<RegistryModel> getChildren(){
        return children.values();
    }
    
    public RegistryModel get(String name){
        return children.get(name);
    }
    
    public void add(RegistryModel registry){
        children.put(registry.name, registry);
    }
    
    public void remove(String name){
        children.remove(name);
    }
    
    public void clear(){
        children.clear();
    }
}
