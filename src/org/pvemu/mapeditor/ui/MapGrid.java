/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.Utils;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.MapData;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapGrid extends JPanel{
    final private MapData map;
    final private ArrayList<GridCell> shapes;
    private GridCell selected = null;
    
    private class GridCell extends Polygon{
        final private Cell cell;
        final private int x;
        final private int y;

        public GridCell(Cell cell, int x, int y) {
            this.cell = cell;
            this.x = x;
            this.y = y;
            
            addPoint((int)(x - Constants.CELL_HALF_WIDTH), y);
            addPoint(x, (int)(y + Constants.CELL_HALF_HEIGHT));
            addPoint((int)(x + Constants.CELL_HALF_WIDTH), y);
            addPoint(x, (int)(y - Constants.CELL_HALF_HEIGHT));
        }

        public Cell getCell() {
            return cell;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public MapGrid(MapData map) {
        this.map = map;
        shapes = new ArrayList<>(Utils.getCellNumberBySize(map.getWidth(), map.getHeight()));
        
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
        
        addMouseMotionListener(new MouseAdapter() {
            int lastCell = 0;

            @Override
            public void mouseMoved(MouseEvent e) {
                for(GridCell cell : shapes){
                    if(cell.contains(e.getPoint())){
                        if(selected != cell){
                            selected = cell;
                            repaint();
                        }
                        break;
                    }
                }
            }
            
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(map.getBackground() != null){
            g.drawImage(map.getBackground().getImage(), 0, 0, getSize().width, getSize().height ,this);
        }
        
        g.setColor(Constants.GRID_COLOR);
        
        for(GridCell cell : shapes){            
            g.drawLine(
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][0][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][0][1] + cell.getY()), 
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][1][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][1][1] + cell.getY())
            );
            g.drawLine(
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][1][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][1][1] + cell.getY()), 
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][2][0] + cell.getX()), 
                    (int)(Constants.CELL_COORD[cell.cell.getGroundSlope()][2][1] + cell.getY())
            );
        }
        
        g.setColor(Constants.SELECTED_COLOR);
        
        if(selected != null){
            g.fillPolygon(selected);
        }
    }
    
}
