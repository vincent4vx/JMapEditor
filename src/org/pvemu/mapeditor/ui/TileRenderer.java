/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.data.Tile;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TileRenderer implements ListCellRenderer<Tile>{

    @Override
    public Component getListCellRendererComponent(JList<? extends Tile> list, Tile value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        label.setText("" + value.getId());
        label.setIcon(new ImageIcon(value.getImage().getScaledInstance(100, 63, BufferedImage.SCALE_FAST)));
        
        if(isSelected)
            label.setBackground(Constants.SELECTED_COLOR);
        
        label.setOpaque(true);
        return label;
    }
    
}
