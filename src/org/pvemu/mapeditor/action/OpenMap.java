/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.action;

import org.pvemu.mapeditor.handler.EditorHandler;
import org.pvemu.mapeditor.data.MapData;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class OpenMap {
    static public void newMap(int width, int height){
        MapData map = new MapData(width, height);
        EditorHandler editor = new EditorHandler(map);
        JMapEditor.getUI().getDesktopPane().add(editor.getUI());
    }
}
