/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.pvemu.mapeditor.action.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMEFileChooser extends JFileChooser {
    final static private JMEFileChooser CHOOSER = new JMEFileChooser();

    private JMEFileChooser() {
        setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(!f.canRead() || !f.canWrite())
                    return false;
                
                return f.isDirectory() || f.getName().endsWith(JMapEditor.getParameters().getString("JME_EXT"));
            }

            @Override
            public String getDescription() {
                return "Fichier de sauvegarde JMapEditor";
            }
        });
        
        setMultiSelectionEnabled(false);
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        Path current = Paths.get("");
        setCurrentDirectory(current.toFile());
    }

    @Override
    public File getSelectedFile() {
        File selected = super.getSelectedFile();
        
        if(selected == null)
            return null;
        
        //ADD ext
        if(selected.getName().split("\\.").length < 2){
            selected = new File(selected.getAbsolutePath() + JMapEditor.getParameters().getString("JME_EXT"));
        }
        
        return selected;
    }

    static public JMEFileChooser getFileChooser(){
        return CHOOSER;
    }
}
