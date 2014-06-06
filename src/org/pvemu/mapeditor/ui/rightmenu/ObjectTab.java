/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.pvemu.mapeditor.common.Point;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
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
            CellObjectRenderer.render((Graphics2D)g, obj, new Point(0, 0), false);
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
