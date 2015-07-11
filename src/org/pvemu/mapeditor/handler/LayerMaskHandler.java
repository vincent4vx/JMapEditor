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
package org.pvemu.mapeditor.handler;

import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.LayerMaskable;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayerMaskHandler {
    final private List<LayerMaskable> maskables = new ArrayList<>();
    final private LayerMask neutralMask = new LayerMask();
    final private LayerMask previewMask = new LayerMask();

    public LayerMaskHandler() {
        fillNeutralMask();
        fillPreviewMask();
    }
    
    public void registerLayerMaskable(LayerMaskable lm){
        maskables.add(lm);
    }
    
    private void fillNeutralMask(){
        for(LayerSet layer : LayerSet.values()){
            neutralMask.setAlpha(layer, 1f);
            neutralMask.setVisible(layer, true);
        }
    }
    
    private void fillPreviewMask(){
        LayerSet[] toDisplay = {
            LayerSet.BACKGROUND, LayerSet.GROUND, LayerSet.LAYER1, LayerSet.LAYER2
        };
        
        for(LayerSet layer : toDisplay){
            previewMask.setAlpha(layer, 1f);
            previewMask.setVisible(layer, true);
        }
    }
    
    private void merge(LayerMask result, LayerMask mask){
        if(mask == null)
            return;
        
        for(LayerSet layer : LayerSet.values()){
            float alpha = result.getAlpha(layer) * mask.getAlpha(layer);
            boolean visible = result.isVisible(layer) && mask.isVisible(layer);
            result.setAlpha(layer, alpha);
            result.setVisible(layer, visible);
        }
    }
    
    public LayerMask mergeAllMasks(){
        LayerMask mask = getNeutralMask();
        
        for(LayerMaskable lm : maskables){
            if(lm.isPriority())
                return new LayerMask(lm.getLayerMask());
            
            merge(mask, lm.getLayerMask());
        }
        
        return mask;
    }

    public LayerMask getNeutralMask() {
        return new LayerMask(neutralMask);
    }
    
    public LayerMask getPreviewMask(){
        return new LayerMask(previewMask);
    }
}
