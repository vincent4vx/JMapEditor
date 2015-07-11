package org.pvemu.mapeditor.base.ui.builder;

import java.awt.BorderLayout;
import org.pvemu.mapeditor.ui.RightToolBar;
import org.pvemu.mapeditor.common.ui.UIBuilderComponent;
import org.pvemu.mapeditor.common.ui.UIBuilderContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RightToolBarBuilder extends UIBuilderContainer<RightToolBar, UIBuilderComponent>{
    final private RightToolBarTabbedPaneBuilder tabbedPaneBuilder = new RightToolBarTabbedPaneBuilder();

    @Override
    protected RightToolBar buildContainerUI() {
        RightToolBar ui = new RightToolBar();
        ui.add(tabbedPaneBuilder.getUI());
        return ui;
    }

    @Override
    public String getName() {
        return "RIGHT_TOOL_BAR";
    }

    public RightToolBarTabbedPaneBuilder getTabbedPaneBuilder() {
        return tabbedPaneBuilder;
    }

    @Override
    public Object getConstraints() {
        return BorderLayout.EAST;
    }
    
}
