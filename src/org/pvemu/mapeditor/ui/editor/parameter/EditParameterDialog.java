/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.editor.parameter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.handler.setting.BasicParameter;
import org.pvemu.mapeditor.handler.setting.ParameterType;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditParameterDialog extends JDialog{
    final private JTextField nameField = new JTextField(12);
    final private JComboBox<ParameterType> typeField = new JComboBox<>(ParameterType.values());
    final private JTextField valueField = new JTextField(32);
    
    private String name;
    private ParameterType type;
    private Object value;
    
    final static public int CANCEL_OPTION = 0;
    final static public int SAVE_OPTION   = 1;
    
    private int currentOption = CANCEL_OPTION;

    EditParameterDialog(Frame owner) {
        this(owner, null, null, null);
    }

    public EditParameterDialog(Frame owner, BasicParameter param) {
        this(owner, param.getName(), param.getType(), param.getValue());
    }

    EditParameterDialog(Frame owner, String name, ParameterType type, Object value) {
        super(owner, "Ajouter un paramètre", true);
        
        this.name = name;
        this.type = type;
        this.value = value;
        
        JPanel panel = new JPanel(new BorderLayout());
        
        if(name != null){
            nameField.setText(name);
            nameField.setEditable(false);
        }
        
        if(type != null)
            typeField.setSelectedItem(type);
        
        if(value != null)
            valueField.setText(value.toString());
        
        panel.add(nameField, BorderLayout.WEST);
        panel.add(typeField, BorderLayout.CENTER);
        panel.add(valueField, BorderLayout.EAST);
        
        JButton save = new JButton("Enregistrer");
        save.addActionListener((e) -> save());
        
        JButton cancel = new JButton("Annuler");
        cancel.addActionListener((e) -> cancel());
        
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(save);
        buttons.add(cancel);
        panel.add(buttons, BorderLayout.SOUTH);
        
        setContentPane(panel);
        pack();
        setLocationRelativeTo(owner);
    }
    
    public int edit(){
        setVisible(true);
        return currentOption;
    }
    
    private void save(){
        try{
            ParameterType nType = (ParameterType) typeField.getSelectedItem();
            Object nValue = nType.getValue(valueField.getText().trim());
            type = nType;
            value = nValue;
            name = nameField.getText().trim();
            currentOption = SAVE_OPTION;
            dispose();
        }catch(IllegalArgumentException e){
            JMapEditor.getErrorHandler().showError(this, "Edition du paramètre : erreur", "Valeur invalide");
        }catch(Exception e){
            JMapEditor.getErrorHandler().showError(this, "Edition du paramètre : erreur", e);
        }
    }
    
    private void cancel(){
        currentOption = CANCEL_OPTION;
        dispose();
    }

    public int getCurrentOption() {
        return currentOption;
    }
    
    public String getParamName(){
        return name;
    }
    
    public ParameterType getParamType(){
        return type;
    }
    
    public Object getParamValue(){
        return value;
    }
}
