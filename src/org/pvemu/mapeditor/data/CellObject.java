/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class CellObject {
    final private Tile tile;
    private boolean flip = false;
    private boolean interactive = false;
    
    public CellObject(CellObject obj){
        tile = obj.tile;
        flip = obj.flip;
        interactive = obj.interactive;
    }

    public CellObject(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public boolean isFlip() {
        return flip;
    }

    public boolean isInteractive() {
        return interactive;
    }
    
    public void flip(){
        flip = !flip;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
}
