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
package org.pvemu.mapeditor.module.saving.data;

import java.sql.SQLException;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.module.saving.data.dao.MapHistoryDAO;
import org.pvemu.mapeditor.module.saving.data.dao.MapInfoDAO;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditorSavingState {
    final private String file;
    final private SQLiteConnexion connection;
    final private MapHistoryDAO historyDAO;
    final private MapInfoDAO infoDAO;
    private MapInfo info;
    private boolean changed = false;

    public EditorSavingState(String file) throws SQLException, ClassNotFoundException {
        this.file = file;
        connection = new SQLiteConnexion(file);
        historyDAO = new MapHistoryDAO(connection);
        infoDAO = new MapInfoDAO(connection);
    }

    public String getFile() {
        return file;
    }

    public MapHistoryDAO getHistoryDAO() {
        return historyDAO;
    }

    public MapInfoDAO getInfoDAO() {
        return infoDAO;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public MapInfo getInfo() {
        return info;
    }

    public void setInfo(MapInfo info) {
        this.info = info;
    }
}
