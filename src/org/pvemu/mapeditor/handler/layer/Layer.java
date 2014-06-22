/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.layer;

import java.util.EnumMap;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public enum Layer {
    BACKGROUND("Background", false),
    GROUND("Sol", true),
    LAYER1("Calque 1", true),
    LAYER2("Calque 2", true),
    GRID("Grille", false),
    ;
    private static Layer selected = GROUND;
    
    final private EnumMap<LayerHandler.LayerManagerColumn, Object> values = new EnumMap<>(LayerHandler.LayerManagerColumn.class);
    final private String name;
    final private boolean editable;

    Layer(String name, boolean editable) {
        this.name = name;
        this.editable = editable;
        values.put(LayerHandler.LayerManagerColumn.LAYER_NAME, name);
        values.put(LayerHandler.LayerManagerColumn.VISIBLE, true);
        values.put(LayerHandler.LayerManagerColumn.ALPHA, 1f);
    }
    
    Object getValue(LayerHandler.LayerManagerColumn col){
        return values.get(col);
    }
    
    void setValue(LayerHandler.LayerManagerColumn col, Object value){
        values.put(col, value);
    }

    public boolean isVisible() {
        return (boolean)values.get(LayerHandler.LayerManagerColumn.VISIBLE);
    }

    public boolean isEditable() {
        return editable;
    }

    public boolean isSelected() {
        return equals(selected);
    }


    public String getName() {
        return name;
    }
    
    public float getAlpha(){
        float alpha = (float) values.get(LayerHandler.LayerManagerColumn.ALPHA);
        
        if(editable && !isSelected())
            alpha /= 1.5;
        
        return alpha;
    }
    
    @Override
    public String toString(){
        return name;
    }

    public static Layer getSelected() {
        return selected;
    }

    public static void setSelected(Layer selected) {
        Layer.selected = selected;
    }
}
