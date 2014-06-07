/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.ui.editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapEditorUI extends JInternalFrame {

    final private EditorHandler handler;
    final private MapGrid grid;

    public MapEditorUI(EditorHandler handler) {
        this.handler = handler;
        grid = new MapGrid(handler.getMap());
        add(grid);

        setResizable(false);
        setIconifiable(true);
        setMaximizable(false);
        setClosable(true);
        setSize((handler.getMap().getWidth() - 1) * Constants.CELL_WIDTH + 10, handler.getMap().getHeight() * Constants.CELL_HEIGHT + 6);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                closeWin();
            }
        });
        
        setFocusable(true);
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 'r'){
                    CellObject obj = JMapEditor.getToolsHandler().getCurrentObject();
                    
                    if(obj != null)
                        obj.flip();
                }else if(e.getKeyChar() == KeyEvent.VK_DELETE){
                    Cell cell = JMapEditor.getToolsHandler().getCurrentCell();
                    
                    if(cell != null){
                        cell.setLayer1(null);
                        EditorHandler.getCurrentHandler().update();
                    }
                }
            }
            
        });
    }

    public MapGrid getGrid() {
        return grid;
    }

    public EditorHandler getHandler() {
        return handler;
    }

    public boolean closeWin() {
        if (!handler.isChanged()) {
            dispose();
            return true;
        }

        int r = JOptionPane.showConfirmDialog(handler.getUI(), "Voulez-vous enregistrer avant de quitter ?");

        try {
            switch (r) {
                case JOptionPane.YES_OPTION:
                    handler.save();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                default:
                    return false;
            }

            dispose();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(handler.getUI(), ex, "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
            return false;
        }
    }

}
