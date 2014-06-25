/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;
import org.pvemu.mapeditor.handler.layer.Layer;
import org.pvemu.mapeditor.ui.editor.MapEditorUI;
import org.pvemu.mapeditor.ui.rightmenu.RightMenu;
import org.pvemu.mapeditor.ui.tileselector.TileSelector;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MainWindow extends JFrame{
    final private JDesktopPane desktopPane = new JDesktopPane();
    final private RightMenu rightMenu = new RightMenu();
    final private TileSelector tileSelector = new TileSelector();

    public MainWindow() throws HeadlessException {
        super(Constants.TITLE);
        setJMenuBar(new MainMenuBar());
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
        panel.add(rightMenu, BorderLayout.EAST);
        panel.add(tileSelector, BorderLayout.SOUTH);
        setContentPane(panel);
    }
    
    private void onQuit(){
        int i = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter ?");
        
        if(i == JOptionPane.YES_OPTION){
            for(JInternalFrame frame : desktopPane.getAllFrames()){
                if(frame.isClosed() || !(frame instanceof MapEditorUI))
                    continue;
                
                MapEditorUI editor = (MapEditorUI)frame;
                
                if(!editor.closeWin()) //don't close
                    return;
            }
            
            System.exit(0);
        }
    }

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public RightMenu getRightMenu() {
        return rightMenu;
    }

    public TileSelector getTileSelector() {
        return tileSelector;
    }
    
    public void repaintAllEditors(){
        for(JInternalFrame frame : desktopPane.getAllFrames()){
            if(!(frame instanceof MapEditorUI))
                continue;
            
            MapEditorUI ui = (MapEditorUI)frame;
            ui.getGrid().repaint();
        }
    }
}
