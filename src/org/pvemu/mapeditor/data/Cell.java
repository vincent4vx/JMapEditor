/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

import java.util.EnumMap;
import org.pvemu.mapeditor.handler.layer.Layer;

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
    
    final private EnumMap<Layer, CellObject> objects = new EnumMap<>(Layer.class);

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
    
    public CellObject getObjectAt(Layer layer){
        return objects.get(layer);
    }
    
    public void setObjectAt(Layer layer, CellObject obj){
        objects.put(layer, obj);
    }
    
    public void removeObjectAt(Layer layer){
        objects.remove(layer);
    }

    public CellObject getGround() {
        return objects.get(Layer.GROUND);
    }

    public CellObject getLayer1() {
        return objects.get(Layer.LAYER1);
    }

    public CellObject getLayer2() {
        return objects.get(Layer.LAYER2);
    }

    public void setGround(CellObject ground) {
        objects.put(Layer.GROUND, ground);
    }

    public void setLayer1(CellObject layer1) {
        objects.put(Layer.LAYER1, layer1);
    }

    public void setLayer2(CellObject layer2) {
        objects.put(Layer.LAYER2, layer2);
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
