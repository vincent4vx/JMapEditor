/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.action;

import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.Tile;
import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class EditMap {
    static public void changeBackground(Tile tile){
        EditorHandler handler = EditorHandler.getCurrentHandler();
        
        if(handler == null)
            return;
        
        handler.getMap().setBackground(tile);
        handler.update();
    }
    
    static public void rotateObject(CellObject obj){
        
    }
}
