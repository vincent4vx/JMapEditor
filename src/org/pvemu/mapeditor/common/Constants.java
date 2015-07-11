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

import java.awt.Color;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Constants {
    final static public String TITLE            = "JMapEditor";
    final static public double[][][] CELL_COORD = new double[][][]{new double[][]{new double[]{}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, -6.500000E+000}}};
    final static public Color GRID_COLOR        = new Color(1f, 1f, 1f, .3f);
    final static public Color BACKGROUND_COLOR  = new Color(30, 30, 30);
    final static public Color SELECTED_COLOR    = new Color(120, 150, 180, 125);
    final static public Color UNWALKABLE_COLOR  = new Color(.8f, 0, 0, .7f);
    final static public Color SIGHT_BLOCK_COLOR = new Color(.8f, .8f, 0, .7f);
    final static public String PARAMETERS_DB    = "parameters.db";
    final static public String CONFIG_TABLE     = "CONFIG";
    final static public String REGISTRY_TABLE   = "REGISTRY";
}
