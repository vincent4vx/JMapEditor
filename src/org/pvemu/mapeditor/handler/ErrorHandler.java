/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import java.awt.Component;
import javax.swing.JOptionPane;
import org.pvemu.mapeditor.action.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ErrorHandler {
    public void showError(String title, Throwable cause){
        showError(JMapEditor.getUI(), title, cause);
    }
    
    public void showError(Component component, String title, Throwable cause){
        showError(component, title, cause.getMessage());
        cause.printStackTrace();
    }
    
    public void showError(String title, String message){
        showError(JMapEditor.getUI(), title, message);
    }
    
    public void showError(Component component, String title, String message){
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
