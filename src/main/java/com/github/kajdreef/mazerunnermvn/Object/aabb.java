/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Object;

import org.lwjgl.util.vector.Vector3f;

/**
 * TODO implement Axis-Aligned Bounding Box collision
 * @author kajdreef
 */
public interface aabb {
    float getX();
    float getZ();
    float getWidth();
    float getHeight();
    boolean detectCollision(aabb obj);
}
