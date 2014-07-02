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
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.handler.setting.RegistryHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RegistryEditor extends JFrame{
    final private Map<DefaultMutableTreeNode, RegistryHandler> regByNode = new HashMap<>();
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
        
        RegistryHandler registry = JMapEditor.getRegistry();
        
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
        
        RegistryHandler registry = regByNode.get(node);
        
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
        
        RegistryHandler registry = regByNode.get(node);
        
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
    
    private DefaultMutableTreeNode createTree(RegistryHandler registry){
        regByNode.clear();
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(registry.getData().getName());
        
        for(RegistryHandler child : registry.getChildren()){
            node.add(createTree(child));
        }
        
        regByNode.put(node, registry);
        return node;
    }
    
    private void updateTree(){
        tree.setModel(new DefaultTreeModel(createTree(JMapEditor.getRegistry())));
    }
}
