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
package org.pvemu.mapeditor.module.newmap;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.base.editor.data.MapData;
import org.pvemu.mapeditor.base.module.Module;
import org.pvemu.mapeditor.common.utils.Utils;
import org.pvemu.mapeditor.module.newmap.ui.NewMapDialog;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class NewMapModule implements Module{
    final private JMenuItem newMapItem;
    final private JButton newMapButton;
    final private NewMapModuleConfigSet configSet;

    public NewMapModule() {
        configSet = new NewMapModuleConfigSet();
        JMapEditor.getParameters().getConfig().handle(configSet);
        
        newMapItem = new JMenuItem("Nouvelle map");
        newMapButton = new JButton(new ImageIcon(configSet.NEW_MAP_ICON.getValue()));
    }

    @Override
    public void start() {
        newMapItem.addActionListener((e) -> newMap());
        JMapEditor.getUI().getJMEMenuBar().getFile().add(newMapItem);
        newMapItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        
        newMapButton.addActionListener((e) -> newMap());
        newMapButton.setToolTipText("Crée une nouvelle map");
        JMapEditor.getUI().getTopToolBar().add(newMapButton);
    }
    
    public void newMap(){
        NewMapDialog dialog = new NewMapDialog(configSet);
        dialog.setVisible(true);
        
        if(dialog.getOption() == NewMapDialog.CANCEL_OPTION)
            return;
        
        MapData data = new MapData(
                dialog.getMapId(), 
                dialog.getMapWidth(), 
                dialog.getMapHeight(), 
                null, 
                generateEmptyCells(dialog.getMapWidth(), dialog.getMapHeight())
        );
        
        configSet.LAST_MAP_ID.setValue(dialog.getMapId());
        JMapEditor.getParameters().getConfig().commitChanges();
        
        //Editor editor = new Editor(data);
        //JMapEditor.APP.getEditorsHandler().open(editor);
    }
    
    private Cell[] generateEmptyCells(int width, int height){
        Cell[] cells = new Cell[Utils.getCellNumberBySize(width, height)];
        
        for(int i = 0; i < cells.length; ++i){
            cells[i] = new Cell(i);
        }
        
        return cells;
    }

    public NewMapModuleConfigSet getConfigSet() {
        return configSet;
    }
}
