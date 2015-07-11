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
package org.pvemu.mapeditor.param;

import java.sql.SQLException;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.param.pvconfig.ConfigSQLiteDriver;
import org.pvemu.mapeditor.param.registry.Registry;
import org.pvemu.pvconfig.PvConfig;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Parameters {
    final private PvConfig config;
    final private Registry registry;

    public Parameters(SQLiteConnexion connexion) throws SQLException {
        this.config = new PvConfig(new ConfigSQLiteDriver(connexion, Constants.CONFIG_TABLE));
        this.registry = new Registry(connexion);
    }

    public PvConfig getConfig() {
        return config;
    }

    public Registry getRegistry() {
        return registry;
    }
    
}
