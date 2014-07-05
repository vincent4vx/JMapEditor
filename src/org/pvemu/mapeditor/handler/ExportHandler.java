/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import javax.imageio.ImageIO;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Compressor;
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
        blank = Paths.get(JMapEditor.getParameters().getString("BLANK_FILE"));
    }
    
    private String mapToFlm(String pattern, MapData map){        
        for(FlmVar var : FlmVar.values()){
            pattern = pattern.replace(var.getName(), var.getValue(map));
        }
        
        return pattern;
    }
    
    public void export(EditorHandler handler) throws FileNotFoundException, IOException, InterruptedException, Exception{
        MapData map = handler.getMap();
        
        String dir = JMapEditor.getParameters().getString("OUTPUT_DIR") + map.getInfo().getId() + "/";
        Files.createDirectories(Paths.get(dir));
        
        String baseName = dir + map.getInfo().getId() + "_" + map.getInfo().getLastDate();
        Path swf = Paths.get(baseName + JMapEditor.getParameters().getString("SWF_EXT"));
        Path flm = Paths.get(baseName + JMapEditor.getParameters().getString("FLM_EXT"));
        
        BufferedImage img = new BufferedImage(
                map.getInfo().getWidth() * JMapEditor.getParameters().getInt("CELL_WIDTH"), 
                map.getInfo().getHeight() * JMapEditor.getParameters().getInt("CELL_HEIGHT"), 
                BufferedImage.TYPE_INT_RGB
        );
        handler.getUI().getGrid().paintLayers(img.createGraphics(), JMapEditor.getMaskHandler().getPreviewMask());
        ImageIO.write(
                img, 
                JMapEditor.getParameters().getString("EXPORT_IMG_FORMAT"), 
                new File(baseName + JMapEditor.getParameters().getString("EXPORT_IMG_EXT"))
        );
        
        Files.copy(blank, swf, StandardCopyOption.REPLACE_EXISTING);
        
        Process p = Runtime.getRuntime().exec(getFlasmCommand() + " -d " + swf);
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
        
        p = Runtime.getRuntime().exec(getFlasmCommand() + " -a " + flm);
        p.waitFor();
        
        Files.delete(flm);
    }
    
    private String getFlasmCommand() throws Exception{
        String os = System.getProperty("os.name").toLowerCase();
        String paramName;
        
        if(os.startsWith("windows")){
            paramName = "FLASM_WIN";
        }else if(os.startsWith("linux")){
            paramName = "FLASM_LINUX";
        }else if(os.startsWith("mac")){
            paramName = "FLASM_MAC";
        }else{
            throw new Exception("Cannot reconnized OS " + os);
        }
        
        return JMapEditor.getParameters().getString(paramName);
    }
}
