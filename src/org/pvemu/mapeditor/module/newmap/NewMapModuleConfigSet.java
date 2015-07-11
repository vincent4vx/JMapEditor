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

package org.pvemu.mapeditor.module.newmap;

import org.pvemu.pvconfig.ConfigItem;
import org.pvemu.pvconfig.ConfigSet;
import org.pvemu.pvconfig.annotation.Note;
import org.pvemu.pvconfig.type.IntConfigItem;
import org.pvemu.pvconfig.type.StringConfigItem;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class NewMapModuleConfigSet extends ConfigSet{
    @Note("Largeur de la map par défaut")
    final public ConfigItem<Integer> DEFAULT_MAP_WIDTH = new IntConfigItem(15);
    
    @Note("Hauteur de la map par défaut")
    final public ConfigItem<Integer> DEFAULT_MAP_HEIGHT = new IntConfigItem(17);
    
    @Note("Dernier id de map utilisé. A incrémenter pour la création de map.")
    final public ConfigItem<Integer> LAST_MAP_ID = new IntConfigItem(13000);
    
    @Note("Chemin vers le fichier de l'icone 'nouvelle carte'")
    final public ConfigItem<String> NEW_MAP_ICON = new StringConfigItem("resources/ui/map_add.png");
}
