/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.handler.tool;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Tools {
    final static public Tool 
            SELECT = new SelectTool(),
            ADD = new AddTool(),
            REMOVE = new RemoveTool(),
            STATE = new StateTool(),
            PREVIEW = new PreviewTool();
}
