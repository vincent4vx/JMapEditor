/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.action;

import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.handler.ErrorHandler;
import org.pvemu.mapeditor.handler.ExportHandler;
import org.pvemu.mapeditor.handler.ParametersHandler;
import org.pvemu.mapeditor.handler.TilesHandler;
import org.pvemu.mapeditor.handler.ToolsHandler;
import org.pvemu.mapeditor.ui.MainWindow;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMapEditor {
    private static MainWindow ui;
    private static TilesHandler tilesHandler;
    final private static ErrorHandler errorHandler = new ErrorHandler();
    final private static ToolsHandler toolsHandler = new ToolsHandler();
    private static ExportHandler exportHandler;
    private static SQLiteConnection parametersDatabase;
    private static ParametersHandler parametersHandler;
    
    public static void main(String[] args){
        try{
            parametersDatabase = new SQLiteConnection(Constants.PARAMETERS_DB);
            parametersHandler = new ParametersHandler(parametersDatabase);
            exportHandler = new ExportHandler();
            tilesHandler = new TilesHandler();
            ui = new MainWindow();
            ui.setVisible(true);
        }catch(Exception ex){
            errorHandler.showError("Erreur fatale !", ex);
        }
    }

    public static MainWindow getUI() {
        return ui;
    }

    public static TilesHandler getTilesHandler() {
        return tilesHandler;
    }

    public static ToolsHandler getToolsHandler() {
        return toolsHandler;
    }

    public static ExportHandler getExportHandler() {
        return exportHandler;
    }

    public static SQLiteConnection getParametersDatabase() {
        return parametersDatabase;
    }

    public static ParametersHandler getParametersHandler() {
        return parametersHandler;
    }

    public static ErrorHandler getErrorHandler() {
        return errorHandler;
    }

}
