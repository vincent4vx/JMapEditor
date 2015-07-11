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
package org.pvemu.mapeditor.module.saving;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.base.editor.data.MapData;
import org.pvemu.mapeditor.base.ui.builder.BaseMenuBarBuilder;
import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.common.Module;
import org.pvemu.mapeditor.tile.Tile;
import org.pvemu.mapeditor.module.saving.data.EditorSavingState;
import org.pvemu.mapeditor.module.saving.data.MapHistory;
import org.pvemu.mapeditor.module.saving.data.MapInfo;
import org.pvemu.mapeditor.module.saving.ui.JMEFileChooser;
import org.pvemu.mapeditor.module.saving.ui.builder.OpenMenuItemBuilder;
import org.pvemu.mapeditor.module.saving.ui.builder.SaveMenuItemBuilder;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SavingModule implements Module{
    @Override
    public String moduleName() {
        return "SAVING";
    }

    @Override
    public void configure() {
        JMapEditor.APP.getEditorsHandler().addListener(new SavingEditorListener(this));
    }

    @Override
    public void initUI() {
        BaseMenuBarBuilder.MenuBuilder menu = JMapEditor.APP.getBaseUIBuilder().getMenuBarBuilder().get(BaseMenuBarBuilder.BaseMenu.FILE);
        menu.add(new OpenMenuItemBuilder(this));
        menu.add(new SaveMenuItemBuilder(this));
    }
    
    public void open(){
        JFileChooser chooser = new JMEFileChooser();
        
        int r = chooser.showOpenDialog(JMapEditor.APP.getUI());
        
        if(r != JFileChooser.APPROVE_OPTION)
            return;
        
        try{
            EditorSavingState state = new EditorSavingState(chooser.getSelectedFile().getAbsolutePath());
            MapInfo info = state.getInfoDAO().load();
            MapHistory history = state.getHistoryDAO().getLast();

            Tile background = history.getBackground() == 0 ? null : JMapEditor.APP.getTilesHandler().getBackgrounds().getTileById(history.getBackground());

            MapData map = new MapData(
                    info.getId(), 
                    info.getWidth(), 
                    info.getHeight(), 
                    background, 
                    Compressor.decompressMapData(history.getCells())
            );

            Editor editor = new Editor(map);
            editor.attach(this, state);
            JMapEditor.APP.getEditorsHandler().open(editor);
        }catch(Exception e){
            JMapEditor.APP.getErrorHandler().showError("Ouvrir : erreur", e);
            return;
        }
    }
    
    public void save(){
        Editor editor = JMapEditor.APP.getEditorsHandler().getCurrent();
        
        if(editor == null){
            JMapEditor.APP.getErrorHandler().showError("Enregister", "Veuillez sélectionner la carte à enregistrer.");
            return;
        }
        
        save(editor);
    }
    
    public void save(Editor editor){
        EditorSavingState state = (EditorSavingState) editor.getAttachment(this);
        MapData map = editor.getMap();
        
        if(state == null){
            File file;
            JFileChooser fileChooser = new JMEFileChooser();
            
            int r = fileChooser.showSaveDialog(editor.getUI());
            
            if(r == JFileChooser.CANCEL_OPTION){
                return;
            }
            
            if(r == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                
                if(file.exists()){
                    r = JOptionPane.showConfirmDialog(JMapEditor.APP.getUI(), "Ce fichier existe déjà. Voulez-vous l'écraser ?");
                    
                    if(r == JOptionPane.NO_OPTION){
                        save(editor); //show new filechooser
                        return;
                    }
                    
                    if(r == JOptionPane.CANCEL_OPTION){
                        return; //quit saving
                    }
                    
                    if(!file.delete())
                        throw new RuntimeException("Cannot delete the file '" + file.getName() + "' !");
                }
                
                try{
                    //creating db structure
                    state = new EditorSavingState(file.getAbsolutePath());
                    MapInfo info = new MapInfo(map.getId(), map.getWidth(), map.getHeight());
                    state.setInfo(info);
                    state.getInfoDAO().create(info);
                    state.getHistoryDAO().createTable();
                    editor.attach(this, state);
                }catch(Exception e){
                    JMapEditor.APP.getErrorHandler().showError("Enregister : erreur", e);
                    return;
                }
            }
        }
        
        
        MapHistory history = new MapHistory(
                System.currentTimeMillis(), 
                map.getBackground() == null ? 0 : map.getBackground().getId(), 
                0, 
                Compressor.compressMapData(map)
        );
        
        try{
            state.getInfo().setLastDate(history.getDate());
            state.getHistoryDAO().add(history);
            state.getInfoDAO().update(state.getInfo());
        }catch(Exception e){
            JMapEditor.APP.getErrorHandler().showError("Enregister : erreur", e);
            return;
        }
        
        state.setChanged(false);
        editor.getUI().setTitle(state.getFile());
    }
}
