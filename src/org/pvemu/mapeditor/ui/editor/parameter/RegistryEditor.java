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
package org.pvemu.mapeditor.ui.editor.parameter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.param.registry.Registry;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RegistryEditor extends JFrame{
    final private Map<DefaultMutableTreeNode, Registry> regByNode = new HashMap<>();
    final private JTree tree;

    public RegistryEditor() throws HeadlessException {
        super("Éditeur du registre");
        
        tree = new JTree(createTree(JMapEditor.getRegistry()));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        JScrollPane scroll = new JScrollPane(tree);
        scroll.setPreferredSize(new Dimension(250, 120));
        add(scroll, BorderLayout.CENTER);
        makeButtons();
        pack();
        setLocationRelativeTo(JMapEditor.getUI());
    }
    
    private void makeButtons(){
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton add = new JButton("Ajouter");
        JButton remove = new JButton("Supprimer");
        JButton edit = new JButton("Éditer");
        
        add.addActionListener((e) -> add());
        remove.addActionListener((e) -> remove());
        edit.addActionListener((e) -> edit());
        
        panel.add(add);
        panel.add(remove);
        panel.add(edit);
        
        add(panel, BorderLayout.SOUTH);
    }
    
    private void add(){
        TreePath path = tree.getSelectionPath();
        
        Registry registry = JMapEditor.getRegistry();
        
        if(path != null){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

            if(node != null)
                registry = regByNode.get(node);
        }
        
        EditParameterDialog dialog = new EditParameterDialog(this);
        int r = dialog.edit();
        
        if(r == EditParameterDialog.CANCEL_OPTION)
            return;
          
        try{
             registry.add(
                    dialog.getParamName(), 
                    dialog.getParamType(), 
                    dialog.getParamValue()
            );
        }catch(Exception e){
            JMapEditor.getErrorHandler().showError(this, "Edition du registre : erreur", e);
            return;
        }
        
        updateTree();
    }
    
    private void remove(){
        TreePath path = tree.getSelectionPath();
        
        if(path == null)
            return;
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        if(node == null)
            return;
        
        Registry registry = regByNode.get(node);
        
        try{
            registry.unset("");
            ((DefaultTreeModel)tree.getModel()).removeNodeFromParent(node);
        }catch(Exception e){
            JMapEditor.getErrorHandler().showError(this, "Edition du registre : erreur", e);
        }
    }
    
    private void edit(){
        TreePath path = tree.getSelectionPath();
        
        if(path == null)
            return;
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        
        if(node == null)
            return;
        
        Registry registry = regByNode.get(node);
        
        EditParameterDialog dialog = new EditParameterDialog(this, registry.getData());
        int r = dialog.edit();
        
        if(r == EditParameterDialog.CANCEL_OPTION)
            return;
        
        try{
            registry.set("", dialog.getParamType(), dialog.getParamValue());
        }catch(Exception e){
            JMapEditor.getErrorHandler().showError(this, "Edition du registre : erreur", e);
        }
    }
    
    private DefaultMutableTreeNode createTree(Registry registry){
        regByNode.clear();
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(registry.getData().getName());
        
        for(Registry child : registry.getChildren()){
            node.add(createTree(child));
        }
        
        regByNode.put(node, registry);
        return node;
    }
    
    private void updateTree(){
        tree.setModel(new DefaultTreeModel(createTree(JMapEditor.getRegistry())));
    }
}
