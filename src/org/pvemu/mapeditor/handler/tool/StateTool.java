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
package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.base.editor.tool.Tool;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.base.editor.change.data.Change;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class StateTool implements Tool{
    public enum CellState{
        WALKABLE{
            @Override
            Change getChange(Editor handler, Cell cell) {
                return ChangeActionFactory.walkable(handler, cell, true);
            }
        },
        UNWALKABLE{
            @Override
            Change getChange(Editor handler, Cell cell) {
                return ChangeActionFactory.walkable(handler, cell, false);
            }
        },
        SIGHT_BLOCK{
            @Override
            Change getChange(Editor handler, Cell cell) {
                return ChangeActionFactory.lineOfSight(handler, cell, false);
            }
        },
        SIGHT_FREE{
            @Override
            Change getChange(Editor handler, Cell cell) {
                return ChangeActionFactory.lineOfSight(handler, cell, true);
            }
        }
        ;
        abstract Change getChange(Editor handler, Cell cell);
    }

    @Override
    public void onClick(Editor handler, Cell cell) {
        handler.getChangeHandler().addChange(JMapEditor.getToolsHandler().getCurrentCellState().getChange(handler, cell));
    }

    @Override
    public LayerMask getLayerMask() {
        LayerMask mask = JMapEditor.getMaskHandler().getNeutralMask();
        float alpha = JMapEditor.getParameters().getFloatDefault("STATE_TOOL_OBJECT_ALPHA_RATE", .5f);
        
        for(LayerSet layer : LayerSet.values()){
            if(layer.isEditable())
                mask.setAlpha(layer, alpha);
        }
        
        return mask;
    }
    
}
