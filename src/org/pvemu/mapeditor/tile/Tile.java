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
package org.pvemu.mapeditor.tile;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Tile {
    final private int id;
    final private BufferedImage image;
    final private ImageIcon thumbnail;

    public Tile(int id, BufferedImage image) {
        this.id = id;
        this.image = image;
        double rate = (double)image.getWidth() / (double)image.getHeight();
        int width, height;
        
        if(rate > 1){
            width = 100;
            height = (int)(width / rate);
        }else{
            height = 100;
            width = (int)(height * rate);
        }
        
        thumbnail = new ImageIcon(image.getScaledInstance(width, height, BufferedImage.SCALE_FAST));
    }

    public int getId() {
        return id;
    }

    public BufferedImage getImage() {
        return image;
    }

    public ImageIcon getThumbnail() {
        return thumbnail;
    }
}
