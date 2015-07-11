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
package org.pvemu.mapeditor.common;

import java.awt.Component;
import javax.swing.JOptionPane;
import org.pvemu.mapeditor.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ErrorHandler {
    public void showError(String title, Throwable cause){
        showError(JMapEditor.getUI(), title, cause);
    }
    
    public void showError(Component component, String title, Throwable cause){
        showError(component, title, cause.toString());
        cause.printStackTrace();
    }
    
    public void showError(String title, String message){
        showError(JMapEditor.getUI(), title, message);
    }
    
    public void showError(Component component, String title, String message){
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
