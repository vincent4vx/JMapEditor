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
package org.pvemu.mapeditor.base.editor.change;

import java.util.HashMap;
import java.util.Map;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.base.editor.change.data.Change;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangesHandler {
    final private Map<String, ChangeAction> actions = new HashMap<>();
    
    public void registerChangeAction(ChangeAction action){
        actions.put(action.name(), action);
    }
    
    public ChangeAction getChangeAction(String name){
        return actions.get(name);
    }
    
    public boolean doChange(Editor editor, Change change){
        ChangeAction action = actions.get(change.getAction());
        
        if(action == null){
            System.err.println("ChangeAction not found : " + change.getAction());
            return false;
        }
        
        return action.perform(editor, change);
    }
    
    public boolean undoChange(Editor editor, Change change){
        ChangeAction action = actions.get(change.getAction());
        
        if(action == null){
            System.err.println("ChangeAction not found : " + change.getAction());
            return false;
        }
        
        return action.undo(editor, change);
    }
}
