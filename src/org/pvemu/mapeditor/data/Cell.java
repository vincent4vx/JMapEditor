/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class Cell{
    private boolean active = true;
    private boolean lineOfSight = true;
    private int layerGroundRot = 0;
    private int groundLevel = 7;
    private int movement = 4;
    private int groundSlope = 1;
    private int layerObject1Rot = 0;
    
    private CellObject ground = null;
    private CellObject layer1 = null;
    private CellObject layer2 = null;

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

    public CellObject getGround() {
        return ground;
    }

    public CellObject getLayer1() {
        return layer1;
    }

    public CellObject getLayer2() {
        return layer2;
    }

    public void setGround(CellObject ground) {
        this.ground = ground;
    }

    public void setLayer1(CellObject layer1) {
        this.layer1 = layer1;
    }

    public void setLayer2(CellObject layer2) {
        this.layer2 = layer2;
    }
}
