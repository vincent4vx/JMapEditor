package org.pvemu.mapeditor.handler;

import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.LayerMaskable;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.handler.layer.Layer;

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
        for(Layer layer : Layer.values()){
            neutralMask.setAlpha(layer, 1f);
            neutralMask.setVisible(layer, true);
        }
    }
    
    private void fillPreviewMask(){
        Layer[] toDisplay = {
            Layer.BACKGROUND, Layer.GROUND, Layer.LAYER1, Layer.LAYER2
        };
        
        for(Layer layer : toDisplay){
            previewMask.setAlpha(layer, 1f);
            previewMask.setVisible(layer, true);
        }
    }
    
    private void merge(LayerMask result, LayerMask mask){
        if(mask == null)
            return;
        
        for(Layer layer : Layer.values()){
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
