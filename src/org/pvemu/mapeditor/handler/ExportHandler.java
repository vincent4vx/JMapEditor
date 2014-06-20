/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        ID((m) -> "16000"),
        WIDTH((m) -> String.valueOf(m.getInfo().getWidth())),
        HEIGHT((m) -> String.valueOf(m.getInfo().getHeight())),
        BACKGROUND((m) -> String.valueOf(m.getBackground().getId())),
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
    
    final private String blank;

    public ExportHandler() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(Constants.BLANK_FILE));
        blank = new String(data);
    }
    
    private String mapToFlm(MapData map){
        String flm = blank;
        
        for(FlmVar var : FlmVar.values()){
            flm = flm.replace(var.getName(), var.getValue(map));
        }
        
        return flm;
    }
    
    public void export(MapData map) throws FileNotFoundException, IOException, InterruptedException{
        String flm = mapToFlm(map);
        
        PrintWriter pw = new PrintWriter("test.flm");
        pw.print(flm);
        pw.close();
        
        Process p = Runtime.getRuntime().exec("flasm -a test.flm");
        p.waitFor();
    }
}
