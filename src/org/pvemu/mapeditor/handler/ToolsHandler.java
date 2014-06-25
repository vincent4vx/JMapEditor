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

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ToolsHandler {
    private CellObject currentObject = null;
    
    final private Tool SELECT = new SelectTool(),
                       ADD = new AddTool(),
                       REMOVE = new RemoveTool();
    
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
        toolType = REMOVE;
    }
    
    public void setAddTool(){
        toolType = ADD;
        JMapEditor.getUI().getRightMenu().getEditTab().getAdd().setSelected(true);
    }
}
