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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import org.pvemu.mapeditor.tile.TilesListContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class FolderSelector extends JPanel{
    final private JTree tree;
    final private TileListUI tileListUI;
    final private Map<DefaultMutableTreeNode, TilesListContainer> tilesByNode = new HashMap<>();

    public FolderSelector(TilesListContainer container, TileListUI tileListUI) {
        this.tileListUI = tileListUI;
        tree = new JTree(createTree(container));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        tree.addTreeSelectionListener((e) -> {
            tileListUI.setTiles(tilesByNode.get((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent()).getTilesList());
        });
        
        JScrollPane scroll = new JScrollPane(tree);
        scroll.setPreferredSize(new Dimension(250, 120));
        add(scroll);
    }
    
    private DefaultMutableTreeNode createTree(TilesListContainer tlc){
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(tlc.getDirectory().getName());
        
        for(TilesListContainer tlc1 : tlc.getTilesListContainers()){
            node.add(createTree(tlc1));
        }
        
        tilesByNode.put(node, tlc);
        
        return node;
    }
}
