/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.handler.EditorHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class PreviewTool implements Tool{

    @Override
    public void onClick(EditorHandler handler, Cell cell) {}

    @Override
    public LayerMask getLayerMask() {
        return JMapEditor.getMaskHandler().getPreviewMask();
    }

    @Override
    public boolean isPriority() {
        return true;
    }
    
}
