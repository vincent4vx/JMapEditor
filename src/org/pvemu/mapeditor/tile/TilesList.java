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
package org.pvemu.mapeditor.tile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.pvemu.mapeditor.splashscreen.LoadingListener;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesList implements ListModel<Tile>, TilesRegistry{
    final private List<Tile> tiles = new ArrayList<>();
    final private Map<Integer, Tile> tilesById = new HashMap<>();
    final private File directory;
    private LoadingListener loadingListener = null;

    public TilesList(File directory) throws IOException {
        this.directory = directory;
    }
    
    final public void refresh() throws IOException{
        tiles.clear();
        tilesById.clear();
        
        File[] files = directory.listFiles((dir, name) -> name.matches(".*\\.(png|bmp|jpg)"));
        
        for(File file : files){
            String strId = file.getName().split("\\.")[0];
            int id;
            
            try{
                id = Integer.parseInt(strId);
            }catch(NumberFormatException e){
                continue;
            }
            
            Tile tile = new Tile(id, ImageIO.read(file));
            
            tiles.add(tile);
            tilesById.put(id, tile);
            
            if(loadingListener != null)
                loadingListener.loaded(file.getName(), 1f / (float)files.length);
        }
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Map<Integer, Tile> getTilesById() {
        return tilesById;
    }

    @Override
    public int getSize() {
        return tiles.size();
    }

    @Override
    public Tile getElementAt(int index) {
        return tiles.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LoadingListener getLoadingListener() {
        return loadingListener;
    }

    public void setLoadingListener(LoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    @Override
    public Tile getTileById(int id) {
        return tilesById.get(id);
    }
}
