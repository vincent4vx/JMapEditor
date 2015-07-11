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
package org.pvemu.mapeditor.tile;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import org.pvemu.dofusmapparser.data.Layer;
import org.pvemu.mapeditor.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesHandler {
    final private TilesConfigSet configSet = new TilesConfigSet();
    
    final private TilesList backgrounds;
    final private TilesListContainer grounds;
    final private TilesListContainer objects;
    
    final private Map<Layer, TilesListContainer> tilesByLayer = new EnumMap<>(Layer.class);

    public TilesHandler() throws IOException{
        JMapEditor.getParameters().getConfig().handle(configSet);
        
        backgrounds = new TilesList(new File(configSet.BACKGROUNDS_DIR.getValue()));
        grounds = new TilesListContainer(new File(configSet.GROUNDS_DIR.getValue()));
        objects = new TilesListContainer(new File(configSet.OBJECTS_DIR.getValue()));
        
        tilesByLayer.put(Layer.GROUND, grounds);
        tilesByLayer.put(Layer.LAYER1, objects);
        tilesByLayer.put(Layer.LAYER2, objects);
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
