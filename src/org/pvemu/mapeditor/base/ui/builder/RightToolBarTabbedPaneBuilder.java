package org.pvemu.mapeditor.base.ui.builder;

import org.pvemu.mapeditor.ui.RightToolBarTabbedPane;
import org.pvemu.mapeditor.common.ui.TabbedPaneBuilder;
import org.pvemu.mapeditor.common.ui.Titleable;
import org.pvemu.mapeditor.common.ui.UIBuilderComponent;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RightToolBarTabbedPaneBuilder<C extends UIBuilderComponent & Titleable> extends TabbedPaneBuilder<RightToolBarTabbedPane, C>{
    final private ToolsTabBuilder toolsTabBuilder = new ToolsTabBuilder();

    @Override
    protected RightToolBarTabbedPane buildContainerUI() {
        RightToolBarTabbedPane c = new RightToolBarTabbedPane();
        c.addTab(toolsTabBuilder.getTitle(), toolsTabBuilder.getUI());
        return c;
    }

    @Override
    public String getName() {
        return "RIGHT_TOOLBAR_TABBED_PANE";
    }

    public ToolsTabBuilder getToolsTabBuilder() {
        return toolsTabBuilder;
    }
    
}
