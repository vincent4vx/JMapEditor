/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.action;

import java.awt.HeadlessException;
import java.io.IOException;
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
    
    public static void main(String[] args) throws HeadlessException, IOException {
        tilesHandler = new TilesHandler();
        ui = new MainWindow();
        ui.setVisible(true);
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
}
