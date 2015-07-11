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
package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.pvemu.mapeditor.common.utils.Point;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.hierarchy.CellObject;
import org.pvemu.mapeditor.ui.CellObjectRenderer;
import org.pvemu.mapeditor.ui.Icons;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ObjectTab extends JPanel{
    final private CellObject obj;
    final private Cell cell;
    private CellObject.RotationListener listener;
    
    private class ObjectView extends JPanel{

        public ObjectView() {
            setPreferredSize(new Dimension(obj.getTile().getImage().getWidth(), obj.getTile().getImage().getHeight()));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            CellObjectRenderer.render((Graphics2D)g, obj, new Point(getBounds().width / 2, obj.getTile().getImage().getHeight()), false);
        }
        
    }

    public ObjectTab(CellObject obj) {
        super(new BorderLayout());
        this.obj = obj;
        cell = null;
        makeContent();
    }
    
    public ObjectTab(Cell cell){
        super(new BorderLayout());
        this.cell = cell;
        obj = cell.getLayer1();
        makeContent();
    }
    
    private void makeContent(){
        ObjectView view = new ObjectView();
        add(new JScrollPane(view), BorderLayout.CENTER);
        
        listener = (c) -> view.repaint();
        obj.addRotationListener(listener);
        
        JPanel tools = new JPanel(new FlowLayout());
        JButton rotate = new JButton(Icons.ROTATE);
        
        rotate.addActionListener((e) -> obj.flip());
        
        tools.add(rotate);
        
        add(tools, BorderLayout.SOUTH);
    }
    
    public void onClose(){
        obj.removeRotationListener(listener);
    }
}
