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
package org.pvemu.mapeditor.param.pvconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.common.SQLiteConnexion;
import org.pvemu.pvconfig.dao.ConfigItemDAO;
import org.pvemu.pvconfig.model.ConfigItemModel;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ConfigSQLiteDriver implements ConfigItemDAO{
    final private SQLiteConnexion connexion;
    final private String tableName;

    public ConfigSQLiteDriver(SQLiteConnexion connexion, String tableName) {
        this.connexion = connexion;
        this.tableName = tableName;
        makeTable();
    }
    
    private void makeTable(){
        try{
            connexion.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                            "NAME TEXT PRIMARY KEY NOT NULL," +
                            "VALUE TEXT" +
                    ")"
            );
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    private ConfigItemModel createByResultSet(ResultSet RS) throws SQLException{
        return new ConfigItemModel(RS.getString("NAME"), RS.getString("VALUE"));
    }

    @Override
    public List<ConfigItemModel> getAll() {
        List<ConfigItemModel> models = new ArrayList<>();

        try{
            connexion.forEachQuery("SELECT * FROM " + tableName, (rs) -> models.add(createByResultSet(rs)));
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        
        return models;
    }

    @Override
    public ConfigItemModel get(String name) {
        try{
            ResultSet RS = connexion.executePrepare("SELECT * FROM " + tableName + " WHERE NAME = ?", name);
            
            if(!RS.next())
                return null;
            
            return createByResultSet(RS);
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean create(ConfigItemModel model) {
        try{
            connexion.executeUpdate("INSERT INTO " + tableName + "(NAME, VALUE) VALUES(?,?)", model.getName(), model.getValue());
            return true;
        }catch(SQLException ex){
            return false;
        }
    }

    @Override
    public boolean update(ConfigItemModel model) {
        try{
            connexion.executeUpdate("UPDATE " + tableName + " SET VALUE = ? WHERE NAME = ?", model.getValue(), model.getName());
            return true;
        }catch(SQLException ex){
            return false;
        }
    }

    @Override
    public boolean delete(ConfigItemModel model) {
        try{
            connexion.executeUpdate("DELETE FROM " + tableName + " WHERE NAME = ?", model.getName());
            return true;
        }catch(SQLException ex){
            return false;
        }
    }
    
}
