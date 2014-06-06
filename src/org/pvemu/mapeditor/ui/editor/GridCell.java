/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.ui.editor;

import java.awt.Polygon;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.Coordinate;
import org.pvemu.mapeditor.data.Cell;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
class GridCell extends Polygon implements Coordinate{

    final private Cell cell;
    final private int x;
    final private int y;
    private boolean hovered = false;

    public GridCell(Cell cell, int x, int y) {
        this.cell = cell;
        this.x = x;
        this.y = y;

        addPoint((int) (x - Constants.CELL_HALF_WIDTH), y);
        addPoint(x, (int) (y + Constants.CELL_HALF_HEIGHT));
        addPoint((int) (x + Constants.CELL_HALF_WIDTH), y);
        addPoint(x, (int) (y - Constants.CELL_HALF_HEIGHT));
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }
}