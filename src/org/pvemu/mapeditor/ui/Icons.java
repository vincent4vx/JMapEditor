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
package org.pvemu.mapeditor.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.pvemu.mapeditor.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class Icons {
    final static public Icon SELECT  = new ImageIcon(JMapEditor.getParameters().getString("SELECT_ICON"));
    final static public Icon REMOVE  = new ImageIcon(JMapEditor.getParameters().getString("REMOVE_ICON"));
    final static public Icon ADD     = new ImageIcon(JMapEditor.getParameters().getString("ADD_ICON"));
    final static public Icon ROTATE  = new ImageIcon(JMapEditor.getParameters().getString("ROTATE_ICON"));
    final static public Icon STATE   = new ImageIcon(JMapEditor.getParameters().getString("STATE_ICON"));
    final static public Icon PREVIEW = new ImageIcon(JMapEditor.getParameters().getString("PREVIEW_ICON"));
}
