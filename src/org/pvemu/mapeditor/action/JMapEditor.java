package org.pvemu.mapeditor.action;

import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.handler.ErrorHandler;
import org.pvemu.mapeditor.handler.ExportHandler;
import org.pvemu.mapeditor.handler.LayerMaskHandler;
import org.pvemu.mapeditor.handler.setting.ParametersHandler;
import org.pvemu.mapeditor.handler.TilesHandler;
import org.pvemu.mapeditor.handler.ToolsHandler;
import org.pvemu.mapeditor.handler.layer.LayerHandler;
import org.pvemu.mapeditor.handler.setting.RegistryHandler;
import org.pvemu.mapeditor.ui.MainWindow;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMapEditor {
    private static MainWindow ui;
    private static TilesHandler tilesHandler;
    final private static ErrorHandler errorHandler = new ErrorHandler();
    private static ToolsHandler toolsHandler;
    private static LayerHandler layerHandler;
    private static LayerMaskHandler maskHandler;
    private static ExportHandler exportHandler;
    private static ParametersHandler parameters;
    private static RegistryHandler registry;
    
    public static void main(String[] args){
        try{
            SQLiteConnection db = new SQLiteConnection(Constants.PARAMETERS_DB);
            parameters = new ParametersHandler(db);
            registry = new RegistryHandler(db);
            exportHandler = new ExportHandler();
            tilesHandler = new TilesHandler();
            maskHandler = new LayerMaskHandler();
            
            toolsHandler = new ToolsHandler();
            maskHandler.registerLayerMaskable(toolsHandler);
            
            layerHandler = new LayerHandler();
            maskHandler.registerLayerMaskable(layerHandler);
            
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

    public static ParametersHandler getParameters() {
        return parameters;
    }

    public static ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public static RegistryHandler getRegistry() {
        return registry;
    }

    public static LayerHandler getLayerHandler() {
        return layerHandler;
    }

    public static LayerMaskHandler getMaskHandler() {
        return maskHandler;
    }

}
