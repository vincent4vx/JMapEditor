/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class AddTool implements Tool{

    @Override
    public void onClick(EditorHandler handler, Cell cell) {
        CellObject obj = JMapEditor.getToolsHandler().getCurrentObject();
        if(obj == null)
            return;
        
        Change change = ChangeActionFactory.addObject(handler, cell, Layer.getSelected(), obj);
        handler.getChangeHandler().addChange(change);
    }
    
}
