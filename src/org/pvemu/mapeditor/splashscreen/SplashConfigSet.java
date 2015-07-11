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

package org.pvemu.mapeditor.splashscreen;

import org.pvemu.pvconfig.ConfigItem;
import org.pvemu.pvconfig.ConfigSet;
import org.pvemu.pvconfig.annotation.Note;
import org.pvemu.pvconfig.type.IntConfigItem;
import org.pvemu.pvconfig.type.StringConfigItem;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SplashConfigSet extends ConfigSet{
    @Note("Image de fond du splash screen")
    public final ConfigItem<String> SPLASH_BACKGROUND = new StringConfigItem("resources/ui/splashscreen.jpg");
    
    public final ConfigItem<Integer> SPLASH_SCREEN_WIDTH = new IntConfigItem(440);
    public final ConfigItem<Integer> SPLASH_SCREEN_HEIGHT = new IntConfigItem(286);
}
