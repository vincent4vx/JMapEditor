/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.ToolsHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class AddTool implements Tool{
    final private ToolsHandler handler;

    public AddTool(ToolsHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onClick(Cell cell) {
        if(handler.getCurrentObject() == null)
            return;
        
        cell.setLayer1(handler.getCurrentObject());
        EditorHandler.getCurrentHandler().update();
    }
    
}
