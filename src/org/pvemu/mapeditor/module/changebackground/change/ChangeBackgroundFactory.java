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
package org.pvemu.mapeditor.module.changebackground.change;

import org.pvemu.mapeditor.base.editor.change.data.Change;
import org.pvemu.mapeditor.tile.Tile;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeBackgroundFactory {
    static public Change changeBackground(Tile background, Tile lastBg){
        int bg = background == null ? -1 : background.getId();
        int last = lastBg == null ? -1 : lastBg.getId();
        return new Change(ChangeBackgroundAction.ACTION_NAME, new int[]{bg}, "" + last);
    }
    
    static public Change removeBackground(Tile lastBg){
        return changeBackground(null, lastBg);
    }
}
