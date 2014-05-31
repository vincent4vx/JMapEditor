/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.io.File;
import java.io.IOException;
import org.pvemu.mapeditor.common.Constants;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Backgrounds extends TilesList{

    public Backgrounds() throws IOException {
        super(new File(Constants.BACKGROUNDS_DIR));
    }

    @Override
    protected TileType tileType() {
        return TileType.BACKGROUND;
    }
    
}
