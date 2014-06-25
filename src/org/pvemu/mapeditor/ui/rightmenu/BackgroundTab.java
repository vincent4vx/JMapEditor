/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.action.EditMap;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.data.Tile;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;
import org.pvemu.mapeditor.handler.changeaction.ChangeActions;
import org.pvemu.mapeditor.ui.TileRenderer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class BackgroundTab extends JPanel{

    public BackgroundTab() {        
        super(new BorderLayout());
        JList<Tile> list = new JList(JMapEditor.getTilesHandler().getBackgrounds());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TileRenderer());
        
        list.addListSelectionListener((e) -> {
            Tile tile = list.getSelectedValue();
            EditorHandler handler = EditorHandler.getCurrentHandler();
            handler.getChangeHandler().addChange(ChangeActionFactory.changeBackground(
                    handler, 
                    handler.getMap(), 
                    tile.getId()
            ));
        });
        
        JButton clear = new JButton("Effacer");
        clear.addActionListener((e) -> {
            EditorHandler handler = EditorHandler.getCurrentHandler();
            handler.getChangeHandler().addChange(ChangeActionFactory.changeBackground(
                    handler, 
                    handler.getMap(), 
                    0
            ));
            list.setSelectedIndex(-1);
        });
        
        add(clear, BorderLayout.NORTH);
        
        add(new JScrollPane(list), BorderLayout.CENTER);
    }
    
    
}
