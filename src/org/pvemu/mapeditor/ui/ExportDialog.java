/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ExportDialog extends JDialog{
    final private JTextArea textArea = new JTextArea(4, 25);
    final private EditorHandler handler;

    public ExportDialog(EditorHandler handler) {
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
            JMapEditor.getExportHandler().export(map);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, ex, "Exportation : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
        
        textArea.setText(
                "DELETE FROM maps WHERE id = " + map.getInfo().getId() + ";\n" +
                "INSERT INTO maps(id, date, width, heigth, mapData) VALUES(" + map.getInfo().getId() + ", " + map.getInfo().getLastDate() + ", " + map.getInfo().getWidth() + ", " + map.getInfo().getHeight() + ", '" + Compressor.compressMapData(map) + "');"
        );
    }
    
}
