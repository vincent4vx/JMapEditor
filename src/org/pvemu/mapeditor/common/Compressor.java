/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.common;

import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.MapData;

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
    
    public static Cell decompressCell(String cellData){
        Cell cell = new Cell();
        
        byte[] _loc8 = new byte[cellData.length()];
        
        for(int i = 0; i < cellData.length(); ++i){
            _loc8[i] = getHashIndex(cellData.charAt(i));
        }
        
        cell.setLineOfSight((_loc8[0] & 1) == 1);
        cell.setLayerGroundRot((_loc8[1] & 48) >> 4);
        cell.setGroundLevel(_loc8[1] & 15);
        cell.setMovement((_loc8[2] & 56) >> 3);
        cell.setLayerGroundNum(((_loc8[0] & 24) << 6) + ((_loc8[2] & 7) << 6) + _loc8[3]);
        cell.setGroundSlope((_loc8[4] & 60) >> 2);
        cell.setLayerGroundFlip(((_loc8[4] & 2) >> 1) == 1);
        cell.setLayerObject1Num(((_loc8[0] & 4) << 11) + ((_loc8[4] & 1) << 12) + (_loc8[5] << 6) + _loc8[6]);
        cell.setLayerObject1Rot((_loc8[7] & 48) >> 4);
        cell.setLayerObject1Flip((_loc8[7] & 8) >> 3 == 1);
        cell.setLayerObject2Flip((_loc8[7] & 4) >> 2 == 1);
        cell.setLayerObject2Interactive((_loc8[7] & 2) >> 1 == 1);
        cell.setLayerObject2Num(((_loc8[0] & 2) << 12) + ((_loc8[7] & 1) << 12) + (_loc8[8] << 6) + _loc8[9]);
        
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
    
    static public String compressCell(Cell cell){
        int[] _loc4 = new int[10];
        _loc4[0] = (1) << 5;
        _loc4[0] = _loc4[0] | (cell.isLineOfSight() ? (1) : (0));
        _loc4[0] = _loc4[0] | (cell.getLayerGroundNum() & 1536) >> 6;
        _loc4[0] = _loc4[0] | (cell.getLayerObject1Num() & 8192) >> 11;
        _loc4[0] = _loc4[0] | (cell.getLayerObject2Num() & 8192) >> 12;
        _loc4[1] = (cell.getLayerGroundRot() & 3) << 4;
        _loc4[1] = _loc4[1] | cell.getGroundLevel() & 15;
        _loc4[2] = (cell.getMovement() & 7) << 3;
        _loc4[2] = _loc4[2] | cell.getLayerGroundNum() >> 6 & 7;
        _loc4[3] = cell.getLayerGroundNum() & 63;
        _loc4[4] = (cell.getGroundSlope() & 15) << 2;
        _loc4[4] = _loc4[4] | (cell.isLayerGroundFlip() ? (1) : (0)) << 1;
        _loc4[4] = _loc4[4] | cell.getLayerObject1Num() >> 12 & 1;
        _loc4[5] = cell.getLayerObject1Num() >> 6 & 63;
        _loc4[6] = cell.getLayerObject1Num() & 63;
        _loc4[7] = (cell.getLayerObject1Rot() & 3) << 4;
        _loc4[7] = _loc4[7] | (cell.isLayerObject1Flip() ? (1) : (0)) << 3;
        _loc4[7] = _loc4[7] | (cell.isLayerObject2Flip() ? (1) : (0)) << 2;
        _loc4[7] = _loc4[7] | (cell.isLayerObject2Interactive() ? (1) : (0)) << 1;
        _loc4[7] = _loc4[7] | cell.getLayerObject2Num() >> 12 & 1;
        _loc4[8] = cell.getLayerObject2Num() >> 6 & 63;
        _loc4[9] = cell.getLayerObject2Num() & 63;
        
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
