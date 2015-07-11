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
import org.pvemu.mapeditor.common.ErrorHandler;
import org.pvemu.mapeditor.base.module.ModulesManager;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.module.newmap.NewMapModule;
import org.pvemu.mapeditor.param.Parameters;
import org.pvemu.mapeditor.splashscreen.SplashScreen;
import org.pvemu.mapeditor.tile.TilesHandler;
import org.pvemu.mapeditor.ui.JMEUI;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMapEditor {
    private static Parameters parameters;
    private static SQLiteConnexion connexion;
    private static TilesHandler tilesHandler;
    private static JMEUI ui;
    final private static ModulesManager modulesManager = new ModulesManager();
    private static ErrorHandler errorHandler;
    
    public static void main(String[] args) {
        try{
            /*connexion = new SQLiteConnexion(Constants.PARAMETERS_DB);
            parameters = new Parameters(connexion);
            
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.setVisible(true);
            
            splashScreen.setInfoText("Loading modules");
            tilesHandler = new TilesHandler();
            ui = new JMEUI();
            loadModules();
            splashScreen.setValue(2);
            
            splashScreen.setInfoText("Starting modules");
            modulesManager.startModules();
            splashScreen.setValue(2);
            
            ui.setVisible(true);
            
            splashScreen.setVisible(false);*/
            
            new JMELoader().load();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private static void loadModules() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        modulesManager.loadModule(NewMapModule.class);
    }

    public static Parameters getParameters() {
        return parameters;
    }

    public static SQLiteConnexion getConnexion() {
        return connexion;
    }

    public static TilesHandler getTilesHandler() {
        return tilesHandler;
    }

    public static JMEUI getUI() {
        return ui;
    }

    public static ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public static ModulesManager getModulesManager() {
        return modulesManager;
    }

    static void setParameters(Parameters parameters) {
        JMapEditor.parameters = parameters;
    }

    static void setConnexion(SQLiteConnexion connexion) {
        JMapEditor.connexion = connexion;
    }

    static void setTilesHandler(TilesHandler tilesHandler) {
        JMapEditor.tilesHandler = tilesHandler;
    }

    static void setUI(JMEUI ui) {
        JMapEditor.ui = ui;
    }

    static void setErrorHandler(ErrorHandler errorHandler) {
        JMapEditor.errorHandler = errorHandler;
    }
    
}
