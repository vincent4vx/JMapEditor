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
package org.pvemu.mapeditor.module.saving;

import javax.swing.JOptionPane;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.Editor;
import org.pvemu.mapeditor.base.editor.EditorListener;
import org.pvemu.mapeditor.module.saving.data.EditorSavingState;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SavingEditorListener implements EditorListener {
    final private SavingModule module;

    SavingEditorListener(SavingModule module) {
        this.module = module;
    }

    @Override
    public boolean onOpen(Editor editor) {
        EditorSavingState state = (EditorSavingState) editor.getAttachment(module);

        if (state == null) {
            editor.getUI().setTitle("Sans titre *");
        } else {
            editor.getUI().setTitle(state.getFile());
        }

        return true;
    }

    @Override
    public boolean onChange(Editor editor) {
        EditorSavingState state = (EditorSavingState) editor.getAttachment(module);

        if (state == null) {
            return true;
        }

        state.setChanged(true);
        editor.getUI().setTitle(state.getFile() + " *");
        return true;
    }

    @Override
    public boolean onClose(Editor editor) {
        EditorSavingState state = (EditorSavingState) editor.getAttachment(module);

        if (state != null && !state.isChanged()) {
            return true;
        }

        int r = JOptionPane.showConfirmDialog(editor.getUI(), "Voulez-vous enregistrer avant de quitter ?");

        try {
            switch (r) {
                case JOptionPane.YES_OPTION:
                    module.save(editor);
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                default:
                    return false;
            }

            return true;
        } catch (Exception ex) {
            JMapEditor.APP.getErrorHandler().showError("Sauvegarde : erreur", ex);
            return false;
        }
    }
}
