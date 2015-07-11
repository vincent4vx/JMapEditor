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
package org.pvemu.mapeditor.base.object.displayer;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.EnumMap;
import java.util.Map;
import org.pvemu.mapeditor.base.editor.ui.EditorGrid;
import org.pvemu.mapeditor.hierarchy.LayerSet;
import org.pvemu.mapeditor.data.LayerMask;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class LayersDisplayer {
    final private Map<LayerSet, LayerDisplayer> displayers = new EnumMap<>(LayerSet.class);
    
    private LayerMask mask = LayerMask.neutralMask();

    public LayersDisplayer() {
        setDisplayer(LayerSet.BACKGROUND, new BackgroundDisplayer());
        setDisplayer(LayerSet.GRID, new GridDisplayer());
        setDisplayer(LayerSet.GROUND, new ObjectDisplayer());
        setDisplayer(LayerSet.MOVEMENT, new MovementDisplayer());
        setDisplayer(LayerSet.LAYER1, new ObjectDisplayer());
        setDisplayer(LayerSet.LINE_OF_SIGHT, new LineOfSightDisplayer());
        setDisplayer(LayerSet.LAYER2, new ObjectDisplayer());
    }
    
    final public void setDisplayer(LayerSet layer, LayerDisplayer displayer){
        displayers.put(layer, displayer);
    }
    
    public void display(EditorGrid grid, Graphics2D g){
        display(grid, g, mask);
    }
    
    public void display(EditorGrid grid, Graphics2D g, LayerMask mask){
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON
         );
        
        for(LayerSet layer : LayerSet.values()){
            displayOne(grid, g, mask, layer);
        }
    }
    
    private void displayOne(EditorGrid grid, Graphics2D g, LayerMask mask, LayerSet layer){
        if (!mask.isVisible(layer)) {
            return;
        }
        
        LayerDisplayer displayer = displayers.get(layer);
        
        if(displayer == null){
            System.err.println("No displayer found for layer " + layer);
            return;
        }

        Composite tmp = g.getComposite();
        
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, mask.getAlpha(layer)));

        displayer.draw(layer, grid, g);

        g.setComposite(tmp);
    }

    public LayerMask getMask() {
        return mask;
    }

    public void setMask(LayerMask mask) {
        this.mask = new LayerMask(mask);
    }
    
    public void mergeMask(LayerMask mask){
        for(LayerSet layer : LayerSet.values()){
            float alpha = this.mask.getAlpha(layer) * mask.getAlpha(layer);
            boolean visible = this.mask.isVisible(layer) && mask.isVisible(layer);
            this.mask.setAlpha(layer, alpha);
            this.mask.setVisible(layer, visible);
        }
    }
}
