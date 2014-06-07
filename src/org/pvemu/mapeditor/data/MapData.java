/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.util.Iterator;
import org.pvemu.mapeditor.common.Utils;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapData implements Iterable<Cell>{
    final private int width;
    final private int height;
    final private Cell[] cells;
    private Tile background = null;

    public MapData(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[Utils.getCellNumberBySize(width, height)];
        
        for(int i = 0; i < cells.length; ++i){
            cells[i] = new Cell();
        }
    }

    public MapData(int width, int height, Cell[] cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<Cell>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < cells.length;
            }

            @Override
            public Cell next() {
                return cells[index++];
            }
        };
    }
    
    public int size(){
        return cells.length;
    }

    public Tile getBackground() {
        return background;
    }

    public void setBackground(Tile background) {
        this.background = background;
    }
}
