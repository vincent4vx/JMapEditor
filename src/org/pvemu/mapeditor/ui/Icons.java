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
    final static public Icon SELECT = new ImageIcon(JMapEditor.getParameters().getString("SELECT_ICON"));
    final static public Icon REMOVE = new ImageIcon(JMapEditor.getParameters().getString("REMOVE_ICON"));
    final static public Icon ADD    = new ImageIcon(JMapEditor.getParameters().getString("ADD_ICON"));
    final static public Icon ROTATE = new ImageIcon(JMapEditor.getParameters().getString("ROTATE_ICON"));
    final static public Icon STATE  = new ImageIcon(JMapEditor.getParameters().getString("STATE_ICON"));
}
