package org.pvemu.mapeditor.ui.editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.handler.changeaction.ChangeActionFactory;
import org.pvemu.mapeditor.handler.layer.Layer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapEditorUI extends JInternalFrame {

    final private EditorHandler handler;
    final private EditorGrid grid;

    public MapEditorUI(EditorHandler handler){
        this.handler = handler;
        grid = new EditorGrid(handler.getMap(), handler);
        add(grid);

        setResizable(false);
        setIconifiable(true);
        setMaximizable(false);
        setClosable(true);
        
        final int CELL_WIDTH = JMapEditor.getParameters().getInt("CELL_WIDTH");
        final int CELL_HEIGHT = JMapEditor.getParameters().getInt("CELL_HEIGHT");
        
        setSize((handler.getMap().getInfo().getWidth() - 1) * CELL_WIDTH + 10, handler.getMap().getInfo().getHeight() * CELL_HEIGHT + 6);

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
                    Cell cell = handler.getSelectedCell();
                    
                    if(cell != null){
                        handler.getChangeHandler().addChange(ChangeActionFactory.flip(handler, cell, Layer.getSelected()));
                    }else{
                        CellObject obj = JMapEditor.getToolsHandler().getCurrentObject();
                        
                        if(obj != null){
                            obj.flip();
                            grid.repaint();
                        }
                    }
                }else if(e.getKeyChar() == KeyEvent.VK_DELETE){
                    Cell cell = handler.getSelectedCell();
                    
                    if(cell == null)
                        return;
                    
                    handler.setSelectedCell(null);
                    handler.getChangeHandler().addChange(ChangeActionFactory.removeObject(handler, cell, Layer.getSelected()));
                }
            }
            
        });
    }

    public EditorGrid getGrid() {
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
