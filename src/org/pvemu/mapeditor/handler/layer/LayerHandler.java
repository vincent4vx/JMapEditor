package org.pvemu.mapeditor.handler.layer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.LayerMaskable;
import org.pvemu.mapeditor.data.LayerMask;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayerHandler implements LayerMaskable{
    final private LayerMask mask = JMapEditor.getMaskHandler().getNeutralMask();
    private Layer selected = Layer.GROUND;
    
    private enum LayerManagerColumn{
        LAYER_NAME("Nom du calque", String.class, false){
            @Override
            void edit(LayerMask mask, Layer layer, Object value) {}

            @Override
            Object value(LayerMask mask, Layer layer) {
                return layer.getName();
            }
        },
        VISIBLE("visible", Boolean.class, true){

            @Override
            void edit(LayerMask mask, Layer layer, Object value) {
                mask.setVisible(layer, (boolean)value);
            }

            @Override
            Object value(LayerMask mask, Layer layer) {
                return mask.isVisible(layer);
            }
            
        },
        ALPHA("alpha", Float.class, true){

            @Override
            void edit(LayerMask mask, Layer layer, Object value) {
                mask.setAlpha(layer, (float)value);
            }

            @Override
            Object value(LayerMask mask, Layer layer) {
                return mask.getAlpha(layer);
            }
            
        },
        ;
        final private String name;
        final private Class clazz;
        final private boolean editable;

        private LayerManagerColumn(String name, Class clazz, boolean editable) {
            this.name = name;
            this.clazz = clazz;
            this.editable = editable;
        }
        
        abstract void edit(LayerMask mask, Layer layer, Object value);
        
        abstract Object value(LayerMask mask, Layer layer);
    }

    @Override
    public LayerMask getLayerMask() {
        return mask;
    }

    public Layer getSelected() {
        return selected;
    }
    
    public TableModel getTableModel(){
        return new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return Layer.values().length;
            }

            @Override
            public int getColumnCount() {
                return LayerManagerColumn.values().length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return LayerManagerColumn.values()[columnIndex].value(mask, Layer.values()[rowIndex]);
            }

            @Override
            public String getColumnName(int column) {
                return LayerManagerColumn.values()[column].name;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return LayerManagerColumn.values()[columnIndex].clazz;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                LayerManagerColumn col = LayerManagerColumn.values()[columnIndex];

                return col.editable;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                LayerManagerColumn col = LayerManagerColumn.values()[columnIndex];
                col.edit(mask, Layer.values()[rowIndex], aValue);
                JMapEditor.getUI().repaintAllEditors();
            }
        };
    }
    
    public ItemListener getItemListener(){
        return (e) -> {
            if(e.getStateChange() == ItemEvent.DESELECTED)
                return;
            
            selected = (Layer) e.getItem();
            JMapEditor.getUI().getTileSelector().setTilesListContainer(selected.getTiles());
            JMapEditor.getToolsHandler().setCurrentObject(null);
            JMapEditor.getUI().repaintAllEditors();
        };
    }
}
