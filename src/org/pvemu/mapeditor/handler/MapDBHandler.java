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
package org.pvemu.mapeditor.handler;

import java.sql.SQLException;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.module.saving.data.dao.MapHistoryDAO;
import org.pvemu.mapeditor.module.saving.data.dao.MapInfoDAO;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class MapDBHandler {
    final private String fileName;
    final private SQLiteConnexion connection;
    final private MapInfoDAO infoDAO;
    final private MapHistoryDAO historyDAO;

    public MapDBHandler(String fileName) throws SQLException, ClassNotFoundException {
        this.fileName = fileName;
        connection = new SQLiteConnexion(fileName);
        infoDAO = new MapInfoDAO(connection);
        historyDAO = new MapHistoryDAO(connection);
    }

    public SQLiteConnexion getConnection() {
        return connection;
    }

    public MapInfoDAO getInfoDAO() {
        return infoDAO;
    }

    public MapHistoryDAO getHistoryDAO() {
        return historyDAO;
    }

    public String getFileName() {
        return fileName;
    }
}
