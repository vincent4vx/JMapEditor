/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data.db.model;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapInfo {
    final private int id;
    final private int width;
    final private int height;
    private long lastDate;

    public MapInfo(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.lastDate = System.currentTimeMillis();
    }

    public MapInfo(int id, int width, int height, long lastDate) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.lastDate = lastDate;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getLastDate() {
        return lastDate;
    }

    public void setLastDate(long lastDate) {
        this.lastDate = lastDate;
    }
}
