/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.common;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class Utils {
    static public int getCellNumberBySize(int width, int height){
        return width * (height - 1) * 2 + (width - height + 1);
    }
}
