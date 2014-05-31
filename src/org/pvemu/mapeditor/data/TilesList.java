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
import javax.swing.ImageIcon;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract public class TilesList implements ListModel<Tile>{
    final private List<Tile> tiles = new ArrayList<>();
    final private File directory;

    public TilesList(File directory) throws IOException {
        this.directory = directory;
        refresh();
    }
    
    abstract protected TileType tileType();
    
    final public void refresh() throws IOException{
        tiles.clear();
        
        for(File file : directory.listFiles((dir, name) -> name.matches(".*\\.(png|bmp|jpg)"))){
            String strId = file.getName().split("\\.")[0];
            int id = Integer.parseInt(strId);
            tiles.add(new Tile(id, tileType(), ImageIO.read(file)));
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
}
