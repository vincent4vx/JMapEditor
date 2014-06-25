/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.Change;
import static org.pvemu.mapeditor.handler.changeaction.CellAction.CELL_ID_PARAM;
import static org.pvemu.mapeditor.handler.changeaction.CellAction.LAYER_ID_PARAM;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RemoveObjectAction extends CellAction{

    @Override
    public int id() {
        return ChangeActions.REMOVE_OBJECT_ACTION;
    }

    @Override
    public void perform(Change change) {
        Layer layer = Layer.values()[change.getParam(LAYER_ID_PARAM)];
        Cell cell = change.getHandler().getMap().getCell(change.getParam(CELL_ID_PARAM));
        cell.removeObjectAt(layer);
        change.getHandler().update();
    }

    @Override
    public String label(Change change) {
        return "Supprimer à la case " + 
                change.getParam(CELL_ID_PARAM) + 
                ", " + 
                Layer.values()[change.getParam(LAYER_ID_PARAM)];
    }
    
    
}
