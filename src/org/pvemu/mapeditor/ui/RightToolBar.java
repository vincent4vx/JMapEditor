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
package org.pvemu.mapeditor.ui;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RightToolBar extends JToolBar{
    /*private ObjectTab objectTab = null;
    final private ToolsTab editTab = new ToolsTab();
    final private BackgroundTab backgroundTab = new BackgroundTab();
    final private JTabbedPane tab = new JTabbedPane();*/
    
    final private JTabbedPane tabbedPane = new JTabbedPane();

    public RightToolBar() {
        super(VERTICAL);
        setPreferredSize(new Dimension(200, getPreferredSize().height));
        /*tab.addTab("Edition", editTab);
        tab.addTab("Fond", backgroundTab);
        
        add(tab);
        add(new LayerManager());*/
    }
    
    /*public void removeObjectTab(){
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
    public ToolsTab getEditTab() {
    return editTab;
    }
    public BackgroundTab getBackgroundTab() {
    return backgroundTab;
    }*/
    
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
