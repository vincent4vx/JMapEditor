/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.action;

import org.pvemu.mapeditor.ui.MainWindow;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMapEditor {
    final private static MainWindow ui = new MainWindow();
    
    public static void main(String[] args) {
        ui.setVisible(true);
    }

    public static MainWindow getUI() {
        return ui;
    }
}
