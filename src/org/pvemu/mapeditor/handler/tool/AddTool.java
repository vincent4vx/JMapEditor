package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class AddTool extends CellObjectTool{

    @Override
    public void onClick(EditorHandler handler, Cell cell) {
        CellObject obj = JMapEditor.getToolsHandler().getCurrentObject();
        if(obj == null)
            return;
        
        Change change = ChangeActionFactory.addObject(handler, cell, JMapEditor.getLayerHandler().getSelected(), obj);
        handler.getChangeHandler().addChange(change);
    }

    @Override
    public void onSelect() {
        JMapEditor.getUI().getRightMenu().getEditTab().getAdd().setSelected(true);
    }
    
}
