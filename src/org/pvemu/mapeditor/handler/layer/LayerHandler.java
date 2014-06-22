/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.layer;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayerHandler extends AbstractTableModel{
    
    enum LayerManagerColumn{
        LAYER_NAME("Nom du calque", String.class, false),
        VISIBLE("visible", Boolean.class, true),
        ALPHA("alpha", Float.class, true),
        ;
        final private String name;
        final private Class clazz;
        final private boolean editable;

        private LayerManagerColumn(String name, Class clazz, boolean editable) {
            this.name = name;
            this.clazz = clazz;
            this.editable = editable;
        }
    }

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
        return Layer.values()[rowIndex].getValue(LayerManagerColumn.values()[columnIndex]);
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
        
        if(!col.editable)
            return false;
        
        return Layer.values()[rowIndex].getValue(col) != null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        LayerManagerColumn col = LayerManagerColumn.values()[columnIndex];
        Layer.values()[rowIndex].setValue(col, aValue);
    }
    
}
