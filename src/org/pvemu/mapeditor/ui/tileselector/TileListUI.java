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
package org.pvemu.mapeditor.ui.tileselector;

import java.awt.Dimension;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.hierarchy.CellObject;
import org.pvemu.mapeditor.tile.Tile;
import org.pvemu.mapeditor.tile.TilesList;
import org.pvemu.mapeditor.handler.tool.Tools;
import org.pvemu.mapeditor.ui.TileRenderer;
import org.pvemu.mapeditor.ui.rightmenu.ObjectTab;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TileListUI extends JScrollPane{
    final private JList<Tile> list = new JList<>();

    public TileListUI() {
        setPreferredSize(new Dimension(getPreferredSize().width, 120));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TileRenderer());
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        
        list.addListSelectionListener((e) -> {
            Tile tile = list.getSelectedValue();
            
            if(tile == null)
                return;
            
            CellObject obj = new CellObject(tile);
            JMapEditor.getToolsHandler().setCurrentObject(obj);
            //JMapEditor.getUI().getRightToolBar().setObjectTab(new ObjectTab(obj));
            JMapEditor.getToolsHandler().setTool(Tools.ADD);
        });
        
        setViewportView(list);
    }

    public void setTiles(TilesList tiles) {
        list.setModel(tiles);
    }
    
    public void clear(){
        list.setModel(new AbstractListModel<Tile>() {

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public Tile getElementAt(int index) {
                return null;
            }
        });
    }
    
}
