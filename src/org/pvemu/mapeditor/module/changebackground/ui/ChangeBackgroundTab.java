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
package org.pvemu.mapeditor.module.changebackground.ui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.tile.Tile;
import org.pvemu.mapeditor.module.changebackground.ChangeBackgroundModule;
import org.pvemu.mapeditor.ui.TileRenderer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeBackgroundTab extends JPanel{

    public ChangeBackgroundTab(ChangeBackgroundModule module) {
        super(new BorderLayout());
        JList<Tile> list = new JList(JMapEditor.APP.getTilesHandler().getBackgrounds());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TileRenderer());
        
        list.addListSelectionListener((e) -> {
            Tile tile = list.getSelectedValue();
            module.changeBackground(tile);
        });
        
        JButton clear = new JButton("Effacer");
        clear.addActionListener((e) -> {
            module.removeBackground();
            list.setSelectedIndex(-1);
        });
        
        add(clear, BorderLayout.NORTH);
        
        add(new JScrollPane(list), BorderLayout.CENTER);
    }
    
    
}
