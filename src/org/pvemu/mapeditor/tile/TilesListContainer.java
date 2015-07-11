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
import org.pvemu.mapeditor.splashscreen.LoadingListener;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesListContainer implements TilesRegistry{
    final private File directory;
    final private List<TilesListContainer> tilesListContainers = new ArrayList<>();
    private TilesList tilesList;
    final private List<Tile> tiles = new ArrayList<>();
    final private Map<Integer, Tile> tilesById = new HashMap<>();
    private LoadingListener listener;

    public TilesListContainer(File directory) throws IOException{
        this.directory = directory;
    }
    
    public void setLoadingListener(LoadingListener listener){
        this.listener = listener;
    }
    
    public void refresh() throws IOException{
        tiles.clear();
        tilesById.clear();
        tilesListContainers.clear();
        
        File[] files = directory.listFiles();
        int count = 1;
        
        for(File f : files){
            if(f.isDirectory())
                ++count;
        }
        
        final int nbList = count;
        
        for(File dir : files){
            if(!dir.isDirectory())
                continue;
            
            TilesListContainer tlc = new TilesListContainer(dir);
            
            tlc.setLoadingListener((s, v) -> {
                listener.loaded(s, v / nbList);
            });
            
            tlc.refresh();
            
            tilesListContainers.add(tlc);
            tiles.addAll(tlc.tiles);
            tilesById.putAll(tlc.tilesById);
        }
        
        tilesList = new TilesList(directory);
        
        tilesList.setLoadingListener((s, v) -> {
            listener.loaded(s, v / nbList);
        });
        
        tilesList.refresh();
        
        tiles.addAll(tilesList.getTiles());
        tilesById.putAll(tilesList.getTilesById());
    }

    public List<TilesListContainer> getTilesListContainers() {
        return tilesListContainers;
    }

    public TilesList getTilesList() {
        return tilesList;
    }

    public Tile getTile(int id){
        return tiles.get(id);
    }

    public File getDirectory() {
        return directory;
    }

    @Override
    public Tile getTileById(int id) {
        return tilesById.get(id);
    }
}
