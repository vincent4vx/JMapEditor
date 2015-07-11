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
package org.pvemu.mapeditor.ui;

import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.tile.Tile;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TileRenderer implements ListCellRenderer<Tile>{

    @Override
    public Component getListCellRendererComponent(JList<? extends Tile> list, Tile value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = new JLabel();
        label.setText("" + value.getId());
        label.setIcon(value.getThumbnail());
        
        if(isSelected){
            label.setBackground(Constants.SELECTED_COLOR);
            label.setOpaque(true);
        }
        
        return label;
    }
    
}
