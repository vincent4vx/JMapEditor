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
package org.pvemu.mapeditor.handler.tool;

import org.pvemu.mapeditor.base.editor.tool.Tool;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.data.LayerMask;
import org.pvemu.mapeditor.base.editor.Editor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class PreviewTool implements Tool{

    @Override
    public void onClick(Editor handler, Cell cell) {}

    @Override
    public LayerMask getLayerMask() {
        return JMapEditor.getMaskHandler().getPreviewMask();
    }

    @Override
    public boolean isPriority() {
        return true;
    }
    
}
