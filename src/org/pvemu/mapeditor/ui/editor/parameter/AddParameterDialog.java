/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.editor.parameter;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.db.model.JMEParameter;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class AddParameterDialog extends JDialog{
    final private JTextField name = new JTextField(12);
    final private JComboBox<JMEParameter.ParameterType> type = new JComboBox<>(JMEParameter.ParameterType.values());
    final private JTextField value = new JTextField(32);

    public AddParameterDialog(Frame owner) {
        super(owner, "Ajouter un paramètre", true);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(name, BorderLayout.WEST);
        panel.add(type, BorderLayout.CENTER);
        panel.add(value, BorderLayout.EAST);
        
        JButton save = new JButton("Enregistrer");
        save.addActionListener((e) -> save());
        panel.add(save, BorderLayout.SOUTH);
        
        setContentPane(panel);
        pack();
        setLocationRelativeTo(owner);
        setVisible(true);
    }
    
    private void save(){
        JMEParameter.ParameterType pType = (JMEParameter.ParameterType)type.getSelectedItem();
        String pName = name.getText();
        
        try{
            Object pValue = pType.getValue(value.getText());
            JMapEditor.getParametersHandler().setParameter(pName, pType, pValue);
            dispose();
        }catch(IllegalArgumentException ex){
            JMapEditor.getErrorHandler().showError(this, "Ajouter un paramètre : erreur", "Valeur '" + value.getText() + "' incorrect pour le type " + pType);
        }catch(Exception ex){
            JMapEditor.getErrorHandler().showError(this, pName, ex);
        }
    }
}
