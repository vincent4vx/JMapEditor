/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RemoveTool extends CellObjectTool{

    @Override
    public void onClick(EditorHandler handler, Cell cell) {
        Change change = ChangeActionFactory.removeObject(handler, cell, JMapEditor.getLayerHandler().getSelected());
        handler.getChangeHandler().addChange(change);
    }
    
}
