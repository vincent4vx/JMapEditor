/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.common;

import java.awt.Color;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Constants {
    final static public String TITLE            = "JMapEditor";
    final static public int DEFAULT_WIDTH       = 15;
    final static public int DEFAULT_HEIGHT      = 17;
    final static public int CELL_WIDTH          = 53;
    final static public int CELL_HEIGHT         = 27;
    final static public double CELL_HALF_WIDTH  = 2.650000E+001;
    final static public double CELL_HALF_HEIGHT = 1.350000E+001;
    final static public int LEVEL_HEIGHT        = 20;
    final static public double[][][] CELL_COORD = new double[][][]{new double[][]{new double[]{}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, 1.350000E+001}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, 0}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, -20}, new double[]{0, -1.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, -6.500000E+000}}, new double[][]{new double[]{-2.650000E+001, 0}, new double[]{0, -3.350000E+001}, new double[]{2.650000E+001, -20}, new double[]{0, -6.500000E+000}}};
    final static public Color GRID_COLOR        = new Color(1f, 1f, 1f, .3f);
    final static public Color BACKGROUND_COLOR  = new Color(30, 30, 30);
    final static public Color SELECTED_COLOR    = new Color(120, 150, 180, 125);
    final static public String RESOURCES_DIR    = "resources/";
    final static public String TILES_DIR        = RESOURCES_DIR + "tiles/";
    final static public String BACKGROUNDS_DIR  = TILES_DIR + "backgrounds/";
    final static public String GROUNDS_DIR      = TILES_DIR + "grounds/";
    final static public String OBJECTS_DIR      = TILES_DIR + "objects/";
    final static public int TILES_PER_LOADER    = 30;
    final static public String UI_RESOURCES_DIR = RESOURCES_DIR + "ui/";
    final static public String SPLASH_IMG       = UI_RESOURCES_DIR + "splashscreen.jpg";
    final static public String JME_EXT          = ".jme";
}
