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
import org.pvemu.mapeditor.hierarchy.CellObject;
import org.pvemu.mapeditor.base.editor.change.data.Change;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class FlipAction extends CellAction{

    @Override
    public int id() {
        return ChangeActions.FLIP_ACTION;
    }

    @Override
    public void perform(Change change) {
        Cell cell = change.getEditor().getMap().getCell(change.getParam(CELL_ID_PARAM));
        LayerSet layer = LayerSet.values()[change.getParam(LAYER_ID_PARAM)];
        CellObject obj = cell.getObjectAt(layer);
        
        if(obj == null)
            return;
        
        obj.flip();
        change.getEditor().update();
    }

    @Override
    public void undo(Change change) {
        Cell cell = change.getEditor().getMap().getCell(change.getParam(CELL_ID_PARAM));
        LayerSet layer = LayerSet.values()[change.getParam(LAYER_ID_PARAM)];
        CellObject obj = cell.getObjectAt(layer);
        
        if(obj == null)
            return;
        
        boolean flip = change.getLastState().equals("1");
        obj.setFlip(flip);
        change.getEditor().update();
    }

    @Override
    public String label(Change change) {
        return "Flip de " + change.getParam(CELL_ID_PARAM) + ", " + change.getParam(LAYER_ID_PARAM);
    }
    
}
