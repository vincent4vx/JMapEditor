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
package org.pvemu.mapeditor.base.editor.ui;

import java.awt.Polygon;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.common.utils.Coordinate;
import org.pvemu.mapeditor.base.editor.data.Cell;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class GridCell extends Polygon implements Coordinate{

    final private Cell cell;
    final private int x;
    final private int y;
    private boolean hovered = false;

    public GridCell(Cell cell, int x, int y) {
        this.cell = cell;
        this.x = x;
        this.y = y;
        
        final double CELL_HALF_WIDTH = JMapEditor.APP.getParameters().getDouble("CELL_HALF_WIDTH");
        final double CELL_HALF_HEIGHT = JMapEditor.APP.getParameters().getDouble("CELL_HALF_HEIGHT");

        addPoint((int) (x - CELL_HALF_WIDTH), y);
        addPoint(x, (int) (y + CELL_HALF_HEIGHT));
        addPoint((int) (x + CELL_HALF_WIDTH), y);
        addPoint(x, (int) (y - CELL_HALF_HEIGHT));
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
