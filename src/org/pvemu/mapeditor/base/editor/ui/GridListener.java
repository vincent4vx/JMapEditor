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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.pvemu.mapeditor.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
class GridListener extends MouseAdapter{
    final private EditorGrid grid;
    private GridCell hovered = null;

    public GridListener(EditorGrid grid) {
        this.grid = grid;
    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        if(!grid.getHandler().haveFocus())
//            return;
//        
//        for (GridCell cell : grid.getShapes()) {
//            if (cell.contains(e.getPoint())) {
//                if (hovered != cell) {
//                    if(hovered != null)
//                        hovered.setHovered(false);
//                    
//                    cell.setHovered(true);
//                    hovered = cell;
//                    grid.repaint();
//                }
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (!grid.getHandler().haveFocus() || hovered == null) {
//            return;
//        }
//        
//        JMapEditor.getToolsHandler().getTool().onClick(grid.getHandler(), hovered.getCell());
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//        if(!grid.getHandler().haveFocus())
//            return;
//        
//        if(hovered != null)
//            hovered.setHovered(false);
//        
//        hovered = null;
//        grid.repaint();
//    }
//
//    public GridCell getHovered() {
//        return hovered;
//    }

}
