/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.module.saving.ui.builder;

import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import org.pvemu.mapeditor.common.ui.UIBuilderComponent;
import org.pvemu.mapeditor.module.saving.SavingModule;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SaveMenuItemBuilder implements UIBuilderComponent<JMenuItem>{
    final private SavingModule module;

    public SaveMenuItemBuilder(SavingModule module) {
        this.module = module;
    }

    @Override
    public JMenuItem getUI() {
        JMenuItem ui = new JMenuItem("Enregister");
        ui.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        ui.addActionListener((e) -> module.save());
        return ui;
    }

    @Override
    public String getName() {
        return "SAVE_MENU_ITEM";
    }
    
}
