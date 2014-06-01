/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.rightmenu;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.action.EditMap;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Tile;
import org.pvemu.mapeditor.ui.TileRenderer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class BackgroundTab extends JScrollPane{

    public BackgroundTab() {        
        displayBackgrounds();
    }
    
    private void displayBackgrounds(){
        JList<Tile> list = new JList(JMapEditor.getTilesHandler().getBackgrounds());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TileRenderer());
        
        list.addListSelectionListener((e) -> {
            Tile tile = list.getSelectedValue();
            EditMap.changeBackground(tile);
        });
        
        setViewportView(list);
    }
    
    
}
