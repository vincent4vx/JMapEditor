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
package org.pvemu.mapeditor.ui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import org.pvemu.mapeditor.base.editor.ui.EditorUI;
import org.pvemu.mapeditor.common.Constants;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMEUI extends JFrame{
    final private JDesktopPane desktopPane = new JDesktopPane();
    final private RightToolBar rightToolBar = new RightToolBar();
    final private BottomToolBar bottomToolBar = new BottomToolBar();
    final private JMEMenuBar menuBar = new JMEMenuBar();
    final private TopToolBar topToolBar = new TopToolBar();

    public JMEUI() throws HeadlessException {
        super(Constants.TITLE);
        setJMenuBar(menuBar);
        makePanel();
        setSize(1010, 650);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                //onQuit();
                System.exit(0);
            }
            
        });
        
        setLocationRelativeTo(null);
    }
    
    private void makePanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(desktopPane, BorderLayout.CENTER);
        panel.add(rightToolBar, BorderLayout.EAST);
        panel.add(bottomToolBar, BorderLayout.SOUTH);
        panel.add(topToolBar, BorderLayout.NORTH);
        setContentPane(panel);
    }
    
    /*private void onQuit(){
        int i = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter ?");
        
        if(i == JOptionPane.YES_OPTION){
            for(JInternalFrame frame : desktopPane.getAllFrames()){
                if(frame.isClosed() || !(frame instanceof EditorUI))
                    continue;
                
                EditorUI editor = (EditorUI)frame;
                
                if(!editor.closeWin()) //don't close
                    return;
            }
            
            System.exit(0);
        }
    }*/

    public JDesktopPane getDesktopPane() {
        return desktopPane;
    }

    public RightToolBar getRightToolBar() {
        return rightToolBar;
    }
    
    public void repaintAllEditors(){
        for(JInternalFrame frame : desktopPane.getAllFrames()){
            if(!(frame instanceof EditorUI))
                continue;
            
            EditorUI ui = (EditorUI)frame;
            ui.getGrid().repaint();
        }
    }

    public JMEMenuBar getJMEMenuBar() {
        return menuBar;
    }

    public TopToolBar getTopToolBar() {
        return topToolBar;
    }

    public BottomToolBar getBottomToolBar() {
        return bottomToolBar;
    }
}
