package org.pvemu.mapeditor.data.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.model.JMERegistry;
import org.pvemu.mapeditor.handler.setting.ParameterType;

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
    
    public JMERegistry getRoot() throws SQLException{
        JMERegistry root = new JMERegistry(-1, "ROOT", null, ParameterType.NULL, null){
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
    
    private JMERegistry getByRS(ResultSet RS, JMERegistry parent) throws SQLException{
        ParameterType type = ParameterType.valueOf(RS.getString("TYPE"));
        return new JMERegistry(
                RS.getInt("ID"),
                RS.getString("NAME"), 
                parent,
                type, 
                type.getValue(RS.getString("VALUE"))
        );
    }
    
    private void addChildren(JMERegistry registry) throws SQLException{
        connection.forEachPrepare(
                "SELECT * FROM JME_REGISTRY WHERE PARENT = ?", 
                (rs) -> {
                    JMERegistry child = getByRS(rs, registry);
                    registry.add(child);
                }, 
                registry.getId()
        );
    }
    
    private int getLastId() throws SQLException{
        ResultSet RS = connection.query("SELECT IFNULL(MAX(ID), 0) FROM JME_REGISTRY");
        RS.next();
        return RS.getInt(1);
    }
    
    public JMERegistry create(String name, JMERegistry parent, ParameterType type, Object value) throws SQLException{
        int id = getLastId() + 1;
        JMERegistry registry = new JMERegistry(id, name, parent, type, value);
        
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
