package org.pvemu.mapeditor.base.ui.builder;

import org.pvemu.mapeditor.ui.ToolsTab;
import org.pvemu.mapeditor.common.ui.Titleable;
import org.pvemu.mapeditor.common.ui.UIBuilderContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ToolsTabBuilder extends UIBuilderContainer<ToolsTab, ToolsGroupBuilder> implements Titleable{

    @Override
    public String getTitle() {
        return "Outils";
    }

    @Override
    protected ToolsTab buildContainerUI() {
        return new ToolsTab();
    }

    @Override
    public String getName() {
        return "TOOLS_TAB";
    }
    
}
