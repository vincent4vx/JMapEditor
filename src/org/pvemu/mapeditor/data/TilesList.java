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
import javax.imageio.ImageIO;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.pvemu.mapeditor.common.LoadingListener;

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
