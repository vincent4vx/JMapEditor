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
package org.pvemu.mapeditor.module.saving.ui;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.pvemu.mapeditor.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMEFileChooser extends JFileChooser {
    public JMEFileChooser() {
        setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(!f.canRead() || !f.canWrite())
                    return false;
                
                return f.isDirectory() || f.getName().endsWith(JMapEditor.APP.getParameters().getString("JME_EXT"));
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
            selected = new File(selected.getAbsolutePath() + JMapEditor.APP.getParameters().getString("JME_EXT"));
        }
        
        return selected;
    }
}
