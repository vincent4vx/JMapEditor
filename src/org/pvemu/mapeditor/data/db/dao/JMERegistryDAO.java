/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.model.JMERegistry;
import org.pvemu.mapeditor.handler.ParametersHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMERegistryDAO {
    final private SQLiteConnection connection;

    public JMERegistryDAO(SQLiteConnection connection) {
        this.connection = connection;
    }
    
    public void createTable() throws SQLException{
        connection.executeUpdate(
                "CREATE TABLE IF NOT EXISTS JME_REGISTRY(" +
                        "ID INT PRIMARY KEY," +
                        "NAME TEXT NOT NULL," +
                        "PARENT INT NOT NULL," +
                        "TYPE TEXT NOT NULL,"  +
                        "VALUE TEXT," +
                        "UNIQUE(NAME, PARENT) ON CONFLICT FAIL" +
                ")"
        );
    }
    
    public JMERegistry getRoot(){
        return new JMERegistry(-1, null, "ROOT", ParametersHandler.ParameterType.NULL, null, this){
            @Override
            public void delete() {
                throw new UnsupportedOperationException("Cannot delete the root node");
            }

            @Override
            public void setType(ParametersHandler.ParameterType type) {
                throw new UnsupportedOperationException("Cannot change type of root node");
            }

            @Override
            public void setValue(Object value) {
                throw new UnsupportedOperationException("Cannot change value of root node");
            }
        };
    }
    
    private JMERegistry getByRS(ResultSet RS, JMERegistry parent) throws SQLException{
        ParametersHandler.ParameterType type = ParametersHandler.ParameterType.valueOf(RS.getString("TYPE"));
        return new JMERegistry(
                RS.getInt("ID"), 
                parent, 
                RS.getString("NAME"), 
                type, 
                type.getValue(RS.getString("VALUE")),
                this
        );
    }
    
    public List<JMERegistry> getChildren(JMERegistry parent) throws SQLException{
        List<JMERegistry> children = new ArrayList<>();
        
        connection.forEachPrepare(
                "SELECT * FROM JME_REGISTRY WHERE PARENT = ?", 
                (rs) -> children.add(getByRS(rs, parent)), 
                parent.getId()
        );
        
        return children;
    }
    
    private int getLastId() throws SQLException{
        ResultSet RS = connection.query("SELECT IFNULL(MAX(ID), 0) FROM JME_REGISTRY");
        RS.next();
        return RS.getInt(1);
    }
    
    public JMERegistry create(String name, JMERegistry parent, ParametersHandler.ParameterType type, Object value) throws SQLException{
        int id = getLastId() + 1;
        JMERegistry registry = new JMERegistry(id, parent, name, type, value, this);
        
        connection.executeUpdate(
                "INSERT INTO JME_REGISTRY(ID, NAME, PARENT, TYPE, VALUE) VALUES(?,?,?,?,?)", 
                registry.getId(),
                registry.getName(),
                registry.getParent() == null ? -1 : registry.getParent().getId(),
                registry.getType(),
                registry.getValue()
        );
        
        return registry;
    }
    
    public void update(JMERegistry registry) throws SQLException{
        connection.executeUpdate(
                "UPDATE JME_REGISTRY SET TYPE = ?, VALUE = ? WHERE ID = ?", 
                registry.getType(),
                registry.getValue(),
                registry.getId()
        );
    }
    
    public void delete(JMERegistry registry) throws SQLException{
        connection.executeUpdate("DELETE FROM JME_REGISTRY WHERE ID = ?", registry.getId());
    }
}
