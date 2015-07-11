package org.pvemu.mapeditor.base.ui.builder;

import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import org.pvemu.mapeditor.JMEApp;
import org.pvemu.mapeditor.ui.JMEUI;
import org.pvemu.mapeditor.common.ui.UIBuilderContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class BaseUIBuilder extends UIBuilderContainer<JMEUI, UIBuilderContainer>{
    final private JMEApp app;
    
    final private BaseMenuBarBuilder menuBarBuilder = new BaseMenuBarBuilder();
    final private RightToolBarBuilder rightToolBarBuilder = new RightToolBarBuilder();
    final private TopToolBarBuilder topToolBarBuilder = new TopToolBarBuilder();
    final private JDesktopPane desktopPane = new JDesktopPane();

    public BaseUIBuilder(JMEApp app) {
        this.app = app;
        
        add(rightToolBarBuilder);
        add(topToolBarBuilder);
    }

    public BaseMenuBarBuilder getMenuBarBuilder() {
        return menuBarBuilder;
    }

    public RightToolBarBuilder getRightToolBarBuilder() {
        return rightToolBarBuilder;
    }

    public TopToolBarBuilder getTopToolBarBuilder() {
        return topToolBarBuilder;
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    @Override
    protected JMEUI buildContainerUI() {
        JMEUI ui = new JMEUI();
        ui.setJMenuBar(menuBarBuilder.getUI());
        ui.add(desktopPane, BorderLayout.CENTER);
        return ui;
    }

    @Override
    public String getName() {
        return "BASE_UI";
    }
}
