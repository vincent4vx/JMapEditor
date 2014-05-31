/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.XMLUtils;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.ui.MapEditorUI;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class EditorHandler {
    final private MapData map;
    final private MapEditorUI ui;
    private File file = null;
    private boolean changed = false;

    public EditorHandler(MapData map) {
        changed = true;
        this.map = map;
        ui = new MapEditorUI(this);
        ui.setTitle(getTitle());
        ui.setVisible(true);
    }

    public MapData getMap() {
        return map;
    }

    public File getFile() {
        return file;
    }

    public boolean isChanged() {
        return changed;
    }
    
    public String getTitle(){
        return (file == null ? "Sans nom" : file.getName()) + " [" + map.getWidth() + "x" + map.getHeight() + "]" + (changed ? " * " : "");
    }
    
    public void update(){
        changed = true;
        ui.getGrid().repaint();
        ui.setTitle(getTitle());
    }

    public MapEditorUI getUI() {
        return ui;
    }
    
    public void save() throws IOException, ParserConfigurationException, TransformerException{
        if(!changed)
            return;
        
        if(file == null){
            JFileChooser fileChooser = new JFileChooser();
            int r = fileChooser.showSaveDialog(ui);
            
            if(r == JFileChooser.CANCEL_OPTION){
                return;
            }
            
            if(r == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
            }
        }
        
        XMLUtils.saveMapXML(map, file);
        changed = false;
        ui.setTitle(getTitle());
    }
    
    static public EditorHandler getCurrentHandler(){
        JInternalFrame frame = JMapEditor.getUI().getDesktopPane().getSelectedFrame();
        
        if(frame == null || !(frame instanceof MapEditorUI)){
            return null;
        }
        
        return ((MapEditorUI)frame).getHandler();
    }
}
