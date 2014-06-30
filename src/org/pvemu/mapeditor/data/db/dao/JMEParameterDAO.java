package org.pvemu.mapeditor.data.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.model.JMEParameter;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMEParameterDAO {
    final private SQLiteConnection connection;

    public JMEParameterDAO(SQLiteConnection connection) {
        this.connection = connection;
    }
    
    private JMEParameter getByRS(ResultSet RS) throws SQLException{
        JMEParameter.ParameterType type = JMEParameter.ParameterType.valueOf(RS.getString("TYPE"));
        return new JMEParameter(
                RS.getString("NAME"), 
                type, 
                type.getValue(RS.getString("VALUE"))
        );
    }
    
    public List<JMEParameter> getAll() throws SQLException{
        final List<JMEParameter> params = new ArrayList<>();
        
        connection.forEachQuery("SELECT * FROM JME_PARAMETER", (rs) -> {
            JMEParameter param = getByRS(rs);
            params.add(param);
        });
        
        return params;
    }
    
    public void create(JMEParameter parameter) throws SQLException{
        connection.executeUpdate(
                "INSERT INTO JME_PARAMETER(NAME, TYPE, VALUE) VALUES(?,?,?)", 
                parameter.getName(), 
                parameter.getType(), 
                parameter.getValue()
        );
    }
    
    public void update(JMEParameter parameter) throws SQLException{
        connection.executeUpdate(
                "UPDATE JME_PARAMETER SET TYPE = ?, VALUE = ? WHERE NAME = ?",
                parameter.getType(),
                parameter.getValue(),
                parameter.getName()
        );
    }
    
    public void delete(JMEParameter parameter) throws SQLException{
        connection.executeUpdate("DELETE FROM JME_PARAMETER WHERE NAME = ?", parameter.getName());
    }
    
    public JMEParameter getByName(String name) throws SQLException{
        ResultSet RS = connection.executePrepare("SELECT * FROM JME_PARAMETER WHERE NAME = ?", name);
        
        if(!RS.next())
            return null;
        
        return getByRS(RS);
    }
    
    public void createTable() throws SQLException{
        connection.executeUpdate(
                "CREATE TABLE IF NOT EXISTS JME_PARAMETER(" +
                        "NAME TEXT PRIMARY KEY NOT NULL," +
                        "TYPE TEXT NOT NULL," +
                        "VALUE TEXT" +
                ")"
        );
    }
}
