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
package org.pvemu.mapeditor.param.registry.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.mapeditor.param.registry.ParameterType;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class RegistryDAO {
    final private SQLiteConnexion connection;
    final private String tableName;

    public RegistryDAO(SQLiteConnexion connection, String tableName) throws SQLException {
        this.connection = connection;
        this.tableName = tableName;
        createTable();
    }
    
    private void createTable() throws SQLException{
        connection.executeUpdate(
                "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                        "ID INT PRIMARY KEY," +
                        "NAME TEXT NOT NULL," +
                        "PARENT INT NOT NULL," +
                        "TYPE TEXT NOT NULL,"  +
                        "VALUE TEXT," +
                        "UNIQUE(NAME, PARENT) ON CONFLICT FAIL" +
                ")"
        );
    }
    
    public RegistryModel getRoot() throws SQLException{
        RegistryModel root = new RegistryModel(-1, "ROOT", null, ParameterType.NULL, null){
            @Override
            public void setType(ParameterType type) {
                throw new UnsupportedOperationException("Cannot change type of root node");
            }

            @Override
            public void setValue(Object value) {
                throw new UnsupportedOperationException("Cannot change value of root node");
            }
        };
        
        addChildren(root);
        
        return root;
    }
    
    private RegistryModel getByRS(ResultSet RS, RegistryModel parent) throws SQLException{
        ParameterType type = ParameterType.valueOf(RS.getString("TYPE"));
        return new RegistryModel(
                RS.getInt("ID"),
                RS.getString("NAME"), 
                parent,
                type, 
                type.getValue(RS.getString("VALUE"))
        );
    }
    
    private void addChildren(RegistryModel registry) throws SQLException{
        connection.forEachPrepare(
                "SELECT * FROM " + tableName + " WHERE PARENT = ?", 
                (rs) -> {
                    RegistryModel child = getByRS(rs, registry);
                    registry.add(child);
                }, 
                registry.getId()
        );
    }
    
    private int getLastId() throws SQLException{
        ResultSet RS = connection.query("SELECT IFNULL(MAX(ID), 0) FROM " + tableName);
        RS.next();
        return RS.getInt(1);
    }
    
    public RegistryModel create(String name, RegistryModel parent, ParameterType type, Object value) throws SQLException{
        int id = getLastId() + 1;
        RegistryModel registry = new RegistryModel(id, name, parent, type, value);
        
        connection.executeUpdate(
                "INSERT INTO " + tableName + "(ID, NAME, PARENT, TYPE, VALUE) VALUES(?,?,?,?,?)", 
                registry.getId(),
                registry.getName(),
                registry.getParent() == null ? -1 : registry.getParent().getId(),
                registry.getType(),
                registry.getValue()
        );
        
        return registry;
    }
    
    public void update(RegistryModel registry) throws SQLException{
        connection.executeUpdate(
                "UPDATE " + tableName + " SET TYPE = ?, VALUE = ? WHERE ID = ?", 
                registry.getType(),
                registry.getValue(),
                registry.getId()
        );
    }
    
    public void delete(RegistryModel registry) throws SQLException{
        connection.executeUpdate("DELETE FROM " + tableName + " WHERE ID = ?", registry.getId());
    }
}
