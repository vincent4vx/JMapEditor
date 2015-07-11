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
import org.pvemu.mapeditor.tile.TilesHandler;
import org.pvemu.mapeditor.ui.JMEUI;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class BaseLoader implements Loadable{
    private LoadingListener listener;

    @Override
    public void setLoadingListener(LoadingListener listener) {
        this.listener = listener;
    }

    @Override
    public void load() {
        try{
            listener.loaded("Loading UI", 0f);
            JMapEditor.setUI(new JMEUI());
            listener.loaded("Loading tiles handler", .5f);
            JMapEditor.setTilesHandler(new TilesHandler());
            listener.loaded("Base loaded", 1f);
        }catch(Exception e){
            throw new Error(e);
        }
    }
    
}
