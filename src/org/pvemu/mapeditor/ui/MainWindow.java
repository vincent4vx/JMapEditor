/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.ui.rightmenu.RightMenu;
import org.pvemu.mapeditor.ui.tileselector.TileSelector;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MainWindow extends JFrame{
    final private JDesktopPane desktopPane = new JDesktopPane();

    public MainWindow() throws HeadlessException {
        super(Constants.TITLE);
        makeMenu();
        makePanel();
        setSize(1010, 650);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                onQuit();
            }
            
        });
        
        setLocationRelativeTo(null);
    }
    
    private void makePanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(desktopPane, BorderLayout.CENTER);
        panel.add(new RightMenu(), BorderLayout.EAST);
        panel.add(new TileSelector(), BorderLayout.SOUTH);
        setContentPane(panel);
    }
    
    private void onQuit(){
        int i = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter ?");
        
        if(i == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    
    private void makeMenu(){
        JMenuBar bar = new JMenuBar();
        
        JMenu file = new JMenu("Fichier");
        file.setMnemonic('F');
        
        JMenuItem newMap = new JMenuItem("Nouvelle map");
        newMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newMap.addActionListener((e) -> new NewMapDialog(this));
        file.add(newMap);
        
        JMenuItem save = new JMenuItem("Sauvegarder");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        save.addActionListener((e) -> {
            EditorHandler handler = EditorHandler.getCurrentHandler();
            
            if(handler == null){
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une map à sauvegarder", "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try{
                handler.save();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, ex, "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(System.err);
            }
        });
        file.add(save);
        
        bar.add(file);
        
        setJMenuBar(bar);
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }
}
