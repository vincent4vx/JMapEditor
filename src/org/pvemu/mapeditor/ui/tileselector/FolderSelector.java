/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.TilesListContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class FolderSelector extends JPanel{
    private TilesListContainer container = JMapEditor.getTilesHandler().getObjects();
    final private JTree tree;
    final private TileListUI tileListUI;
    final private Map<DefaultMutableTreeNode, TilesListContainer> tilesByNode = new HashMap<>();

    public FolderSelector(TileListUI tileListUI) {
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

    public TilesListContainer getContainer() {
        return container;
    }

    public void setContainer(TilesListContainer container) {
        this.container = container;
    }
}
