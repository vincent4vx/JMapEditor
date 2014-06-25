/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Change {
    final private int action;
    final private EditorHandler handler;
    final private int[] params;
    final private String lastState;

    public Change(int action, EditorHandler handler, int[] params, String lastState) {
        this.action = action;
        this.handler = handler;
        this.params = params;
        this.lastState = lastState;
    }

    public int getAction() {
        return action;
    }

    public EditorHandler getHandler() {
        return handler;
    }

    public int getParam(int index){
        if(index >= params.length)
            return -1;
        
        return params[index];
    }

    public String getLastState() {
        return lastState;
    }
}
