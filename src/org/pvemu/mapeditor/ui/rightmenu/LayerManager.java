/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import org.pvemu.mapeditor.handler.layer.Layer;
import org.pvemu.mapeditor.handler.layer.LayerHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayerManager extends JPanel{

    public LayerManager() {
        super(new BorderLayout());
        makeBorder();
        makeTable();
    }
    
    private void makeBorder(){
        Border border = BorderFactory.createEtchedBorder();
        border = BorderFactory.createTitledBorder(border, "Calques");
        
        setBorder(border);
    }
    
    private void makeTable(){
        JComboBox<Layer> selector = new JComboBox<>();
        
        for(Layer layer : Layer.values()){
            if(layer.isEditable())
                selector.addItem(layer);
        }
        
        selector.addItemListener((e) -> {
            Layer.setSelected((Layer) e.getItem());
        });
        
        add(selector, BorderLayout.SOUTH);
        JTable table = new JTable(new LayerHandler());
        table.setDefaultEditor(Float.class, new DefaultCellEditor(new JComboBox<>(new Float[]{1f, .9f, .8f, .7f, .6f, .5f, .4f, .3f, .2f, .1f, 0f})));
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
    }
    
}
