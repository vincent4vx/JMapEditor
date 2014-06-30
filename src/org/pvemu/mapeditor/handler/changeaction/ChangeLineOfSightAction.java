/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.Change;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeLineOfSightAction extends CellAction{
    final static public int LINE_OF_SIGHT_PARAM = 0;

    @Override
    public int id() {
        return ChangeActions.CHANGE_LINE_OF_SIGHT_ACTION;
    }

    @Override
    public void perform(Change change) {
        Cell cell = change.getHandler().getMap().getCell(change.getParam(CELL_ID_PARAM));
        cell.setLineOfSight(change.getParam(LINE_OF_SIGHT_PARAM) == 1);
        change.getHandler().update();
    }

    @Override
    public String label(Change change) {
        return "Case " + change.getParam(CELL_ID_PARAM) + " ligne de vue " + (change.getParam(LINE_OF_SIGHT_PARAM) == 1 ? " libre" : " bloquante");
    }
    
}
