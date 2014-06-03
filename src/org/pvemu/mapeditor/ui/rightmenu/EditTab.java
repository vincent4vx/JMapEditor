/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditTab extends JPanel{

    public EditTab() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        tilesTools();
    }
    
    private void tilesTools(){  
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        panel.add(new JLabel("Actions"));
        
        JToggleButton select = new JToggleButton(new ImageIcon(Constants.UI_RESOURCES_DIR + "cursor.png"));
        select.setPreferredSize(new Dimension(20, 20));
        select.setSelected(true);
        select.setToolTipText("Sélectionner");
        select.addActionListener((e) -> JMapEditor.getToolsHandler().setSelectTool());
        panel.add(select);
        
        JToggleButton remove = new JToggleButton(new ImageIcon(Constants.UI_RESOURCES_DIR + "bin.png"));
        remove.setPreferredSize(new Dimension(20, 20));
        remove.setToolTipText("Supprimer");
        remove.addActionListener((e) -> JMapEditor.getToolsHandler().setRemoveTool());
        panel.add(remove);
        
        ButtonGroup group = new ButtonGroup();
        group.add(select);
        group.add(remove);
        
        add(panel);
    }
    
}
