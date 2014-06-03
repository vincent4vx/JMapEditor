/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.ui.editor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.CellObject;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
class GridListener extends MouseAdapter {
    final private MapGrid grid;
    private GridCell selected = null;

    public GridListener(MapGrid grid) {
        this.grid = grid;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GridCell cell : grid.getShapes()) {
            if (cell.contains(e.getPoint())) {
                if (selected != cell) {
                    if(selected != null)
                        selected.setHovered(false);
                    
                    cell.setHovered(true);
                    selected = cell;
                    grid.repaint();
                }
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (selected == null) {
            return;
        }
        
        JMapEditor.getToolsHandler().getTool().onClick(selected.getCell());
    }

}
