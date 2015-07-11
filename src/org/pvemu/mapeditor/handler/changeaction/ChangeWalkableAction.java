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
package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.base.editor.change.data.Change;

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
        Cell cell = change.getEditor().getMap().getCell(change.getParam(CELL_ID_PARAM));
        cell.setMovement(change.getParam(WALKABLE_PARAM) * 4);
        change.getEditor().update();
    }

    @Override
    public String label(Change change) {
        return "Case " + change.getParam(CELL_ID_PARAM) + (change.getParam(WALKABLE_PARAM) != 0 ? " " : " non ") + "marchable";
    }
    
}
