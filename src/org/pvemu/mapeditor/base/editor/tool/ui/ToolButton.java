/* 
 * Copyright (C) 2014 Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pvemu.mapeditor.base.editor.tool.ui;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JToggleButton;
import org.pvemu.mapeditor.JMEApp;
import org.pvemu.mapeditor.base.editor.tool.Tool;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ToolButton extends JToggleButton{

    public ToolButton(JMEApp app, Tool tool, Icon icon, String toolTip) {
        super(icon);
        setPreferredSize(new Dimension(20, 20));
        setToolTipText(toolTip);
        addActionListener((e) -> app.getToolsHandler().setTool(tool));
    }
    
}
