/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.editor.parameter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.handler.ParametersHandler.ParameterType;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class AdvancedParameters extends JFrame{
    final private JTable table;

    AdvancedParameters() throws HeadlessException {
        super("Paramètres avancés");
        table = new JTable(JMapEditor.getParametersHandler());
        makeContent();
        pack();
        setLocationRelativeTo(JMapEditor.getUI());
    }
    
    private void makeContent(){
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel txt = new JLabel("<html><body><b>Attention:</b> les paramètres ne sont pas vérifiés avant d'être modifiés, et peuvent donc être invalide.<br/>Ceci peut causer des instabilités, ou des erreurs dans le logiciel. Ne modifiez que si vous êtes sûr de ce que vous faites !</body></html>");
        txt.setForeground(Color.red);
        txt.setFont(txt.getFont().deriveFont(Font.PLAIN));
        panel.add(txt, BorderLayout.NORTH);
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(table);
        table.setDefaultEditor(ParameterType.class, new DefaultCellEditor(new JComboBox<>(ParameterType.values())));
        
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel buttons = new JPanel(new FlowLayout());
        JButton add = new JButton("Ajouter");
        buttons.add(add);
        add.addActionListener((e) -> add());
        
        JButton remove = new JButton("Supprimer");
        remove.addActionListener((e) -> remove());
        buttons.add(remove);
        
        JButton close = new JButton("Fermer");
        close.addActionListener((e) -> dispose());
        buttons.add(close);
        panel.add(buttons, BorderLayout.SOUTH);
        
        setContentPane(panel);
    }
    
    private void add(){
        EditParameterDialog dialog = new EditParameterDialog(this, null, null, null);
        int r = dialog.edit();
        
        if(r == EditParameterDialog.CANCEL_OPTION)
            return;
        
        String name = dialog.getParamName();
        ParameterType type = dialog.getParamType();
        Object value = dialog.getParamValue();
        
        try{
            JMapEditor.getParametersHandler().setParameter(name, type, value);
        }catch(Exception e){
            JMapEditor.getErrorHandler().showError(this, "Ajouter un paramètre : erreur", e);
        }
    }
    
    private void remove(){
        int row = table.getSelectedRow();
        
        if(row == -1)
            return;
        
        try{
            JMapEditor.getParametersHandler().removeParameterRow(row);
        }catch(Exception e){
            JMapEditor.getErrorHandler().showError(this, "Suppression d'un paramètre : erreur", e);
        }
    }
    
}
