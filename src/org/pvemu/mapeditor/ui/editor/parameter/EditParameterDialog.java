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
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.param.registry.BasicParameter;
import org.pvemu.mapeditor.param.registry.ParameterType;

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
