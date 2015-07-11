package org.pvemu.mapeditor.module.changebackground.ui.builder;

import org.pvemu.mapeditor.common.ui.Titleable;
import org.pvemu.mapeditor.common.ui.UIBuilderComponent;
import org.pvemu.mapeditor.module.changebackground.ChangeBackgroundModule;
import org.pvemu.mapeditor.module.changebackground.ui.ChangeBackgroundTab;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeBackgroundTabBuilder implements UIBuilderComponent<ChangeBackgroundTab>, Titleable{
    final private ChangeBackgroundModule module;

    public ChangeBackgroundTabBuilder(ChangeBackgroundModule module) {
        this.module = module;
    }

    @Override
    public ChangeBackgroundTab getUI() {
        return new ChangeBackgroundTab(module);
    }

    @Override
    public String getName() {
        return "CHANGE_BACKGROUND_TAB";
    }

    @Override
    public String getTitle() {
        return "Fond";
    }
    
}
