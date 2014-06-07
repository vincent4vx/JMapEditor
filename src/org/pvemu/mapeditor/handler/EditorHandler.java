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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.XMLUtils;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.ui.editor.MapEditorUI;

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
    
    public EditorHandler(MapData map, File file){
        this.map = map;
        this.file = file;
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
    
    public void save() throws IOException, ParserConfigurationException, TransformerException, Exception{
        if(!changed)
            return;
        
        if(file == null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(Constants.JME_EXT);
                }

                @Override
                public String getDescription() {
                    return "Fichier de sauvegarde JMapEditor";
                }
            });
            
            int r = fileChooser.showSaveDialog(ui);
            
            if(r == JFileChooser.CANCEL_OPTION){
                return;
            }
            
            if(r == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                
                if(file.getName().split("\\.").length < 2){
                    file = new File(file.getAbsolutePath() + Constants.JME_EXT);
                }
                
                if(file.isDirectory()){
                    throw new Exception(file.getName() + " is a folder !");
                }
                
                if(file.exists()){
                    r = JOptionPane.showConfirmDialog(JMapEditor.getUI(), "Ce fichier existe déjà. Voulez-vous l'écraser ?");
                    
                    if(r == JOptionPane.NO_OPTION){
                        file = null;
                        save(); //show new filechooser
                        return;
                    }
                    
                    if(r == JOptionPane.CANCEL_OPTION){
                        file = null;
                        return; //quit saving
                    }
                }
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
