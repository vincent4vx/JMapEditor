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
package org.pvemu.mapeditor;

import org.pvemu.mapeditor.splashscreen.LoadingListener;
import org.pvemu.mapeditor.common.utils.Utils;
import org.pvemu.mapeditor.tile.TilesList;
import org.pvemu.mapeditor.tile.TilesListContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesLoader implements Loadable{
    private LoadingListener listener;

    @Override
    public void setLoadingListener(LoadingListener listener) {
        this.listener = listener;
    }

    @Override
    public void load() {
        try{
            TilesList bg = JMapEditor.getTilesHandler().getBackgrounds();
            bg.setLoadingListener((s, v) -> listener.loaded("Background : " + s, (float)Utils.getAbsoluteValue(0, .1, v)));
            bg.refresh();

            TilesListContainer grounds = JMapEditor.getTilesHandler().getGrounds();
            grounds.setLoadingListener((s, v) -> listener.loaded("Ground : " + s, (float)Utils.getAbsoluteValue(.1, .25, v)));
            grounds.refresh();
            
            TilesListContainer objects = JMapEditor.getTilesHandler().getObjects();
            objects.setLoadingListener((s, v) -> listener.loaded("Object : " + s, (float)Utils.getAbsoluteValue(.25, 1, v)));
            objects.refresh();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
}
