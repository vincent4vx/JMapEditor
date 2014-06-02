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
    final private boolean flip;
    final private boolean interactive;

    public CellObject(Tile tile, boolean flip, boolean interactive) {
        this.tile = tile;
        this.flip = flip;
        this.interactive = interactive;
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
    
    public CellObject rotate(){
        return new CellObject(tile, !flip, interactive);
    }
}
