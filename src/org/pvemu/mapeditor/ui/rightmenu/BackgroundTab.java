/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.rightmenu;

import java.io.IOException;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.action.EditMap;
import org.pvemu.mapeditor.data.Backgrounds;
import org.pvemu.mapeditor.data.Tile;
import org.pvemu.mapeditor.ui.TileRenderer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class BackgroundTab extends JScrollPane{
    private Backgrounds backgrounds;

    public BackgroundTab() {
        try {
            backgrounds = new Backgrounds();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex, "Impossible de récupérer les fonds", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        displayBackgrounds();
    }
    
    private void displayBackgrounds(){
        if(backgrounds == null)
            return;
        
        JList<Tile> list = new JList(backgrounds);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TileRenderer());
        
        list.addListSelectionListener((e) -> {
            Tile tile = list.getSelectedValue();
            EditMap.changeBackground(tile);
        });
        
        setViewportView(list);
    }
    
}
