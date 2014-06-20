/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.action;

import org.pvemu.mapeditor.handler.ExportHandler;
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
    final private static ToolsHandler toolsHandler = new ToolsHandler();
    private static ExportHandler exportHandler;
    
    public static void main(String[] args){
        try{
            exportHandler = new ExportHandler();
            tilesHandler = new TilesHandler();
            ui = new MainWindow();
            ui.setVisible(true);
        }catch(Exception ex){
            ex.printStackTrace(System.err);
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
}
