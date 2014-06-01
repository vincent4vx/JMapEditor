/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.tileselector;

import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.action.EditMap;
import org.pvemu.mapeditor.data.Tile;
import org.pvemu.mapeditor.data.TilesList;
import org.pvemu.mapeditor.ui.TileRenderer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TileListUI extends JScrollPane{
    private TilesList tiles = null;

    public TileListUI() {
        setPreferredSize(new Dimension(getPreferredSize().width, 120));
    }

    public void setTiles(TilesList tiles) {
        this.tiles = tiles;
        
        JList<Tile> list = new JList(tiles);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TileRenderer());
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        
        list.addListSelectionListener((e) -> {
            Tile tile = list.getSelectedValue();
            EditMap.changeBackground(tile);
        });
        
        setViewportView(list);
    }
    
}