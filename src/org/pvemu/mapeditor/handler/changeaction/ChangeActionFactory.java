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

import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.hierarchy.CellObject;
import org.pvemu.mapeditor.base.editor.change.data.Change;
import org.pvemu.mapeditor.base.editor.data.MapData;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeActionFactory {
    static public Change addObject(Editor handler, Cell cell, LayerSet layer, CellObject obj){
        System.out.println(cell.getId());
        return new Change(
                ChangeActions.ADD_OBJECT_ACTION, 
                handler, 
                new int[]{
                    layer.ordinal(),
                    cell.getId(),
                    obj.getTile().getId(),
                    obj.isFlip() ? 1 : 0,
                    obj.isInteractive() ? 1 : 0
                }, 
                Compressor.compressCell(cell)
        );
    }
    
    static public Change removeObject(Editor handler, Cell cell, LayerSet layer){
        return new Change(
                ChangeActions.REMOVE_OBJECT_ACTION, 
                handler, 
                new int[]{layer.ordinal(), cell.getId()}, 
                Compressor.compressCell(cell)
        );
    }
    
    static public Change changeBackground(Editor handler, MapData map, int bgID){
        int last = map.getBackground() == null ? 0 : map.getBackground().getId();
        return new Change(
                ChangeActions.CHANGE_BACKBROUND_ACTION, 
                handler, 
                new int[]{bgID}, 
                "" + last
        );
    }
    
    static public Change flip(Editor handler, Cell cell, LayerSet layer){
        CellObject obj = cell.getObjectAt(layer);
        boolean flip = obj == null ? false : obj.isFlip();
        return new Change(
                ChangeActions.FLIP_ACTION, 
                handler, 
                new int[]{layer.ordinal(), cell.getId()}, 
                flip ? "1" : "0"
        );
    }
    
    static public Change walkable(Editor handler, Cell cell, boolean walkable){
        return new Change(
                ChangeActions.CHANGE_WALKABLE_ACTION, 
                handler, 
                new int[]{walkable ? 1 : 0, cell.getId()}, 
                Compressor.compressCell(cell)
        );
    }
    
    static public Change lineOfSight(Editor handler, Cell cell, boolean lineOfSight){
        return new Change(
                ChangeActions.CHANGE_LINE_OF_SIGHT_ACTION, 
                handler, 
                new int[]{lineOfSight ? 1 : 0, cell.getId()}, 
                Compressor.compressCell(cell)
        );
    }
}
