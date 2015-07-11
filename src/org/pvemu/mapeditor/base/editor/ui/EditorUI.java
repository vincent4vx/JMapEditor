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
package org.pvemu.mapeditor.base.editor.ui;

import javax.swing.JInternalFrame;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.Editor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditorUI extends JInternalFrame {
    final private Editor editor;
    final private EditorGrid grid;

    public EditorUI(Editor editor){
        this.editor = editor;
        grid = new EditorGrid(editor.getMap(), editor);
        add(grid);

        setResizable(false);
        setIconifiable(true);
        setMaximizable(false);
        setClosable(true);
        
        final int CELL_WIDTH = JMapEditor.APP.getParameters().getInt("CELL_WIDTH");
        final int CELL_HEIGHT = JMapEditor.APP.getParameters().getInt("CELL_HEIGHT");
        
        setSize((editor.getMap().getWidth() - 1) * CELL_WIDTH + 10, editor.getMap().getHeight() * CELL_HEIGHT + 6);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        
        setFocusable(true);
        /*addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 'r'){
                    Cell cell = editor.getSelectedCell();
                    
                    if(cell != null){
                        editor.getChangeHandler().addChange(ChangeActionFactory.flip(editor, cell, app.getLayerHandler().getSelected()));
                    }else{
                        CellObject obj = app.getToolsHandler().getCurrentObject();
                        
                        if(obj != null){
                            obj.flip();
                            grid.repaint();
                        }
                    }
                }else if(e.getKeyChar() == KeyEvent.VK_DELETE){
                    Cell cell = editor.getSelectedCell();
                    
                    if(cell == null)
                        return;
                    
                    editor.setSelectedCell(null);
                    editor.getChangeHandler().addChange(ChangeActionFactory.removeObject(editor, cell, app.getLayerHandler().getSelected()));
                }
            }
            
        });*/
    }

    public EditorGrid getGrid() {
        return grid;
    }

    public Editor getEditor() {
        return editor;
    }

    /*public boolean closeWin() {
        if (!editor.isChanged()) {
            dispose();
            return true;
        }

        int r = JOptionPane.showConfirmDialog(editor.getUI(), "Voulez-vous enregistrer avant de quitter ?");

        try {
            switch (r) {
                case JOptionPane.YES_OPTION:
                    editor.save();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                default:
                    return false;
            }

            dispose();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(editor.getUI(), ex, "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
            return false;
        }
    }*/

}
