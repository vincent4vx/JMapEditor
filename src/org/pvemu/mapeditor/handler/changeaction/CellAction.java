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

import org.pvemu.mapeditor.base.editor.change.ChangeAction;
import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.base.editor.change.data.Change;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract public class CellAction implements ChangeAction{
    final static public int LAYER_ID_PARAM = 0;
    final static public int CELL_ID_PARAM  = 1;

    @Override
    public void undo(Change change){
        Cell cell = Compressor.decompressCell(change.getParam(CELL_ID_PARAM), change.getLastState());
        change.getEditor().getMap().setCell(cell);
        change.getEditor().update();
    }
    
}
