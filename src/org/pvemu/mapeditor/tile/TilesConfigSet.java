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

import org.pvemu.pvconfig.ConfigItem;
import org.pvemu.pvconfig.ConfigSet;
import org.pvemu.pvconfig.annotation.Note;
import org.pvemu.pvconfig.type.StringConfigItem;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TilesConfigSet extends ConfigSet{
    @Note("Dossier contenant les tiles de backgrounds")
    final public ConfigItem<String> BACKGROUNDS_DIR = new StringConfigItem("resources/tiles/backgrounds/");
    
    @Note("Dossier contenant les tiles de sols")
    final public ConfigItem<String> GROUNDS_DIR = new StringConfigItem("resources/tiles/grounds/");
    
    @Note("Dossier contenant les tiles des objets")
    final public ConfigItem<String> OBJECTS_DIR = new StringConfigItem("resources/tiles/objects");
}
