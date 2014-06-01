/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

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
