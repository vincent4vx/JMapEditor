/* 
 * Copyright (C) 2014 Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pvemu.mapeditor.base.editor.data;

import java.util.Iterator;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.common.utils.Utils;
import org.pvemu.mapeditor.tile.Tile;
import org.pvemu.mapeditor.module.saving.data.MapHistory;
import org.pvemu.mapeditor.module.saving.data.MapInfo;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapData implements Iterable<Cell>{
    final private int id;
    final private int width;
    final private int height;
    private Tile background;
    final private Cell[] cells;

    public MapData(int id, int width, int height, Tile background, Cell[] cells) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.background = background;
        this.cells = cells;
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

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    /*static public MapData loadMap(MapInfo info, MapHistory history){
        Cell[] cells = Compressor.decompressMapData(history.getCells());
        Tile background = history.getBackground() == 0 ? null : JMapEditor.getTilesHandler().getBackgrounds().getTileById(history.getBackground());
        return new MapData(info, background, cells);
    }*/
}
