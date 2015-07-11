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

import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.base.editor.change.data.Change;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeList {
    final private Editor editor;
    final private List<Change> changes;
    private int current;

    public ChangeList(Editor editor, List<Change> changes) {
        this.editor = editor;
        this.changes = changes;
        current = changes.size() - 1;
    }

    public ChangeList(Editor editor) {
        this.editor = editor;
        changes = new ArrayList<>();
        current = -1;
    }
    
    public void addChange(Change change){
        if(!editor.getHandler().getChangesHandler().doChange(editor, change)){
            return;
        }
        
        ++current;
        
        if(current == changes.size())
            changes.add(change);
        else{
            List<Change> tmp = changes.subList(0, current);
            tmp.add(change);
            changes.clear();
            changes.addAll(tmp);
        }
        
        editor.getHandler().change(editor);
    }
    
    public void undo(){
        if(current <= 0)
            return;
        
        --current;
        Change change = changes.get(current);
        
        if(!editor.getHandler().getChangesHandler().undoChange(editor, change)){
            return;
        }
        
        editor.getHandler().change(editor);
    }
    
    public void redo(){
        if(current >= changes.size() || current < 0)
            return;
        
        Change change = changes.get(current);
        
        if(!editor.getHandler().getChangesHandler().doChange(editor, change)){
            return;
        }
        
        editor.getHandler().change(editor);
        ++current;
    }
}
