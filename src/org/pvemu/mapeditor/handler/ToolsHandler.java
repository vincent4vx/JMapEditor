/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.handler.tool.AddTool;
import org.pvemu.mapeditor.handler.tool.RemoveTool;
import org.pvemu.mapeditor.handler.tool.SelectTool;
import org.pvemu.mapeditor.handler.tool.Tool;
import org.pvemu.mapeditor.ui.rightmenu.ObjectTab;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ToolsHandler {
    private CellObject currentObject = null;
    private Cell currentCell = null;
    
    final private Tool SELECT = new SelectTool(this),
                       ADD = new AddTool(this),
                       REMOVE = new RemoveTool(this);
    
    private Tool toolType = SELECT;

    public CellObject getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(CellObject currentObject) {
        this.currentObject = currentObject;
    }

    public Tool getTool() {
        return toolType;
    }

    public void setSelectTool(){
        toolType = SELECT;
    }
    
    public void setRemoveTool(){
        currentCell = null;
        toolType = REMOVE;
    }
    
    public void setAddTool(){
        currentCell = null;
        toolType = ADD;
        JMapEditor.getUI().getRightMenu().getEditTab().getAdd().setSelected(true);
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
}
