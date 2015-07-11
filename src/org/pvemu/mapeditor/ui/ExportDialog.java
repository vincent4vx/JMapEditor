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
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.base.editor.data.MapData;
import org.pvemu.mapeditor.base.editor.Editor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ExportDialog extends JDialog{
    final private JTextArea textArea = new JTextArea(4, 25);
    final private Editor handler;

    public ExportDialog(Editor handler) {
        super(JMapEditor.getUI(), "Exporter...");
        this.handler = handler;
        
        setModal(true);
        makeContent();
        pack();
        setResizable(false);
        setLocationRelativeTo(JMapEditor.getUI());
        setVisible(true);
    }
    
    private void makeContent(){
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(new JLabel("SQL:"), BorderLayout.NORTH);
        
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroll, BorderLayout.CENTER);
        
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton export = new JButton("Exporter");
        buttons.add(export);
        export.addActionListener((e) -> export());
        
        JButton cancel = new JButton("Fermer");
        buttons.add(cancel);
        cancel.addActionListener((e) -> {setVisible(false); dispose();});
        
        panel.add(buttons, BorderLayout.SOUTH);
        
        setContentPane(panel);
    }
    
    private void export(){
        MapData map = handler.getMap();
        try {
            JMapEditor.getExportHandler().export(handler);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, ex, "Exportation : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
        
        /*textArea.setText(
                "DELETE FROM maps WHERE id = " + map.getInfo().getId() + ";\n" +
                "INSERT INTO maps(id, date, width, heigth, mapData) VALUES(" + map.getInfo().getId() + ", " + map.getInfo().getLastDate() + ", " + map.getInfo().getWidth() + ", " + map.getInfo().getHeight() + ", '" + Compressor.compressMapData(map) + "');"
        );*/
    }
    
}
