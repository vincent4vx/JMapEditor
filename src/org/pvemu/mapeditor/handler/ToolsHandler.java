package org.pvemu.mapeditor.handler;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.LayerMaskable;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.handler.tool.StateTool;
import org.pvemu.mapeditor.handler.tool.Tool;
import org.pvemu.mapeditor.handler.tool.Tools;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ToolsHandler implements LayerMaskable{
    private CellObject currentObject = null;
    
    private Tool tool = Tools.SELECT;

    public CellObject getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(CellObject currentObject) {
        this.currentObject = currentObject;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
        tool.onSelect();
        JMapEditor.getUI().repaintAllEditors();
    }
    
    public StateTool.CellState getCurrentCellState(){
        return (StateTool.CellState) JMapEditor.getUI().getRightMenu().getEditTab().getStateSelector().getSelectedItem();
    }

    @Override
    public LayerMask getLayerMask() {
        return tool.getLayerMask();
    }

    @Override
    public boolean isPriority() {
        return tool.isPriority();
    }
}
