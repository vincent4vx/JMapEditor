package org.pvemu.mapeditor.base.ui.builder;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.pvemu.mapeditor.common.ui.Titleable;
import org.pvemu.mapeditor.common.ui.UIBuilderComponent;
import org.pvemu.mapeditor.common.ui.UIBuilderContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract public class ToolsGroupBuilder extends UIBuilderContainer<JPanel, UIBuilderComponent> implements Titleable{

    @Override
    protected JPanel buildContainerUI() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), getTitle()));
        return panel;
    }
    
}
