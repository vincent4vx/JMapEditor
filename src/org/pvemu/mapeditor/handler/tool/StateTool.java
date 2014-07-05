package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class StateTool implements Tool{
    public enum CellState{
        WALKABLE{
            @Override
            Change getChange(EditorHandler handler, Cell cell) {
                return ChangeActionFactory.walkable(handler, cell, true);
            }
        },
        UNWALKABLE{
            @Override
            Change getChange(EditorHandler handler, Cell cell) {
                return ChangeActionFactory.walkable(handler, cell, false);
            }
        },
        SIGHT_BLOCK{
            @Override
            Change getChange(EditorHandler handler, Cell cell) {
                return ChangeActionFactory.lineOfSight(handler, cell, false);
            }
        },
        SIGHT_FREE{
            @Override
            Change getChange(EditorHandler handler, Cell cell) {
                return ChangeActionFactory.lineOfSight(handler, cell, true);
            }
        }
        ;
        abstract Change getChange(EditorHandler handler, Cell cell);
    }

    @Override
    public void onClick(EditorHandler handler, Cell cell) {
        handler.getChangeHandler().addChange(JMapEditor.getToolsHandler().getCurrentCellState().getChange(handler, cell));
    }

    @Override
    public LayerMask getLayerMask() {
        LayerMask mask = JMapEditor.getMaskHandler().getNeutralMask();
        float alpha = JMapEditor.getParameters().getFloatDefault("STATE_TOOL_OBJECT_ALPHA_RATE", .5f);
        
        for(Layer layer : Layer.values()){
            if(layer.isEditable())
                mask.setAlpha(layer, alpha);
        }
        
        return mask;
    }
    
}
