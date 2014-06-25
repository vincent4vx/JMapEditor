/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Change;
import org.pvemu.mapeditor.data.Tile;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeBackgroundAction implements ChangeAction{
    final static public int BACKGROUND_ID_PARAM = 0;

    @Override
    public int id() {
        return ChangeActions.CHANGE_BACKBROUND_ACTION;
    }

    @Override
    public void perform(Change change) {
        Tile bg = JMapEditor.getTilesHandler().getBackgrounds().getTileById(change.getParam(BACKGROUND_ID_PARAM));
        change.getHandler().getMap().setBackground(bg);
        change.getHandler().update();
    }

    @Override
    public void undo(Change change) {
        Tile bg = null;
        
        try{
            bg = JMapEditor.getTilesHandler().getBackgrounds().getTileById(Integer.parseInt(change.getLastState()));
        }catch(Exception e){}
        
        change.getHandler().getMap().setBackground(bg);
        change.getHandler().update();
    }

    @Override
    public String label(Change change) {
        return "Background : " + change.getParam(BACKGROUND_ID_PARAM);
    }
    
}
