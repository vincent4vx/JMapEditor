/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.common.LayerMaskable;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public interface Tool extends LayerMaskable{
    public void onClick(EditorHandler handler, Cell cell);
    default public void onSelect(){}
}
