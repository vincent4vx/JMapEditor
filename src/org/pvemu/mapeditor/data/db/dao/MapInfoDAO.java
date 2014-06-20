/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.model.MapInfo;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapInfoDAO {
    final private SQLiteConnection connection;

    public MapInfoDAO(SQLiteConnection connection) {
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
