package org.pvemu.mapeditor.handler.layer;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.TilesListContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public enum Layer {
    BACKGROUND("Background", null),
    WALKABLE("Marchable", null),
    GRID("Grille", null),
    LINE_OF_SIGHT("Ligne de vue", null),
    GROUND("Sol", JMapEditor.getTilesHandler().getGrounds()),
    LAYER1("Calque 1", JMapEditor.getTilesHandler().getObjects()),
    LAYER2("Calque 2", JMapEditor.getTilesHandler().getObjects()),
    ;
    
    final private String name;
    final private TilesListContainer tiles;

    Layer(String name, TilesListContainer tiles) {
        this.name = name;
        this.tiles = tiles;
    }

    public boolean isEditable() {
        return tiles != null;
    }

    public String getName() {
        return name;
    }

    public TilesListContainer getTiles() {
        return tiles;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
