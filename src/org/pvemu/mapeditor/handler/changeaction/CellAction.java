/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.Change;

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
        change.getHandler().getMap().setCell(cell);
        change.getHandler().update();
    }
    
}
