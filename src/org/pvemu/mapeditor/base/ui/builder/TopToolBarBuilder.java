package org.pvemu.mapeditor.base.ui.builder;

import java.awt.BorderLayout;
import org.pvemu.mapeditor.ui.TopToolBar;
import org.pvemu.mapeditor.common.ui.UIBuilderComponent;
import org.pvemu.mapeditor.common.ui.UIBuilderContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TopToolBarBuilder extends UIBuilderContainer<TopToolBar, UIBuilderComponent>{

    @Override
    protected TopToolBar buildContainerUI() {
        return new TopToolBar();
    }

    @Override
    public String getName() {
        return "TOP_TOOLBAR";
    }

    @Override
    public Object getConstraints() {
        return BorderLayout.NORTH;
    }
    
}
