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
package org.pvemu.mapeditor.module.newmap.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.module.newmap.NewMapModuleConfigSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class NewMapDialog extends JDialog{
    final private JSpinner id = new JSpinner();
    final private JSpinner width = new JSpinner();
    final private JSpinner height = new JSpinner();
    
    final static public int OK_OPTION     = 0;
    final static public int CANCEL_OPTION = 1;
    
    private int option = CANCEL_OPTION;

    public NewMapDialog(NewMapModuleConfigSet configSet) throws HeadlessException {
        super(JMapEditor.getUI(), "Nouvelle map");
        
        id.setValue(configSet.LAST_MAP_ID.getValue() + 1);
        width.setValue(configSet.DEFAULT_MAP_WIDTH.getValue());
        height.setValue(configSet.DEFAULT_MAP_HEIGHT.getValue());
        
        setModal(true);        
        makePanel();
        pack();
        setResizable(false);
        setLocationRelativeTo(JMapEditor.getUI());
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
            option = OK_OPTION;
            dispose();
        });
        getRootPane().setDefaultButton(ok);
        ok.requestFocus();
        
        JButton cancel  = new JButton("Annuler");
        cancel.addActionListener((e) -> {
            option = CANCEL_OPTION;
            dispose();
        });
        
        buttons.add(ok);
        buttons.add(cancel);
        panel.add(buttons, BorderLayout.SOUTH);
        
        setContentPane(panel);
    }
    
    public int getMapId(){
        return (int)id.getValue();
    }
    
    public int getMapWidth(){
        return (int)width.getValue();
    }
    
    public int getMapHeight(){
        return (int)height.getValue();
    }

    public int getOption() {
        return option;
    }
}
