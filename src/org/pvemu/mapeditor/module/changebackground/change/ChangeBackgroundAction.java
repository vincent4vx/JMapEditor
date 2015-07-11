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
package org.pvemu.mapeditor.module.changebackground.change;

import org.pvemu.mapeditor.base.editor.change.ChangeAction;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.base.editor.change.data.Change;
import org.pvemu.mapeditor.tile.Tile;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeBackgroundAction implements ChangeAction{
    final static public int BACKGROUND_ID_PARAM = 0;
    
    final static public String ACTION_NAME = "CHANGE_BACKGROUND";

    @Override
    public String name() {
        return ACTION_NAME;
    }

    @Override
    public boolean perform(Editor editor, Change change) {
        Tile bg = JMapEditor.APP.getTilesHandler().getBackgrounds().getTileById(change.getParam(BACKGROUND_ID_PARAM));
        editor.getMap().setBackground(bg);
        return true;
    }

    @Override
    public boolean undo(Editor editor, Change change) {
        Tile bg;
        
        try{
            bg = JMapEditor.APP.getTilesHandler().getBackgrounds().getTileById(Integer.parseInt(change.getLastState()));
        }catch(Exception e){
            return false;
        }
        
        editor.getMap().setBackground(bg);
        return true;
    }

    @Override
    public String label(Change change) {
        return "Background : " + change.getParam(BACKGROUND_ID_PARAM);
    }
    
}
