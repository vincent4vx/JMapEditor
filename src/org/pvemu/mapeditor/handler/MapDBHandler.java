/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler;

import java.sql.SQLException;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.dao.MapHistoryDAO;
import org.pvemu.mapeditor.data.db.dao.MapInfoDAO;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class MapDBHandler {
    final private String fileName;
    final private SQLiteConnection connection;
    final private MapInfoDAO infoDAO;
    final private MapHistoryDAO historyDAO;

    public MapDBHandler(String fileName) throws SQLException, ClassNotFoundException {
        this.fileName = fileName;
        connection = new SQLiteConnection(fileName);
        infoDAO = new MapInfoDAO(connection);
        historyDAO = new MapHistoryDAO(connection);
    }

    public SQLiteConnection getConnection() {
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
