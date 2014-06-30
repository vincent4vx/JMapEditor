/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.Coordinate;
import org.pvemu.mapeditor.data.CellObject;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class CellObjectRenderer {

    static public void render(Graphics2D g, CellObject obj, Coordinate coordinate, boolean selected) {
        if (obj == null) {
            return;
        }

        BufferedImage img = obj.getTile().getImage();

        int width = img.getWidth();
        int height = img.getHeight();
        
        int x = coordinate.getX() - width / 2;
        int y = (int) (coordinate.getY() - height + JMapEditor.getParametersHandler().getDouble("CELL_HALF_HEIGHT"));

        if (obj.isFlip()) {
            x += width;
            width = -width;
        }

        if (selected) {
            RescaleOp rescale = new RescaleOp(new float[]{1f, 1f, 1f, 1f}, new float[]{100f, 100f, 100f, 0f}, null);
            img = rescale.filter(img, null);
        }

        g.drawImage(img, x, y, width, height, null);
    }
}
