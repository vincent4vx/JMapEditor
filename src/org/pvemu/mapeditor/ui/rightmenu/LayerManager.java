package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.handler.layer.Layer;

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
        JComboBox<Layer> selector = new JComboBox<>();
        
        for(Layer layer : Layer.values()){
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
