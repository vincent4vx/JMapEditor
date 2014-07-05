package org.pvemu.mapeditor.data;

import java.util.EnumMap;
import java.util.Map.Entry;
import org.pvemu.mapeditor.common.IPair;
import org.pvemu.mapeditor.common.MutablePair;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayerMask {
    final private EnumMap<Layer, MutablePair<Boolean, Float>> masks;
    
    final private static EnumMap<Layer, MutablePair<Boolean, Float>> defaultMasks = new EnumMap<>(Layer.class);
    
    static {
        for(Layer layer : Layer.values()){
            defaultMasks.put(layer, new MutablePair<>(false, 0f));
        }
    }

    public LayerMask() {
        masks = new EnumMap<>(defaultMasks);
    }
    
    public LayerMask(LayerMask obj){
        masks = new EnumMap<>(Layer.class);
        
        for(Entry<Layer, MutablePair<Boolean, Float>> entry : obj.masks.entrySet()){
            masks.put(entry.getKey(), new MutablePair<>(entry.getValue().getFirst(), entry.getValue().getSecond()));
        }
    }
    
    public void setAlpha(Layer layer, float alpha){
        masks.get(layer).setSecond(alpha);
    }
    
    public void setVisible(Layer layer, boolean visible){
        masks.get(layer).setFirst(visible);
    }
    
    public float getAlpha(Layer layer){
        return masks.get(layer).getSecond();
    }
    
    public boolean isVisible(Layer layer){
        IPair<Boolean, Float> pair = masks.get(layer);
        return pair.getFirst() && pair.getSecond() != 0;
    }
}
