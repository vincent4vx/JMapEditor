/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.action.OpenMap;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class NewMapDialog extends JDialog{
    final private JSpinner id = new JSpinner();
    final private JSpinner width = new JSpinner();
    final private JSpinner height = new JSpinner();

    public NewMapDialog() throws HeadlessException, SQLException {
        super(JMapEditor.getUI(), "Nouvelle map");
        
        id.setValue(JMapEditor.getParameters().getIntDefault("LAST_MAP_ID", 13000) + 1);
        width.setValue(JMapEditor.getParameters().getInt("DEFAULT_WIDTH"));
        height.setValue(JMapEditor.getParameters().getInt("DEFAULT_HEIGHT"));
        
        setModal(true);        
        makePanel();
        pack();
        setResizable(false);
        setLocationRelativeTo(JMapEditor.getUI());
        setVisible(true);
    }
    
    private void makePanel(){
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel opts = new JPanel(new GridLayout(0, 2));
        opts.add(new JLabel("ID"));
        opts.add(id);
        opts.add(new JLabel("Hauteur"));
        opts.add(height);
        opts.add(new JLabel("Largeur"));
        opts.add(width);
        panel.add(opts, BorderLayout.CENTER);
        
        JPanel buttons = new JPanel(new FlowLayout());
        
        JButton ok = new JButton("Ok");
        ok.addActionListener((e) -> {
            OpenMap.newMap((Integer)id.getValue(), (Integer)width.getValue(), (Integer)height.getValue());
            try {
                JMapEditor.getParameters().setInt("LAST_MAP_ID", (Integer)id.getValue());
            } catch (Exception ex) {
                JMapEditor.getErrorHandler().showError("Création de la carte : erreur", ex);
            }
            setVisible(false);
            dispose();
        });
        getRootPane().setDefaultButton(ok);
        ok.requestFocus();
        
        JButton cancel  = new JButton("Annuler");
        cancel.addActionListener((e) -> {
            setVisible(false);
            dispose();
        });
        
        buttons.add(ok);
        buttons.add(cancel);
        panel.add(buttons, BorderLayout.SOUTH);
        
        setContentPane(panel);
    }
    
}
