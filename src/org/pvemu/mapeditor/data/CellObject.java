/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class CellObject {
    public interface RotationListener{
        public void onRotation(CellObject obj);
    }
    
    final private Tile tile;
    private boolean flip = false;
    private boolean interactive = false;
    final private List<RotationListener> listeners = new ArrayList<>();
    
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
        
        for(RotationListener listener : listeners)
            listener.onRotation(this);
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
    
    public void addRotationListener(RotationListener listener){
        listeners.add(listener);
    }
    
    public void clearRotationListeners(){
        listeners.clear();
    }
    
    public void removeRotationListener(RotationListener listener){
        listeners.remove(listener);
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }
    
    static public CellObject getCellObject(int tileNumber, boolean flip, boolean interactive, TilesRegistry registry){
        if(tileNumber == 0)
            return null;
        
        Tile tile = registry.getTileById(tileNumber);
        
        if(tile == null)
            return null;
        
        CellObject obj = new CellObject(tile);
        obj.setInteractive(interactive);
        obj.setFlip(flip);
        
        return obj;
    }
}
