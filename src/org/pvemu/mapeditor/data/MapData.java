/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.util.Iterator;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.common.Utils;
import org.pvemu.mapeditor.data.db.model.MapHistory;
import org.pvemu.mapeditor.data.db.model.MapInfo;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapData implements Iterable<Cell>{
    final private MapInfo info;
    private Tile background;
    final private Cell[] cells;

    private MapData(MapInfo info, Tile background, Cell[] cells) {
        this.info = info;
        this.background = background;
        this.cells = cells;
    }
    

    public MapInfo getInfo() {
        return info;
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
    
    public Cell getCell(int id){
        return cells[id];
    }
    
    public void setCell(Cell cell){
        cells[cell.getId()] = cell;
    }
    
    static public MapData generateMap(MapInfo info){
        Cell[] cells = new Cell[Utils.getCellNumberBySize(info.getWidth(), info.getHeight())];
        
        for(int i = 0; i < cells.length; ++i){
            cells[i] = new Cell(i);
        }
        
        return new MapData(info, null, cells);
    }
    
    static public MapData loadMap(MapInfo info, MapHistory history){
        Cell[] cells = Compressor.decompressMapData(history.getCells());
        Tile background = history.getBackground() == 0 ? null : JMapEditor.getTilesHandler().getBackgrounds().getTileById(history.getBackground());
        return new MapData(info, background, cells);
    }
}
