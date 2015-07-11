/* 
 * Copyright (C) 2014 Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pvemu.mapeditor.base.editor.tool;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import org.pvemu.mapeditor.hierarchy.CellObject;
import org.pvemu.mapeditor.handler.tool.StateTool;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ToolsHandler{
    final private Map<String, Tool> tools = new HashMap<>();
    
    private CellObject currentObject = null;
    
    private Tool currentTool = null;
    final private ButtonGroup buttonGroup = new ButtonGroup();

    public CellObject getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(CellObject currentObject) {
        this.currentObject = currentObject;
    }

    public Tool getTool() {
        return currentTool;
    }

    public void setTool(Tool tool) {
        this.currentTool = tool;
        tool.onSelect();
        //JMapEditor.getUI().repaintAllEditors();
    }
    
    public void registerTool(Tool tool){
        tools.put(tool.name(), tool);
        buttonGroup.add(tool.getButton());
    }
    
    public Tool getTool(String name){
        return tools.get(name);
    }
    
    public StateTool.CellState getCurrentCellState(){
        return null;//(StateTool.CellState) JMapEditor.getUI().getRightToolBar().getEditTab().getStateSelector().getSelectedItem();
    }
}
