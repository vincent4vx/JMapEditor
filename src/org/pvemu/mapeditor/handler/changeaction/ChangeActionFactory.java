/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeActionFactory {
    static public Change addObject(EditorHandler handler, Cell cell, Layer layer, CellObject obj){
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
    
    static public Change removeObject(EditorHandler handler, Cell cell, Layer layer){
        return new Change(
                ChangeActions.REMOVE_OBJECT_ACTION, 
                handler, 
                new int[]{layer.ordinal(), cell.getId()}, 
                Compressor.compressCell(cell)
        );
    }
    
    static public Change changeBackground(EditorHandler handler, MapData map, int bgID){
        int last = map.getBackground() == null ? 0 : map.getBackground().getId();
        return new Change(
                ChangeActions.CHANGE_BACKBROUND_ACTION, 
                handler, 
                new int[]{bgID}, 
                "" + last
        );
    }
    
    static public Change flip(EditorHandler handler, Cell cell, Layer layer){
        CellObject obj = cell.getObjectAt(layer);
        boolean flip = obj == null ? false : obj.isFlip();
        return new Change(
                ChangeActions.FLIP_ACTION, 
                handler, 
                new int[]{layer.ordinal(), cell.getId()}, 
                flip ? "1" : "0"
        );
    }
}
