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
package org.pvemu.mapeditor;

import java.lang.reflect.InvocationTargetException;
import org.pvemu.mapeditor.splashscreen.LoadingListener;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.base.module.Module;
import org.pvemu.mapeditor.module.newmap.NewMapModule;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ModulesLoader implements Loadable{
    private LoadingListener listener;
    
    final private List<Class<? extends Module>> modules = new ArrayList<>();

    public ModulesLoader() {
        addCoreModules();
    }
    
    private void addCoreModules(){
        addModule(NewMapModule.class);
        //addModule(new SavingModule());
        //addModule(new ChangeBackgroundModule());
    }

    @Override
    public void setLoadingListener(LoadingListener listener) {
        this.listener = listener;
    }

    @Override
    public void load() {
        int i = 0;
        for(Class<? extends Module> module : modules){
            loadModule(module);
            listener.loaded(module.getSimpleName(), (++i / modules.size()) / 2);
        }
        
        listener.loaded("Starting modules", .5f);
        JMapEditor.getModulesManager().startModules();
        listener.loaded("Modules started", 1);
    }
    
    private void loadModule(Class<? extends Module> module){
        try{
            JMapEditor.getModulesManager().loadModule(module);
        }catch(Exception e){
            throw new Error(e);
        }
    }
    
    public void addModule(Class<? extends Module> module){
        modules.add(module);
    }
}
