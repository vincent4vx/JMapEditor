package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.BorderLayout;
import java.awt.event.ContainerAdapter;
import java.awt.event.ItemEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import org.pvemu.mapeditor.action.JMapEditor;
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
        JTable table = new JTable(new LayerHandler());
        table.setDefaultEditor(Float.class, new DefaultCellEditor(new JComboBox<>(new Float[]{1f, .9f, .8f, .7f, .6f, .5f, .4f, .3f, .2f, .1f, 0f})));
        
        table.addPropertyChangeListener((e) -> JMapEditor.getUI().repaintAllEditors());
        
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
        
        selector.addItemListener((e) -> {
            if(e.getStateChange() == ItemEvent.DESELECTED)
                return;
            
            Layer selected = (Layer) e.getItem();
            Layer.setSelected(selected);
            JMapEditor.getUI().getTileSelector().setTilesListContainer(selected.getTiles());
            JMapEditor.getToolsHandler().setCurrentObject(null);
            JMapEditor.getUI().repaintAllEditors();
        });
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Calque courant"), BorderLayout.WEST);
        panel.add(selector, BorderLayout.CENTER);
        
        add(panel, BorderLayout.NORTH);
    }
}
