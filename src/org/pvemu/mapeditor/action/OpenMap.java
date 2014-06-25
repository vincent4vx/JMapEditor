/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.action;

import java.sql.SQLException;
import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.data.db.model.MapHistory;
import org.pvemu.mapeditor.data.db.model.MapInfo;
import org.pvemu.mapeditor.handler.MapDBHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class OpenMap {
    static public void newMap(int id, int width, int height){
        MapData map = MapData.generateMap(new MapInfo(id, width, height));
        EditorHandler editor = new EditorHandler(map);
        JMapEditor.getUI().getDesktopPane().add(editor.getUI());
    }
    
    static public void loadMap(String file) throws SQLException, ClassNotFoundException{
        MapDBHandler data = new MapDBHandler(file);
        MapInfo info = data.getInfoDAO().load();
        MapHistory history = data.getHistoryDAO().getLast();
        MapData map = MapData.loadMap(info, history);
        EditorHandler handler = new EditorHandler(map, data);
        JMapEditor.getUI().getDesktopPane().add(handler.getUI());
    }
}
