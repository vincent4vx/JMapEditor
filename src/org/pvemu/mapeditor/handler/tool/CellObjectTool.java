package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract public class CellObjectTool implements Tool{

    @Override
    public LayerMask getLayerMask() {
        LayerMask mask = JMapEditor.getMaskHandler().getNeutralMask();
        Layer selected = JMapEditor.getLayerHandler().getSelected();
        float alpha = JMapEditor.getParameters().getFloatDefault("UNSELECTED_LAYER_ALPHA_RATE", .75f);
        
        for(Layer layer : Layer.values()){
            if(layer.equals(selected))
                continue;
            
            mask.setAlpha(layer, alpha);
        }
        
        return mask;
    }
}
