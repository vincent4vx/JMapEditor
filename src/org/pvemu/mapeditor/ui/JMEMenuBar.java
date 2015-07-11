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

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class JMEMenuBar extends JMenuBar{
    final private JMenu file;
    final private JMenu edit;
    final private JMenu map;

    public JMEMenuBar() {
        file = new JMenu("Fichier");
        addMenu(file, 'F');
        
        edit = new JMenu("Edition");
        addMenu(edit, 'E');
        
        map = new JMenu("Carte");
        addMenu(map, 'C');
    }
    
    public void addMenu(JMenu menu, char mnemonic){
        menu.setMnemonic(mnemonic);
        add(menu);
    }

    public JMenu getFile() {
        return file;
    }

    public JMenu getEdit() {
        return edit;
    }

    public JMenu getMap() {
        return map;
    }
    
}
