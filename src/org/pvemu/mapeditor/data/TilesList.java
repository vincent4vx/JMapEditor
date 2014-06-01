/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.pvemu.mapeditor.common.LoadingListener;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesList implements ListModel<Tile>{
    final private List<Tile> tiles = new ArrayList<>();
    final private File directory;
    private LoadingListener loadingListener = null;

    public TilesList(File directory) throws IOException {
        this.directory = directory;
    }
    
    final public void refresh() throws IOException{
        tiles.clear();
        
        File[] files = directory.listFiles((dir, name) -> name.matches(".*\\.(png|bmp|jpg)"));
        
        for(File file : files){
            String strId = file.getName().split("\\.")[0];
            int id;
            
            try{
                id = Integer.parseInt(strId);
            }catch(NumberFormatException e){
                continue;
            }
            
            tiles.add(new Tile(id, ImageIO.read(file)));
            
            if(loadingListener != null)
                loadingListener.loaded(file.getName(), 1f / (float)files.length);
        }
    }

    public List<Tile> getTiles() {
        return tiles;
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
}
