package org.pvemu.mapeditor.common;

import org.pvemu.mapeditor.data.LayerMask;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public interface LayerMaskable {
    public LayerMask getLayerMask();
    
    default public boolean isPriority(){ 
        return false; 
    };
}
