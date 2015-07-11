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
package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayerManager extends JPanel{

    public LayerManager() {
        super(new BorderLayout());
        makeBorder();
        makeSelector();
        makeTable();
    }
    
    private void makeBorder(){
        Border border = BorderFactory.createEtchedBorder();
        border = BorderFactory.createTitledBorder(border, "Calques");
        
        setBorder(border);
    }
    
    private void makeTable(){
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(JMapEditor.getLayerHandler().getTableModel());
        table.setDefaultEditor(Float.class, new DefaultCellEditor(new JComboBox<>(new Float[]{1f, .9f, .8f, .7f, .6f, .5f, .4f, .3f, .2f, .1f, 0f})));
        
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }
    
    private void makeSelector(){
        JComboBox<LayerSet> selector = new JComboBox<>();
        
        for(LayerSet layer : LayerSet.values()){
            if(layer.isEditable())
                selector.addItem(layer);
        }
        
        selector.setSelectedItem(JMapEditor.getLayerHandler().getSelected());
        selector.addItemListener(JMapEditor.getLayerHandler().getItemListener());
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Calque courant"), BorderLayout.WEST);
        panel.add(selector, BorderLayout.CENTER);
        
        add(panel, BorderLayout.NORTH);
    }
}
