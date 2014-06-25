/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.handler.layer.Layer;

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
        Cell cell = change.getHandler().getMap().getCell(change.getParam(CELL_ID_PARAM));
        Layer layer = Layer.values()[change.getParam(LAYER_ID_PARAM)];
        CellObject obj = cell.getObjectAt(layer);
        
        if(obj == null)
            return;
        
        obj.flip();
        change.getHandler().update();
    }

    @Override
    public void undo(Change change) {
        Cell cell = change.getHandler().getMap().getCell(change.getParam(CELL_ID_PARAM));
        Layer layer = Layer.values()[change.getParam(LAYER_ID_PARAM)];
        CellObject obj = cell.getObjectAt(layer);
        
        if(obj == null)
            return;
        
        boolean flip = change.getLastState().equals("1");
        obj.setFlip(flip);
        change.getHandler().update();
    }

    @Override
    public String label(Change change) {
        return "Flip de " + change.getParam(CELL_ID_PARAM) + ", " + change.getParam(LAYER_ID_PARAM);
    }
    
}
