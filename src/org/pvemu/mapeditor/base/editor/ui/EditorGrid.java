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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.utils.Utils;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.base.editor.data.MapData;
import org.pvemu.mapeditor.base.editor.Editor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditorGrid extends JPanel{
    final private MapData map;
    final private List<GridCell> shapes;
    final private GridListener listener;
    final private Editor editor;
    

    public EditorGrid(MapData map, Editor editor) {
        this.map = map;
        this.editor = editor;
        shapes = new ArrayList<>(Utils.getCellNumberBySize(map.getWidth(), map.getHeight()));
        listener = new GridListener(this);
        
        setBackground(Constants.BACKGROUND_COLOR);
        
        reloadShapes();
        
        addMouseListener(listener);
        addMouseMotionListener(listener);
        
        JPopupMenu menu = new JPopupMenu("test");
        
        menu.add(new JMenu("test"));
        menu.add(new JMenu("test"));
        menu.add(new JMenu("test"));
        menu.add(new JMenu("test"));
        
        setComponentPopupMenu(menu);
    }
    
    public void reloadShapes(){
        shapes.clear();
        
        int _loc14 = map.getWidth() - 1;
        int _loc9 = -1;
        int _loc10 = 0;
        double _loc11 = 0;
        
        final double CELL_HALF_WIDTH = JMapEditor.APP.getParameters().getDouble("CELL_HALF_WIDTH");
        final double CELL_HALF_HEIGHT = JMapEditor.APP.getParameters().getDouble("CELL_HALF_HEIGHT");
        final int CELL_WIDTH = JMapEditor.APP.getParameters().getInt("CELL_WIDTH");
        final int LEVEL_HEIGHT = JMapEditor.APP.getParameters().getInt("LEVEL_HEIGHT");
        
        for(Cell cell : map){
            if(_loc9 == _loc14){
                _loc9 = 0;
                ++_loc10;

                if(_loc11 == 0D){
                    _loc11 = CELL_HALF_WIDTH;
                    --_loc14;
                }else{
                    _loc11 = 0;
                    ++_loc14;
                }
            }else{
                ++_loc9;
            }
            
            int x = (int)(_loc9 * CELL_WIDTH + _loc11);
            int y = (int)(_loc10 * CELL_HALF_HEIGHT - LEVEL_HEIGHT * (cell.getGroundLevel() - 7));
            
            shapes.add(new GridCell(cell, x, y));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        JMapEditor.APP.getEditorsHandler().getLayersDisplayer().display(this, g2d);
    }
    
    /*public void paintLayers(Graphics2D g, LayerMask mask){
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON
         );
        
        //display layers
        for(Layer layer : Layer.values()){
            LayerDisplayer.display(layer, this, g, mask);
        }
        
        //if(isEdit){
            //display current cell
            GridCell cell = listener.getHovered();
            if (cell != null) {
                Color tmp = g.getColor();
                g.setColor(Constants.SELECTED_COLOR);
                g.fillPolygon(cell);
                g.setColor(tmp);
            }
        //}
    }*/

    public List<GridCell> getShapes() {
        return shapes;
    }

    public MapData getMap() {
        return map;
    }

    public Editor getHandler() {
        return editor;
    }
    
}
