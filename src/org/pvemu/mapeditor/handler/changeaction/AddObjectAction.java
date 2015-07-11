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
import org.pvemu.mapeditor.tile.Tile;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class AddObjectAction extends CellAction{
    final static public int TILE_ID_PARAM     = 2;
    final static public int FLIP_PARAM        = 3;
    final static public int INTERACTIVE_PARAM = 4;

    @Override
    public int id() {
        return ChangeActions.ADD_OBJECT_ACTION;
    }

    @Override
    public void perform(Change change) {
        LayerSet layer = LayerSet.values()[change.getParam(LAYER_ID_PARAM)];
        Cell cell = change.getEditor().getMap().getCell(change.getParam(CELL_ID_PARAM));
        Tile tile = layer.getTiles().getTileById(change.getParam(TILE_ID_PARAM));
        CellObject obj = null;
        
        if(tile != null){
            obj = new CellObject(
                    tile, 
                    change.getParam(FLIP_PARAM) == 1, 
                    change.getParam(INTERACTIVE_PARAM) == 1
            );
        }
        
        cell.setObjectAt(layer, obj);
        change.getEditor().update();
    }

    @Override
    public String label(Change change) {
        return "Ajouter " + change.getParam(TILE_ID_PARAM) +
                " à la case " + change.getParam(CELL_ID_PARAM) + 
                ", " + LayerSet.values()[change.getParam(LAYER_ID_PARAM)];
    }
    
}
