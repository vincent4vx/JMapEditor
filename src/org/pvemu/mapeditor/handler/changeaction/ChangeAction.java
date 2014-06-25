/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.changeaction;

import org.pvemu.mapeditor.data.Change;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public interface ChangeAction {
    public int id();
    public void perform(Change change);
    public void undo(Change change);
    public String label(Change change);
}
