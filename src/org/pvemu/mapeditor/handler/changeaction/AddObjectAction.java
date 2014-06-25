/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.data.Tile;
import org.pvemu.mapeditor.handler.layer.Layer;

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
        Layer layer = Layer.values()[change.getParam(LAYER_ID_PARAM)];
        Cell cell = change.getHandler().getMap().getCell(change.getParam(CELL_ID_PARAM));
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
        change.getHandler().update();
    }

    @Override
    public String label(Change change) {
        return "Ajouter " + change.getParam(TILE_ID_PARAM) +
                " à la case " + change.getParam(CELL_ID_PARAM) + 
                ", " + Layer.values()[change.getParam(LAYER_ID_PARAM)];
    }
    
}
