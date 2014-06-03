/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.editor;

import org.pvemu.mapeditor.ui.CellObjectRenderer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.Utils;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.handler.tool.AddTool;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapGrid extends JPanel{
    final private MapData map;
    final private List<GridCell> shapes;
    final private GridListener listener;
    

    public MapGrid(MapData map) {
        this.map = map;
        shapes = new ArrayList<>(Utils.getCellNumberBySize(map.getWidth(), map.getHeight()));
        listener = new GridListener(this);
        
        int _loc14 = map.getWidth() - 1;
        int _loc9 = -1;
        int _loc10 = 0;
        double _loc11 = 0;
        
        
        for(Cell cell : map){
            if(_loc9 == _loc14){
                _loc9 = 0;
                ++_loc10;

                if(_loc11 == 0D){
                    _loc11 = Constants.CELL_HALF_WIDTH;
                    --_loc14;
                }else{
                    _loc11 = 0;
                    ++_loc14;
                }
            }else{
                ++_loc9;
            }
            
            int x = (int)(_loc9 * Constants.CELL_WIDTH + _loc11);
            int y = (int)(_loc10 * Constants.CELL_HALF_HEIGHT);
            
            shapes.add(new GridCell(cell, x, y));
        }
        
        setBackground(Constants.BACKGROUND_COLOR);
        
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON
         );
        
        if(map.getBackground() != null){
            g.drawImage(map.getBackground().getImage(), 0, 0, getSize().width, getSize().height ,this);
        }
        
        LayerDisplayer.GROUND.draw(g2d, shapes);
        LayerDisplayer.LAYER_ONE.draw(g2d, shapes);
        LayerDisplayer.LAYER_TWO.draw(g2d, shapes);
        
        for(GridCell cell : shapes){
        
            g.setColor(Constants.GRID_COLOR);
            
            g.drawLine(
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][0][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][0][1] + cell.getY()), 
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][1] + cell.getY())
            );
            g.drawLine(
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][1] + cell.getY()), 
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][2][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.getCell().getGroundSlope()][2][1] + cell.getY())
            );
            
            if(cell.isHovered()){
                g.setColor(Constants.SELECTED_COLOR);
                g.fillPolygon(cell);
                
                if(cell.getCell().getLayer1() != null || !(JMapEditor.getToolsHandler().getTool() instanceof AddTool))
                    continue;
                
                CellObjectRenderer.render(g2d, JMapEditor.getToolsHandler().getCurrentObject(), cell, false);
            }
        }
    }

    List<GridCell> getShapes() {
        return shapes;
    }
    
}
