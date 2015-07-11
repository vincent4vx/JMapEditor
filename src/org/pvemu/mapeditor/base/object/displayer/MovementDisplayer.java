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
package org.pvemu.mapeditor.base.object.displayer;

import java.awt.Color;
import java.awt.Graphics2D;
import org.pvemu.mapeditor.base.editor.ui.EditorGrid;
import org.pvemu.mapeditor.base.editor.ui.GridCell;
import org.pvemu.mapeditor.hierarchy.LayerSet;
import org.pvemu.mapeditor.common.Constants;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MovementDisplayer implements LayerDisplayer{

        @Override
        public void draw(LayerSet layer, EditorGrid grid, Graphics2D g) {
            for(GridCell cell : grid.getShapes()){
                g.setColor(Constants.UNWALKABLE_COLOR);
                
                if(cell.getCell().getMovement() != 4){
                    g.fill(cell);
                    g.setColor(Color.BLACK);
                    g.drawString(cell.getCell().getMovement() + "", cell.getX(), cell.getY());
                }
            }
        }
    
}
