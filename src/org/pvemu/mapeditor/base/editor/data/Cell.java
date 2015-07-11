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
package org.pvemu.mapeditor.base.editor.data;

import org.pvemu.mapeditor.hierarchy.CellObject;
import java.util.EnumMap;
import org.pvemu.mapeditor.hierarchy.LayerSet;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Cell{
    final private int id;
    private boolean active = true;
    private boolean lineOfSight = true;
    private int layerGroundRot = 0;
    private int groundLevel = 7;
    private int movement = 4;
    private int groundSlope = 1;
    private int layerObject1Rot = 0;
    
    final private EnumMap<LayerSet, CellObject> objects = new EnumMap<>(LayerSet.class);

    public Cell(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLineOfSight() {
        return lineOfSight;
    }

    public void setLineOfSight(boolean lineOfSight) {
        this.lineOfSight = lineOfSight;
    }

    public int getLayerGroundRot() {
        return layerGroundRot;
    }

    public void setLayerGroundRot(int layerGroundRot) {
        this.layerGroundRot = layerGroundRot;
    }

    public int getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(int groundLevel) {
        this.groundLevel = groundLevel;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getGroundSlope() {
        return groundSlope;
    }

    public void setGroundSlope(int groundSlope) {
        this.groundSlope = groundSlope;
    }

    public int getLayerObject1Rot() {
        return layerObject1Rot;
    }

    public void setLayerObject1Rot(int layerObject1Rot) {
        this.layerObject1Rot = layerObject1Rot;
    }
    
    public CellObject getObjectAt(LayerSet layer){
        return objects.get(layer);
    }
    
    public void setObjectAt(LayerSet layer, CellObject obj){
        objects.put(layer, obj);
    }
    
    public void removeObjectAt(LayerSet layer){
        objects.remove(layer);
    }

    public CellObject getGround() {
        return objects.get(LayerSet.GROUND);
    }

    public CellObject getLayer1() {
        return objects.get(LayerSet.LAYER1);
    }

    public CellObject getLayer2() {
        return objects.get(LayerSet.LAYER2);
    }

    public void setGround(CellObject ground) {
        objects.put(LayerSet.GROUND, ground);
    }

    public void setLayer1(CellObject layer1) {
        objects.put(LayerSet.LAYER1, layer1);
    }

    public void setLayer2(CellObject layer2) {
        objects.put(LayerSet.LAYER2, layer2);
    }
    
    public void copy(Cell cell){
        active = cell.active;
        lineOfSight = cell.lineOfSight;
        layerGroundRot = cell.layerGroundRot;
        layerObject1Rot = cell.layerObject1Rot;
        objects.clear();
        objects.putAll(cell.objects);
        groundLevel = cell.groundLevel;
        groundSlope = cell.groundSlope;
        movement = cell.movement;
    }

    public int getId() {
        return id;
    }
}
