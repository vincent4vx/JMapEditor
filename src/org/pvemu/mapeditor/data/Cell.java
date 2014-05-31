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
    private int layerGroundNum = 0;
    private int groundSlope = 1;
    private boolean layerGroundFlip = false;
    private int layerObject1Num = 0;
    private int layerObject1Rot = 0;
    private boolean layerObject1Flip = false;
    private boolean layerObject2Flip = false;
    private boolean layerObject2Interactive = false;
    private int layerObject2Num = 0;

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

    public int getLayerGroundNum() {
        return layerGroundNum;
    }

    public void setLayerGroundNum(int layerGroundNum) {
        this.layerGroundNum = layerGroundNum;
    }

    public int getGroundSlope() {
        return groundSlope;
    }

    public void setGroundSlope(int groundSlope) {
        this.groundSlope = groundSlope;
    }

    public boolean isLayerGroundFlip() {
        return layerGroundFlip;
    }

    public void setLayerGroundFlip(boolean layerGroundFlip) {
        this.layerGroundFlip = layerGroundFlip;
    }

    public int getLayerObject1Num() {
        return layerObject1Num;
    }

    public void setLayerObject1Num(int layerObject1Num) {
        this.layerObject1Num = layerObject1Num;
    }

    public int getLayerObject1Rot() {
        return layerObject1Rot;
    }

    public void setLayerObject1Rot(int layerObject1Rot) {
        this.layerObject1Rot = layerObject1Rot;
    }

    public boolean isLayerObject1Flip() {
        return layerObject1Flip;
    }

    public void setLayerObject1Flip(boolean layerObject1Flip) {
        this.layerObject1Flip = layerObject1Flip;
    }

    public boolean isLayerObject2Flip() {
        return layerObject2Flip;
    }

    public void setLayerObject2Flip(boolean layerObject2Flip) {
        this.layerObject2Flip = layerObject2Flip;
    }

    public boolean isLayerObject2Interactive() {
        return layerObject2Interactive;
    }

    public void setLayerObject2Interactive(boolean layerObject2Interactive) {
        this.layerObject2Interactive = layerObject2Interactive;
    }

    public int getLayerObject2Num() {
        return layerObject2Num;
    }

    public void setLayerObject2Num(int layerObject2Num) {
        this.layerObject2Num = layerObject2Num;
    }
}
