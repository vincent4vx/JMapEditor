package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.Change;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeWalkableAction extends CellAction{
    final static public int WALKABLE_PARAM = 0;

    @Override
    public int id() {
        return ChangeActions.CHANGE_WALKABLE_ACTION;
    }

    @Override
    public void perform(Change change) {
        Cell cell = change.getHandler().getMap().getCell(change.getParam(CELL_ID_PARAM));
        cell.setMovement(change.getParam(WALKABLE_PARAM) * 4);
        change.getHandler().update();
    }

    @Override
    public String label(Change change) {
        return "Case " + change.getParam(CELL_ID_PARAM) + (change.getParam(WALKABLE_PARAM) != 0 ? " " : " non ") + "marchable";
    }
    
}
