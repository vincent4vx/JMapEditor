/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.action.OpenMap;
import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MainMenuBar extends JMenuBar{

    public MainMenuBar() {
        add(file());
        add(map());
    }
    
    private JMenu file(){
        JMenu file = new JMenu("Fichier");
        file.setMnemonic('F');
        
        JMenuItem newMap = new JMenuItem("Nouvelle map");
        newMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newMap.addActionListener((e) -> new NewMapDialog());
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
    
    private JMenu map(){
        JMenu map = new JMenu("Map");
        map.setMnemonic('M');
        
        JMenuItem export = new JMenuItem("Exporter");
        export.addActionListener((e) -> export());
        map.add(export);
        
        return map;
    }
    
    private void open(){
        try{
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(JMapEditor.getUI());
            
            File file = chooser.getSelectedFile();
            
            if(file == null)
                return;
            
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

        try {
            JMapEditor.getExportHandler().export(handler.getMap());
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
