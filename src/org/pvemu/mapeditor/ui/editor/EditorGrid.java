package org.pvemu.mapeditor.ui.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.Utils;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditorGrid extends JPanel{
    final private MapData map;
    final private List<GridCell> shapes;
    final private GridListener listener;
    final private EditorHandler handler;
    

    public EditorGrid(MapData map, EditorHandler handler) {
        this.map = map;
        this.handler = handler;
        shapes = new ArrayList<>(Utils.getCellNumberBySize(map.getInfo().getWidth(), map.getInfo().getHeight()));
        listener = new GridListener(this);
        
        int _loc14 = map.getInfo().getWidth() - 1;
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
        
        //display layers
        for(Layer layer : Layer.values()){
            LayerDisplayer.display(layer, this, g2d);
        }
        
        //display current cell
        GridCell cell = listener.getHovered();
        if (cell != null) {
            Color tmp = g.getColor();
            g.setColor(Constants.SELECTED_COLOR);
            g.fillPolygon(cell);
            g.setColor(tmp);
        }
    }

    List<GridCell> getShapes() {
        return shapes;
    }

    public MapData getMap() {
        return map;
    }

    public EditorHandler getHandler() {
        return handler;
    }
    
}