/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui.tileselector;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TileSelector extends JToolBar{

    public TileSelector() {
        TileListUI tileListUI = new TileListUI();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new FolderSelector(tileListUI), BorderLayout.WEST);
        panel.add(tileListUI, BorderLayout.CENTER);
        add(panel);
    }
    
}
