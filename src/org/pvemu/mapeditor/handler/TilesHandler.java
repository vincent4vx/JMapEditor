/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.data.TilesList;
import org.pvemu.mapeditor.data.TilesListContainer;
import org.pvemu.mapeditor.ui.SplashScreen;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesHandler {
    private TilesList backgrounds;
    private TilesListContainer grounds;
    private TilesListContainer objects;
    
    private double loadingValue = 0;

    public TilesHandler() throws HeadlessException, IOException{
        SplashScreen loader = new SplashScreen();
        loader.setVisible(true);
        
        try{
            loader.setInfoText("Loading backgrounds");
            backgrounds = new TilesList(new File(JMapEditor.getParameters().getString("BACKGROUNDS_DIR")));
            
            backgrounds.setLoadingListener((s, v) -> {
                    loadingValue += v * 10;
                    loader.setValue((int)loadingValue);
                    loader.setInfoText("Loading background : " + s);
            });
            backgrounds.refresh();
            
            loader.setValue((int)loadingValue);
            loader.setInfoText("Loading grounds");
            grounds = new TilesListContainer(
                    new File(JMapEditor.getParameters().getString("GROUNDS_DIR")),
                    (s, v) -> {
                        loadingValue += v * 20;
                        loader.setValue((int)loadingValue);
                        loader.setInfoText("Loading gound : " + s);
                    }
            );
            
            loader.setInfoText("Loading objects");
            objects = new TilesListContainer(
                    new File(JMapEditor.getParameters().getString("OBJECTS_DIR")),
                    (s, v) -> {
                        loadingValue += v * 70;
                        loader.setValue((int)loadingValue);
                        loader.setInfoText("Loading object : " + s);
                    }
            );
        }catch(IOException e){
            JOptionPane.showMessageDialog(loader, e, "Erreur fatale", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(System.err);
            System.exit(1);
        }
        
        loader.dispose();
    }

    public TilesList getBackgrounds() {
        return backgrounds;
    }

    public TilesListContainer getGrounds() {
        return grounds;
    }

    public TilesListContainer getObjects() {
        return objects;
    }
}
