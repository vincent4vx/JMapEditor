/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.pvemu.mapeditor.action.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class Icons {
    final static public Icon SELECT = new ImageIcon(JMapEditor.getParametersHandler().getString("SELECT_ICON"));
    final static public Icon REMOVE = new ImageIcon(JMapEditor.getParametersHandler().getString("REMOVE_ICON"));
    final static public Icon ADD    = new ImageIcon(JMapEditor.getParametersHandler().getString("ADD_ICON"));
    final static public Icon ROTATE = new ImageIcon(JMapEditor.getParametersHandler().getString("ROTATE_ICON"));
}
