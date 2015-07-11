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
package org.pvemu.mapeditor.base.module;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ModulesManager {
    final private List<Module> modules = new ArrayList<>();
    final private Map<Class, Module> modulesByClass = new HashMap<>();
    
    public<T extends Module> T loadModule(Class<T> moduleClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        T module = getModule(moduleClass);
        
        if(module != null)
            return module;
        
        Constructor[] constructors = moduleClass.getConstructors();
        
        if(constructors.length > 1)
            throw new IllegalArgumentException("Invalid number of constructors in " + moduleClass.getName());
        
        Constructor constructor = constructors[0];
        
        Class[] paramsTypes = constructor.getParameterTypes();
        Object[] params = new Object[paramsTypes.length];
        
        for(int i = 0; i < params.length; ++i){
            if(!paramsTypes[i].isAssignableFrom(Module.class))
                throw new IllegalArgumentException("The constructor of a module should only have parameters of type Module");
            
            params[i] = loadModule(paramsTypes[i]);
        }
        
        module = (T)constructor.newInstance(params);
        modules.add(module);
        modulesByClass.put(moduleClass, module);
        return module;
    }
    
    public<T extends Module> T getModule(Class<T> moduleClass){
        if(modulesByClass.containsKey(moduleClass))
            return (T)modulesByClass.get(moduleClass);
        
        for(Module module : modules){
            if(!moduleClass.isInstance(module))
                continue;
            
            modulesByClass.put(moduleClass, module);
            return (T)module;
        }
        
        return null;
    }
    
    public boolean isLoaded(Class<? extends Module> moduleClass){
        return getModule(moduleClass) != null;
    }
    
    public void startModules(){
        for(Module module : modules){
            module.start();
        }
    }
}
