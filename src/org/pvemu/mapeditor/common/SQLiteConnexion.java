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
public class SQLiteConnexion {
    public interface ResultSetCallback{
        public void call(ResultSet RS) throws SQLException;
    }
    
    final private Connection connection;

    public SQLiteConnexion(String db) throws SQLException, ClassNotFoundException {
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
