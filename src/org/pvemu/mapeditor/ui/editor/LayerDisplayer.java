/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.editor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.List;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract class LayerDisplayer {
    final static public LayerDisplayer LAYER_ONE = new LayerDisplayer() {
        @Override
        CellObject extractObject(Cell cell) {
            return cell.getLayer1();
        }
    };
    
    final static public LayerDisplayer LAYER_TWO = new LayerDisplayer() {
        @Override
        CellObject extractObject(Cell cell) {
            return cell.getLayer2();
        }
    };
    
    final static public LayerDisplayer GROUND = new LayerDisplayer() {
        @Override
        CellObject extractObject(Cell cell) {
            return cell.getGround();
        }
    };

    private LayerDisplayer() {
    }
    
    abstract CellObject extractObject(Cell cell);
    
    public void draw(Graphics2D g, List<GridCell> shapes){
        for(GridCell shape : shapes){
            CellObject obj = extractObject(shape.getCell());
            
            if(obj == null)
                continue;
            
            BufferedImage img = obj.getTile().getImage();
            
            int width = img.getWidth();
            int height = img.getHeight();
            
            if(obj.isFlip()){
                width = -width;
            }
            
            if(shape.isSelected()){
                RescaleOp rescale = new RescaleOp(new float[]{1f, 1f, 1f, 1f}, new float[]{100f, 100f, 100f, 0f}, null);
                img = rescale.filter(img, null);
            }
            
            g.drawImage(img, shape.getX(), shape.getY(), width, height, null);
        }
    }
}
