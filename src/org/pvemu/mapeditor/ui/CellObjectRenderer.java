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
package org.pvemu.mapeditor.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.common.utils.Coordinate;
import org.pvemu.mapeditor.hierarchy.CellObject;

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
        int y = (int) (coordinate.getY() - height + JMapEditor.getParameters().getDouble("CELL_HALF_HEIGHT"));

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
