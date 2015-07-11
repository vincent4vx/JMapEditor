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

import java.awt.Graphics2D;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.hierarchy.CellObject;
import org.pvemu.mapeditor.base.editor.ui.EditorGrid;
import org.pvemu.mapeditor.base.editor.ui.GridCell;
import org.pvemu.mapeditor.hierarchy.LayerSet;
import org.pvemu.mapeditor.ui.CellObjectRenderer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ObjectDisplayer implements LayerDisplayer{
    @Override
    public void draw(LayerSet layer, EditorGrid grid, Graphics2D g) {
        //Cell currentCell = grid.getHandler().getSelectedCell();
        for (GridCell shape : grid.getShapes()) {
            CellObject obj = shape.getCell().getObjectAt(layer);

            if (obj == null) {
                    //display object to add
/*                    if(shape.isHovered() && layer.isSelected() && JMapEditor.getToolsHandler().getTool() instanceof AddTool){
                 CellObjectRenderer.render(g, JMapEditor.getToolsHandler().getCurrentObject(), shape, false);
                 }*/
                continue;
            }

            /*boolean hightlight = (shape.isHovered()
                    || currentCell == shape.getCell())/* && layer.isSelected()*/;

            boolean hightlight = false;
            CellObjectRenderer.render(g, obj, shape, hightlight);
        }
    }

}
