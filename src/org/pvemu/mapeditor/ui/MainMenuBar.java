/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.action.OpenMap;
import org.pvemu.mapeditor.common.JMEFileChooser;
import org.pvemu.mapeditor.data.db.model.MapHistory;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.MapDBHandler;
import org.pvemu.mapeditor.ui.editor.parameter.AdvancedParameters;
import org.pvemu.mapeditor.ui.editor.parameter.SettingsDialog;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MainMenuBar extends JMenuBar{

    public MainMenuBar() {
        add(file());
        add(edit());
        add(map());
    }
    
    private JMenu file(){
        JMenu file = new JMenu("Fichier");
        file.setMnemonic('F');
        
        JMenuItem newMap = new JMenuItem("Nouvelle map");
        newMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newMap.addActionListener((e) -> {
            try{
                new NewMapDialog();    
            }catch(SQLException ex){
                JMapEditor.getErrorHandler().showError("Nouvelle map : erreur", ex);
            }
        });
        file.add(newMap);
        
        JMenuItem open = new JMenuItem("Ouvrir");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        open.addActionListener((e) -> open());
        file.add(open);
        
        JMenuItem save = new JMenuItem("Sauvegarder");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        save.addActionListener((e) -> save());
        file.add(save);
        
        return file;
    }
    
    private JMenu edit(){
        JMenu edit = new JMenu("Edition");
        edit.setMnemonic('E');
        
        JMenuItem settings = new JMenuItem("Paramètres");
        edit.add(settings);
        settings.addActionListener((e) -> new SettingsDialog().setVisible(true));
        
        return edit;
    }
    
    private JMenu map(){
        JMenu map = new JMenu("Map");
        map.setMnemonic('M');
        
        JMenuItem export = new JMenuItem("Exporter");
        export.addActionListener((e) -> export());
        map.add(export);
        
        final JMenu versions = new JMenu("Versions");
        versions.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                try {
                    versions.removeAll();
                    
                    EditorHandler handler = EditorHandler.getCurrentHandler();
                    
                    if(handler == null)
                        return;
                    
                    MapDBHandler DBHandler = handler.getData();
                    
                    if(DBHandler == null)
                        return;
                    
                    for(MapHistory history : DBHandler.getHistoryDAO().getAll()){
                        JMenuItem item = new JMenuItem((new SimpleDateFormat("d/M/y H:m:s")).format(new Date(history.getDate())));
                        item.addActionListener((evt) -> handler.loadHistory(history));
                        versions.add(item);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(JMapEditor.getUI(), ex, "Error during loading history", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(System.err);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });
        map.add(versions);
        
        return map;
    }
    
    private void open(){
        try{
            JFileChooser chooser = JMEFileChooser.getFileChooser();
            
            if(chooser.showOpenDialog(JMapEditor.getUI()) == JFileChooser.CANCEL_OPTION)
                return;
            
            File file = chooser.getSelectedFile();
            OpenMap.loadMap(file.getAbsolutePath());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), "Erreur lors de l'ouverture : " + ex, "Ouverture : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }
    
    private void save(){
        EditorHandler handler = EditorHandler.getCurrentHandler();

        if(handler == null){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), "Veuillez sélectionner une map à sauvegarder", "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            handler.save();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), ex, "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }
    
    private void export(){
        EditorHandler handler = EditorHandler.getCurrentHandler();

        if(handler == null)
            return;

        /*try {
            JMapEditor.getExportHandler().export(handler.getMap());
        } catch (Exception ex){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), ex, "Exportation : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }*/
        
        new ExportDialog(handler);
    }
}
