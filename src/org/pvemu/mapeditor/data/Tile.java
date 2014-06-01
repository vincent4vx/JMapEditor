/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Tile {
    final private int id;
    final private BufferedImage image;

    public Tile(int id, BufferedImage image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public BufferedImage getImage() {
        return image;
    }
}
