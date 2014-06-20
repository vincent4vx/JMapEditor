/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.model.MapHistory;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class MapHistoryDAO {
    final private SQLiteConnection connection;

    public MapHistoryDAO(SQLiteConnection connection) {
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
