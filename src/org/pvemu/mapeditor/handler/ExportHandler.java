/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import org.pvemu.mapeditor.common.Compressor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.data.MapData;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ExportHandler {
    private interface VarExporter{
        String export(MapData map);
    }
    
    private enum FlmVar{
        ID((m) -> String.valueOf(m.getInfo().getId())),
        WIDTH((m) -> String.valueOf(m.getInfo().getWidth())),
        HEIGHT((m) -> String.valueOf(m.getInfo().getHeight())),
        BACKGROUND((m) -> String.valueOf(m.getBackground() == null ? 0 : m.getBackground().getId())),
        AMBIANCE((m) -> "0"),
        MUSIC((m) -> "0"),
        OUTDOOR((m) -> "TRUE"),
        CAPABILITIES((m) -> "14"),
        MAPDATA((m) -> Compressor.compressMapData(m))
        ;
        final private VarExporter exporter;

        private FlmVar(VarExporter exporter) {
            this.exporter = exporter;
        }
        
        String getValue(MapData map){
            return exporter.export(map);
        }
        
        String getName(){
            return "__" + name().toUpperCase() + "__";
        }
    }
    
    final private Path blank;

    public ExportHandler() {
        blank = Paths.get(Constants.BLANK_FILE);
    }
    
    private String mapToFlm(String pattern, MapData map){        
        for(FlmVar var : FlmVar.values()){
            pattern = pattern.replace(var.getName(), var.getValue(map));
        }
        
        return pattern;
    }
    
    public void export(MapData map) throws FileNotFoundException, IOException, InterruptedException{
        String baseName = map.getInfo().getId() + "_" + map.getInfo().getLastDate();
        Path swf = Paths.get(baseName + Constants.SWF_EXT);
        Path flm = Paths.get(baseName + Constants.FLM_EXT);
        
        Files.copy(blank, swf, StandardCopyOption.REPLACE_EXISTING);
        
        Process p = Runtime.getRuntime().exec("flasm -d " + swf);
        p.waitFor();
        
        InputStreamReader isr = new InputStreamReader(p.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        
        StringBuilder sb = new StringBuilder();
        
        for(String line = br.readLine(); line != null; line = br.readLine()){
            sb.append(line);
            sb.append("\r\n");
        }
        
        String pattern = sb.toString();
        
        String data = mapToFlm(pattern, map);
        
        Files.write(flm, data.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        
        p = Runtime.getRuntime().exec("flasm -a " + flm);
        p.waitFor();
        
        Files.delete(flm);
    }
}
