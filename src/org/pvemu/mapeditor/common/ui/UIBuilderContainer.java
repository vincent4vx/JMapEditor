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
package org.pvemu.mapeditor.common.ui;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract public class UIBuilderContainer<U extends Container, C extends UIBuilderComponent> implements UIBuilderComponent<U>{
    final private Map<String, C> componentsByName = new HashMap<>();
    final private List<C> components = new ArrayList<>();
    
    public void add(C component, Object contraints){
        componentsByName.put(component.getName(), component);
        components.add(component);
    }
    
    protected void addToContainerUI(U container, C component){
        container.add(component.getUI(), component.getConstraints());
    }
    
    public void add(C component){
        add(component, null);
    }
    
    public void remove(String name){
        C component = componentsByName.remove(name);
        components.remove(component);
    }
    
    public C get(String name){
        return componentsByName.get(name);
    }
    
    public C get(Enum e){
        return componentsByName.get(e.name());
    }
    
    abstract protected U buildContainerUI();

    @Override
    final public U getUI() {
        U containerUI = buildContainerUI();
        
        for(C component : components){
            addToContainerUI(containerUI, component);
        }
        
        return containerUI;
    }
}
