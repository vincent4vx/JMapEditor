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
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.module.saving.data.MapInfo;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapInfoDAO {
    final private SQLiteConnexion connection;

    public MapInfoDAO(SQLiteConnexion connection) {
        this.connection = connection;
    }
    
    public MapInfo load() throws SQLException{
        ResultSet RS = connection.query("SELECT * FROM MAP_INFO");
        
        if(!RS.next())
            throw new SQLException("Invalid MAP_INFO table : empty table !");
        
        int id = RS.getInt("ID");
        int width = RS.getInt("WIDTH");
        int height = RS.getInt("HEIGHT");
        long lastDate = RS.getLong("LAST_DATE");
        
        return new MapInfo(id, width, height, lastDate);
    }
    
    public void create(MapInfo mapInfo) throws SQLException{
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE MAP_INFO(" +
                            "ID INT NOT NULL," +
                            "WIDTH INT NOT NULL," +
                            "HEIGHT INT NOT NULL," +
                            "LAST_DATE BIGINT)"
            );
        }
        
        connection.executeUpdate(
                "INSERT INTO MAP_INFO(ID, WIDTH, HEIGHT, LAST_DATE) VALUES(?,?,?,?)", 
                mapInfo.getId(),
                mapInfo.getWidth(),
                mapInfo.getHeight(),
                mapInfo.getLastDate()
        );
    }
    
    public void update(MapInfo mapInfo) throws SQLException{
        connection.executeUpdate("UPDATE MAP_INFO SET LAST_DATE = ?", mapInfo.getLastDate());
    }
}
