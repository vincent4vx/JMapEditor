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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.pvemu.mapeditor.action.OpenMap;
import org.pvemu.mapeditor.common.Constants;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class NewMapDialog extends JDialog{
    final private JTextField width = new JTextField("" + Constants.DEFAULT_WIDTH);
    final private JTextField height = new JTextField("" + Constants.DEFAULT_HEIGHT);

    public NewMapDialog(MainWindow parent) throws HeadlessException {
        super(parent, "Nouvelle map");
        setModal(true);        
        makePanel();
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    
    private void makePanel(){
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel opts = new JPanel(new GridLayout(0, 2));
        opts.add(new JLabel("Hauteur"));
        opts.add(height);
        opts.add(new JLabel("Largeur"));
        opts.add(width);
        panel.add(opts, BorderLayout.CENTER);
        
        JPanel buttons = new JPanel(new FlowLayout());
        
        JButton ok = new JButton("Ok");
        ok.addActionListener((e) -> {
            OpenMap.newMap(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
            setVisible(false);
            dispose();
        });
        
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
