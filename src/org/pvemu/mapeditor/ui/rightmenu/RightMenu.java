/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.rightmenu;

import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RightMenu extends JToolBar{

    public RightMenu() {
        super(VERTICAL);
        JTabbedPane tab = new JTabbedPane();
        tab.addTab("Edition", new EditTab());
        tab.addTab("Fond", new BackgroundTab());
        
        add(tab);
        add(new LayerManager());
    }
    
}
