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
package org.pvemu.mapeditor.data;

import java.util.EnumMap;
import java.util.Map.Entry;
import org.pvemu.mapeditor.common.utils.IPair;
import org.pvemu.mapeditor.common.utils.MutablePair;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayerMask {
    final private EnumMap<LayerSet, MutablePair<Boolean, Float>> masks;
    
    final private static EnumMap<LayerSet, MutablePair<Boolean, Float>> defaultMasks = new EnumMap<>(LayerSet.class);
    
    static {
        for(LayerSet layer : LayerSet.values()){
            defaultMasks.put(layer, new MutablePair<>(false, 0f));
        }
    }

    public LayerMask() {
        masks = new EnumMap<>(defaultMasks);
    }
    
    public LayerMask(LayerMask obj){
        masks = new EnumMap<>(LayerSet.class);
        
        for(Entry<LayerSet, MutablePair<Boolean, Float>> entry : obj.masks.entrySet()){
            masks.put(entry.getKey(), new MutablePair<>(entry.getValue().getFirst(), entry.getValue().getSecond()));
        }
    }
    
    public void setAlpha(LayerSet layer, float alpha){
        masks.get(layer).setSecond(alpha);
    }
    
    public void setVisible(LayerSet layer, boolean visible){
        masks.get(layer).setFirst(visible);
    }
    
    public float getAlpha(LayerSet layer){
        return masks.get(layer).getSecond();
    }
    
    public boolean isVisible(LayerSet layer){
        IPair<Boolean, Float> pair = masks.get(layer);
        return pair.getFirst() && pair.getSecond() != 0;
    }
    
    static public LayerMask neutralMask(){
        LayerMask mask = new LayerMask();
        
        for(LayerSet layer : LayerSet.values()){
            mask.setAlpha(layer, 1f);
            mask.setVisible(layer, true);
        }
        
        return mask;
    }
}
