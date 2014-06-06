/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.ToolsHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RemoveTool implements Tool{
    final private ToolsHandler handler;

    public RemoveTool(ToolsHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onClick(Cell cell) {
        CellObject obj = cell.getLayer1();
        cell.setLayer1(null);
        handler.setCurrentObject(obj);
        EditorHandler.getCurrentHandler().update();
    }
    
}
