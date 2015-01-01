/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Math;

/**
 *
 * @author kajdreef
 */
public class Vec3 {
    public float x, y, z;
    
    /**
     * Initialize a new 3 dimensional Vector
     * @param x
     * @param y
     * @param z 
     */
    public Vec3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Initialize a new vector with zeroes.
     */
    public Vec3(){
        this(0,0,0);
    }
    
    /**
     * Copy the given vector
     * @param vector 
     */
    public Vec3(Vec3 vector){
        this(vector.x, vector.y, vector.z);
    }
    
    /** Creates a copy of this vector.
     * 
     * @return 
     */
    public Vec3 copy() {
        return new Vec3(this);
    }
    
    public void set(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(Vec3 vector){
        set(vector.x, vector.y, vector.z);
    }
    
    /**
     * Calculate the new value of the vector after adding the parameter
     * by the original vector.
     * 
     * @param x
     * @param y
     * @param z
     */
    public void add(float x, float y, float z){
        this.x += x;
        this.y += y;
        this.z += z;
    }
    
    /**
     * Calculate the new value of the vector after adding the parameter
     * by the original vector.
     * 
     * @param vector
     */
    public void plus(Vec3 vector){
        add(vector.x, vector.y, vector.z);
    }
    
    /**
     * Calculate the new value of the vector after subtracting the parameter
     * from the original vector.
     * 
     * @param x
     * @param y
     * @param z
     */
    public void minus(float x, float y, float z){
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }
    
    /**
     * Calculate the new value of the vector after subtracting the parameter
     * from the original vector.
     * 
     * @param vector 
     */
    public void minus(Vec3 vector){
        minus(vector.x, vector.y, vector.z);
    }
    
    //TODO implement scale
    public void scale(float x, float y, float z){}
    public void scale(Vec3 vector){}
    
    //TODO implement translate
    public void translate(float x, float y, float z){}
    public void translate(Vec3 vector){}   
    
    //TODO implement rotate
    public void rotate(float x, float y, float z){}
    public void rotate(Vec3 vector){}

    /**
     * Calculate the dot product of the two vectors.
     * 
     * @param vector
     * @return result of the dot product (which is an Integer)
     */
    public float dot(Vec3 vector){
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }
    
    /**
     * Calculate the cross product of the two vectors.
     * @param vector
     * @return result of the cross vector
     */
    public Vec3 cross(Vec3 vector){
         return new Vec3(
                y * vector.z - z * vector.y,
                z * vector.x - x * vector.z,
                x * vector.y - y * vector.x);
    }
}
