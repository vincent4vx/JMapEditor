/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SQLiteConnection {
    public interface ResultSetCallback{
        public void call(ResultSet RS) throws SQLException;
    }
    
    final private Connection connection;

    public SQLiteConnection(String db) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + db);
    }
    
    public ResultSet query(String query) throws SQLException{
        return connection.createStatement().executeQuery(query);
    }

    public Connection getConnection() {
        return connection;
    }
    
    public PreparedStatement prepare(String query, Object... args) throws SQLException{
        PreparedStatement stmt = connection.prepareStatement(query);
        
        int i = 0;
        for(Object arg : args){
            stmt.setString(++i, Objects.toString(arg));
        }
        
        return stmt;
    }
    
    public ResultSet executePrepare(String query, Object... args) throws SQLException{
        return prepare(query, args).executeQuery();
    }
    
    public int executeUpdate(String query, Object... args) throws SQLException{
        try(PreparedStatement stmt = prepare(query, args)) {
            return stmt.executeUpdate();
        }
    }
    
    private void forEachResultSet(ResultSet RS, ResultSetCallback callback) throws SQLException{
        while(RS.next()){
            callback.call(RS);
        }
    }
    
    public void forEachQuery(String query, ResultSetCallback callback) throws SQLException{
        forEachResultSet(query(query), callback);
    }
    
    public void forEachPrepare(String query, ResultSetCallback callback, Object... args) throws SQLException{
        forEachResultSet(executePrepare(query, args), callback);
    }
    
    public Statement createStatement() throws SQLException{
        return connection.createStatement();
    }
    
}
