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
package org.pvemu.mapeditor.module.saving.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.module.saving.data.MapHistory;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapHistoryDAO {
    final private SQLiteConnexion connection;

    public MapHistoryDAO(SQLiteConnexion connection) {
        this.connection = connection;
    }
    
    private MapHistory getByRS(ResultSet RS) throws SQLException{
        return new MapHistory(
                RS.getLong("DATE"), 
                RS.getInt("BACKGROUND"), 
                RS.getInt("MUSIC"), 
                RS.getString("CELLS")
        );
    }
    
    public List<MapHistory> getAll() throws SQLException{
        final List<MapHistory> list = new ArrayList<>();
        connection.forEachQuery("SELECT * FROM MAP_HISTORY ORDER BY DATE DESC", (rs) -> list.add(getByRS(rs)));
        return list;
    }
    
    public MapHistory getLast() throws SQLException{
        ResultSet RS = connection.query("SELECT H.* FROM MAP_HISTORY H JOIN MAP_INFO I ON I.LAST_DATE = H.DATE");
        
        if(!RS.next())
            throw new SQLException("Invalid MAP_HISTORY : cannot found the last MAP_HISTORY");
        
        return getByRS(RS);
    }
    
    public void add(MapHistory history) throws SQLException{
        connection.executeUpdate(
                "INSERT INTO MAP_HISTORY(DATE, BACKGROUND, MUSIC, CELLS) VALUES(?,?,?,?)", 
                history.getDate(),
                history.getBackground(),
                history.getMusic(),
                history.getCells()
        );
    }
    
    public void createTable() throws SQLException{
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE MAP_HISTORY(" +
                            "DATE BIGINT PRIMARY KEY NOT NULL," +
                            "BACKGROUND INT," +
                            "MUSIC INT," +
                            "CELLS TEXT)"
            );
        }
    }
}
