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
package org.pvemu.mapeditor.base.editor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JInternalFrame;
import org.pvemu.mapeditor.base.editor.change.ChangesHandler;
import org.pvemu.mapeditor.base.editor.ui.EditorUI;
import org.pvemu.mapeditor.JMEApp;
import org.pvemu.mapeditor.base.object.displayer.LayersDisplayer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditorsHandler {
    static public interface EditorAction{
        public void perform(Editor editor);
    }
    
    final private JMEApp app;
    final private List<EditorListener> listeners = new ArrayList<>();
    final private LayersDisplayer layersDisplayer = new LayersDisplayer();
    final private ChangesHandler changesHandler = new ChangesHandler();

    public EditorsHandler(JMEApp app) {
        this.app = app;
    }
    
    public void addListener(EditorListener listener){
        listeners.add(listener);
    }
    
    public void removeListener(EditorListener listener){
        listeners.remove(listener);
    }

    List<EditorListener> getListeners() {
        return listeners;
    }
    
    public void open(Editor editor){
        editor.setHandler(this);
        
        for(EditorListener listener : listeners){
            if(!listener.onOpen(editor)){
                editor.setHandler(null);
                return;
            }
        }
        
        app.getBaseUIBuilder().getDesktopPane().add(editor.getUI());
        editor.getUI().setVisible(true);
    }
    
    public void close(Editor editor){
        for(EditorListener listener : listeners){
            if(!listener.onClose(editor))
                return;
        }
        
        editor.getUI().dispose();
        app.getBaseUIBuilder().getDesktopPane().remove(editor.getUI());
    }
    
    public void change(Editor editor){
        for(EditorListener listener : listeners){
            if(!listener.onChange(editor))
                return;
        }
        
        editor.refreshGrid();
    }
    
    public Editor getCurrent(){
        JInternalFrame frame = app.getBaseUIBuilder().getDesktopPane().getSelectedFrame();
        
        if(frame == null || !(frame instanceof EditorUI))
            return null;
        
        return ((EditorUI)frame).getEditor();
    }

    public LayersDisplayer getLayersDisplayer() {
        return layersDisplayer;
    }

    public ChangesHandler getChangesHandler() {
        return changesHandler;
    }
    
    public void actionOnCurrent(EditorAction action){
        Editor editor = getCurrent();
        
        if(editor == null)
            return;
        
        action.perform(editor);
    }
}
