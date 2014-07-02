/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.editor.parameter;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.pvemu.mapeditor.action.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SettingsDialog extends JFrame{

    public SettingsDialog() throws HeadlessException {
        super("Paramètres");
        
        JButton parameters = new JButton("Paramètres avancés");
        parameters.addActionListener((e) -> new ParametersEditor().setVisible(true));
        add(parameters, BorderLayout.EAST);
        
        JButton registry = new JButton("Registre");
        registry.addActionListener((e) -> new RegistryEditor().setVisible(true));
        add(registry, BorderLayout.WEST);
        
        pack();
        setLocationRelativeTo(JMapEditor.getUI());
    }
    
}
