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
package org.pvemu.mapeditor.common;

import java.util.ArrayList;
import java.util.List;
import org.pvemu.mapeditor.JMapEditor;
import org.pvemu.mapeditor.base.editor.data.Cell;
import org.pvemu.mapeditor.hierarchy.CellObject;
import org.pvemu.mapeditor.base.editor.data.MapData;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class Compressor {
    public final static char[] HASH = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
        't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
        'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
    };
    
    public static Cell decompressCell(int index, String cellData){
        Cell cell = new Cell(index);
        
        byte[] _loc8 = new byte[cellData.length()];
        
        for(int i = 0; i < cellData.length(); ++i){
            _loc8[i] = getHashIndex(cellData.charAt(i));
        }
        
        CellObject ground = CellObject.getCellObject(
                ((_loc8[0] & 24) << 6) + ((_loc8[2] & 7) << 6) + _loc8[3], 
                ((_loc8[4] & 2) >> 1) == 1, 
                false, 
                JMapEditor.APP.getTilesHandler().getGrounds()
        );
        
        CellObject layer1 = CellObject.getCellObject(
                ((_loc8[0] & 4) << 11) + ((_loc8[4] & 1) << 12) + (_loc8[5] << 6) + _loc8[6], 
                (_loc8[7] & 8) >> 3 == 1, 
                false, 
                JMapEditor.APP.getTilesHandler().getObjects()
        );
        
        CellObject layer2 = CellObject.getCellObject(
                ((_loc8[0] & 2) << 12) + ((_loc8[7] & 1) << 12) + (_loc8[8] << 6) + _loc8[9], 
                (_loc8[7] & 4) >> 2 == 1, 
                (_loc8[7] & 2) >> 1 == 1, 
                JMapEditor.APP.getTilesHandler().getObjects()
        );
        
        cell.setActive(((_loc8[0] & 32) >> 5) != 0);
        
        cell.setLineOfSight((_loc8[0] & 1) == 1);
        cell.setLayerGroundRot((_loc8[1] & 48) >> 4);
        cell.setGroundLevel(_loc8[1] & 15);
        cell.setMovement((_loc8[2] & 56) >> 3);
        cell.setGroundSlope((_loc8[4] & 60) >> 2);
        cell.setLayerObject1Rot((_loc8[7] & 48) >> 4);
        
        cell.setGround(ground);
        cell.setLayer1(layer1);
        cell.setLayer2(layer2);
        
        return cell;
    }

    public static byte getHashIndex(char c) {
        if(c >= 'a' && c <= 'z'){
            return (byte)(c - 'a');
        }
        if(c >= 'A' && c <= 'Z'){
            return (byte)(c - 'A' + 26);
        }
        if(c >= '0' && c <= '9'){
            return (byte)(c - '0' + 52);
        }
        if(c == '-'){
            return 62;
        }
        if(c == '_'){
            return 63;
        }

        return -1;
    }
    
    static public Cell[] decompressMapData(String mapData){
        List<Cell> cells = new ArrayList<>();        

        for(int i = 0; i < mapData.length(); i += 10){
            String cellData = mapData.substring(i, i + 10);
            cells.add(decompressCell(i / 10, cellData));
        }
        
        return cells.toArray(new Cell[0]);
    }
    
    static public String compressCell(Cell cell){
        CellObject ground = cell.getGround();
        CellObject layer1 = cell.getLayer1();
        CellObject layer2 = cell.getLayer2();
        
        int groundNum = ground == null ? 0 : ground.getTile().getId();
        int layer1Num = layer1 == null ? 0 : layer1.getTile().getId();
        int layer2Num = layer2 == null ? 0 : layer2.getTile().getId();
        
        boolean groundFilp = ground == null ? false : ground.isFlip();
        boolean layer1Flip = layer1 == null ? false : layer1.isFlip();
        boolean layer2Flip = layer2 == null ? false : layer2.isFlip();
        
        boolean layer2Interactive = layer2 == null ? false : layer2.isInteractive();
        
        int[] _loc4 = new int[10];
        _loc4[0] = (cell.isActive() ? 1 : 0) << 5;
        _loc4[0] = _loc4[0] | (cell.isLineOfSight() ? (1) : (0));
        _loc4[0] = _loc4[0] | (groundNum & 1536) >> 6;
        _loc4[0] = _loc4[0] | (layer1Num & 8192) >> 11;
        _loc4[0] = _loc4[0] | (layer2Num & 8192) >> 12;
        _loc4[1] = (cell.getLayerGroundRot() & 3) << 4;
        _loc4[1] = _loc4[1] | cell.getGroundLevel() & 15;
        _loc4[2] = (cell.getMovement() & 7) << 3;
        _loc4[2] = _loc4[2] | groundNum >> 6 & 7;
        _loc4[3] = groundNum & 63;
        _loc4[4] = (cell.getGroundSlope() & 15) << 2;
        _loc4[4] = _loc4[4] | (groundFilp ? (1) : (0)) << 1;
        _loc4[4] = _loc4[4] | layer1Num >> 12 & 1;
        _loc4[5] = layer1Num >> 6 & 63;
        _loc4[6] = layer1Num & 63;
        _loc4[7] = (cell.getLayerObject1Rot() & 3) << 4;
        _loc4[7] = _loc4[7] | (layer1Flip ? (1) : (0)) << 3;
        _loc4[7] = _loc4[7] | (layer2Flip ? (1) : (0)) << 2;
        _loc4[7] = _loc4[7] | (layer2Interactive ? (1) : (0)) << 1;
        _loc4[7] = _loc4[7] | layer2Num >> 12 & 1;
        _loc4[8] = layer2Num >> 6 & 63;
        _loc4[9] = layer2Num & 63;
        
        StringBuilder sb = new StringBuilder(10);
        
        for(int i : _loc4){
            sb.append(HASH[i]);
        }
        
        return sb.toString();
    }
    
    public static String compressMapData(MapData map){
        StringBuilder sb = new StringBuilder(map.size() * 10);
        
        for(Cell cell : map){
            sb.append(compressCell(cell));
        }
        
        return sb.toString();
   }
}
