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

import org.pvemu.mapeditor.splashscreen.SplashScreen;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.ErrorHandler;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.common.utils.Pair;
import org.pvemu.mapeditor.common.utils.Utils;
import org.pvemu.mapeditor.param.Parameters;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMELoader {
    private SplashScreen splashScreen;
    
    private boolean loaded = false;
    
    final private List<Pair<Integer, Loadable>> loaders = new ArrayList<>();
    private int totalSize = 0;

    public JMELoader() {
        addLoader(2, new BaseLoader());
        addLoader(90, new TilesLoader());
        addLoader(8, new ModulesLoader());
    }
    
    public void load(){
        loadSettings();
        
        try{
            splashScreen = new SplashScreen();
        }catch(Exception e){
            throw new Error(e);
        }
        
        splashScreen.setVisible(true);
        
        int min = 0;
        for(Pair<Integer, Loadable> loader : loaders){
            int max = min + loader.getFirst();
            
            int minP = min * 100 / totalSize;
            int maxP = max * 100 / totalSize;
            
            loader.getSecond().setLoadingListener((s, v) -> {
                splashScreen.setInfoText(s);
                splashScreen.setValue((int)Utils.getAbsoluteValue(minP, maxP, v));
            });
            
            min = max;
            
            loader.getSecond().load();
        }
        
        splashScreen.dispose();
        loaded = true;
        JMapEditor.getUI().setVisible(true);
    }
    
    private void loadSettings(){
        try{
            JMapEditor.setErrorHandler(new ErrorHandler());
            SQLiteConnexion connection = new SQLiteConnexion(Constants.PARAMETERS_DB);
            JMapEditor.setParameters(new Parameters(connection));
        }catch(Exception e){
            throw new Error(e);
        }
    }
    
    final public void addLoader(int size, Loadable loader){
        loaders.add(new Pair<>(size, loader));
        totalSize += size;
    }
}
