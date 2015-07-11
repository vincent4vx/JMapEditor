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

import java.util.HashMap;
import java.util.Map;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.pvemu.mapeditor.base.editor.data.MapData;
import org.pvemu.mapeditor.base.editor.change.ChangeList;
import org.pvemu.mapeditor.base.editor.ui.EditorUI;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class Editor {
    private EditorsHandler handler = null;
    
    final private MapData map;
    final private EditorUI ui;
    final private ChangeList changeHandler = new ChangeList(this);
    
    final private Map<Object, Object> attachments = new HashMap<>();

    public Editor(MapData map) {
        this.map = map;
        ui = new EditorUI(this);
        
        ui.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                close();
            }
            
        });
    }
    
    private void close(){
        if(handler == null)
            throw new Error("The editor is not handled by an EditorHandler");
        
        handler.close(this);
        handler = null;
    }
    
    void setHandler(EditorsHandler handler){
        this.handler = handler;
    }

    public EditorsHandler getHandler() {
        return handler;
    }
    
    public void attach(Object key, Object value){
        attachments.put(key, value);
    }
    
    public Object getAttachment(Object key){
        return attachments.get(key);
    }
    
    public Object removeAttachment(Object key){
        return attachments.get(key);
    }

    public MapData getMap() {
        return map;
    }

    public EditorUI getUI() {
        if(handler == null)
            throw new Error("Try to get UI on a non open Editor");
        
        return ui;
    }
    
    public void refreshGrid(){
        ui.getGrid().repaint();
    }
    
    public void reloadCells(){
        ui.getGrid().reloadShapes();
        ui.getGrid().repaint();
    }

    public ChangeList getChangeHandler() {
        return changeHandler;
    }
}
