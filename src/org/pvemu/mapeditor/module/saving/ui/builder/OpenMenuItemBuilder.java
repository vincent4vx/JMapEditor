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
public class OpenMenuItemBuilder implements UIBuilderComponent<JMenuItem>{
    final private SavingModule module;

    public OpenMenuItemBuilder(SavingModule module) {
        this.module = module;
    }

    @Override
    public JMenuItem getUI() {
        JMenuItem item = new JMenuItem("Ouvrir");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        item.addActionListener((e) -> module.open());
        return item;
    }

    @Override
    public String getName() {
        return "OPEN_MENU_ITEM";
    }
    
}
