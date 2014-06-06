/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RightMenu extends JToolBar{
    private ObjectTab objectTab = null;
    final private EditTab editTab = new EditTab();
    final private BackgroundTab backgroundTab = new BackgroundTab();
    final private JTabbedPane tab = new JTabbedPane();

    public RightMenu() {
        super(VERTICAL);
        setPreferredSize(new Dimension(200, getPreferredSize().height));
        tab.addTab("Edition", editTab);
        tab.addTab("Fond", backgroundTab);
        
        add(tab);
        add(new LayerManager());
    }
    
    public void removeObjectTab(){
        if(objectTab != null){
            objectTab.onClose();
            tab.remove(objectTab);
            tab.setSelectedComponent(editTab);
        }
        objectTab = null;
    }
    
    public void setObjectTab(ObjectTab objectTab){
        removeObjectTab();
        this.objectTab = objectTab;
        tab.add("Objet", objectTab);
        tab.setSelectedComponent(objectTab);
    }

    public EditTab getEditTab() {
        return editTab;
    }

    public BackgroundTab getBackgroundTab() {
        return backgroundTab;
    }
}
