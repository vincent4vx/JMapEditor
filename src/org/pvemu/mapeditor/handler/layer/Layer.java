package org.pvemu.mapeditor.handler.layer;

import java.util.EnumMap;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.TilesListContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public enum Layer {
    BACKGROUND("Background", null),
    WALKABLE("Marchable", null),
    GRID("Grille", null),
    GROUND("Sol", JMapEditor.getTilesHandler().getGrounds()),
    LAYER1("Calque 1", JMapEditor.getTilesHandler().getObjects()),
    LAYER2("Calque 2", JMapEditor.getTilesHandler().getObjects()),
    LINE_OF_SIGHT("Ligne de vue", null),
    ;
    private static Layer selected = GROUND;
    
    final private EnumMap<LayerHandler.LayerManagerColumn, Object> values = new EnumMap<>(LayerHandler.LayerManagerColumn.class);
    final private String name;
    final private TilesListContainer tiles;

    Layer(String name, TilesListContainer tiles) {
        this.name = name;
        this.tiles = tiles;
        
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
        return tiles != null;
    }

    public boolean isSelected() {
        return equals(selected);
    }


    public String getName() {
        return name;
    }
    
    public float getAlpha(){
        float alpha = (float) values.get(LayerHandler.LayerManagerColumn.ALPHA);
        
        if(isEditable() && !isSelected())
            alpha /= 1.5;
        
        return alpha;
    }

    public TilesListContainer getTiles() {
        return tiles;
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
