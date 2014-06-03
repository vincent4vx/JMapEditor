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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.Point;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.ui.CellObjectRenderer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ObjectTab extends JPanel{
    final private CellObject obj;
    
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
        
        ObjectView view = new ObjectView();
        add(new JScrollPane(view), BorderLayout.CENTER);
        
        JPanel tools = new JPanel(new FlowLayout());
        JButton rotate = new JButton(new ImageIcon(Constants.UI_RESOURCES_DIR + "rotate.png"));
        rotate.addActionListener((e) -> {
            obj.flip();
            view.repaint();
        });
        tools.add(rotate);
        
        add(tools, BorderLayout.SOUTH);
    }
    
    
}
