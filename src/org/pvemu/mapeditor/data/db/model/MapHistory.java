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
public class MapHistory {
    final private long date;
    final private int background;
    final private int music;
    final private String cells;

    public MapHistory(long date, int background, int music, String cells) {
        this.date = date;
        this.background = background;
        this.music = music;
        this.cells = cells;
    }

    public int getBackground() {
        return background;
    }

    public int getMusic() {
        return music;
    }

    public long getDate() {
        return date;
    }

    public String getCells() {
        return cells;
    }
}
