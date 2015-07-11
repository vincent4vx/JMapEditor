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
package org.pvemu.mapeditor.module.changebackground;

import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.tile.Tile;
import org.pvemu.mapeditor.common.Module;
import org.pvemu.mapeditor.module.changebackground.change.ChangeBackgroundAction;
import org.pvemu.mapeditor.module.changebackground.change.ChangeBackgroundFactory;
import org.pvemu.mapeditor.module.changebackground.ui.builder.ChangeBackgroundTabBuilder;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ChangeBackgroundModule implements Module{

    @Override
    public String moduleName() {
        return "CHANGE_BACKGROUND";
    }

    @Override
    public void configure() {
        JMapEditor.APP.getEditorsHandler()
                .getChangesHandler()
                .registerChangeAction(new ChangeBackgroundAction());
    }

    @Override
    public void initUI() {
        JMapEditor.APP.getBaseUIBuilder()
                .getRightToolBarBuilder()
                .getTabbedPaneBuilder()
                .add(new ChangeBackgroundTabBuilder(this));
    }
    
    public void changeBackground(Tile background){
        JMapEditor.APP.getEditorsHandler().actionOnCurrent((e) -> changeBackground(e, background));
    }
    
    public void removeBackground(){
        JMapEditor.APP.getEditorsHandler().actionOnCurrent((e) -> removeBackground(e));
    }
    
    public void removeBackground(Editor editor){
        editor.getChangeHandler().addChange(ChangeBackgroundFactory.removeBackground(
                editor.getMap().getBackground()
        ));
    }
    
    public void changeBackground(Editor editor, Tile background){
        editor.getChangeHandler().addChange(ChangeBackgroundFactory.changeBackground(
                background, editor.getMap().getBackground()
        ));
    }
    
}
