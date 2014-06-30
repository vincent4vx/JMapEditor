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
import org.pvemu.mapeditor.handler.changeaction.ChangeAction;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;

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
    
}
