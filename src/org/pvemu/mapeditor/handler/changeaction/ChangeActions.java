/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeActions {
    final static public int CHANGE_BACKBROUND_ACTION = 0;
    final static public int ADD_OBJECT_ACTION        = 1;
    final static public int REMOVE_OBJECT_ACTION     = 2;
    final static public int FLIP_ACTION              = 4;
    
    final static private Map<Integer, ChangeAction> actions = new HashMap<>();
    
    static{
        registerAction(new ChangeBackgroundAction());
        registerAction(new AddObjectAction());
        registerAction(new RemoveObjectAction());
        registerAction(new FlipAction());
    }
    
    static public void registerAction(ChangeAction action){
        actions.put(action.id(), action);
    }
    
    static public ChangeAction getAction(int actionID){
        return actions.get(actionID);
    }
}
