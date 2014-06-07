/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.pvemu.mapeditor.common.LoadingListener;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesListContainer implements TilesRegistry{
    final private File directory;
    final private List<TilesListContainer> tilesListContainers = new ArrayList<>();
    final private TilesList tilesList;
    final private List<Tile> tiles = new ArrayList<>();
    final private Map<Integer, Tile> tilesById = new HashMap<>();

    public TilesListContainer(File directory, LoadingListener listener) throws IOException{
        this.directory = directory;
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
            
            TilesListContainer tlc = new TilesListContainer(dir, (s, v) -> {
                listener.loaded(s, v / nbList);
            });
            
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
