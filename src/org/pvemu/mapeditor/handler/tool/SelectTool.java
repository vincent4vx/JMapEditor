/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.layer.Layer;
import org.pvemu.mapeditor.ui.rightmenu.ObjectTab;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SelectTool implements Tool{

    @Override
    public void onClick(EditorHandler handler, Cell cell) {
        Layer layer = Layer.getSelected();
        CellObject obj = cell.getObjectAt(layer);
        
        if(obj == null){
            JMapEditor.getUI().getRightMenu().removeObjectTab();
            handler.setSelectedCell(null);
            JMapEditor.getToolsHandler().setCurrentObject(null);
            return;
        }
        
        handler.setSelectedCell(cell);
        JMapEditor.getUI().getRightMenu().setObjectTab(new ObjectTab(cell));
        JMapEditor.getToolsHandler().setCurrentObject(obj);
        handler.getUI().getGrid().repaint();
    }
    
}
