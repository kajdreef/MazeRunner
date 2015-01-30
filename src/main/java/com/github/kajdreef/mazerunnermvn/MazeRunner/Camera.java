/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.MazeRunner;

import com.github.kajdreef.mazerunnermvn.Input.abstractInput;
import com.github.kajdreef.mazerunnermvn.Object.aabb;
import com.github.kajdreef.mazerunnermvn.Input.Button;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Player class contains the location and viewing direction of the player.
 * 
 * @author kajdreef
 */
public class Camera implements aabb {
    private Vector3f position;
    private Vector3f scale = new Vector3f(1f,1f,1f);
    private Matrix4f viewMatrix;
    
    private static float xRotation = 0, yRotation = 0;
    
    private final float walkingSpeed = 0.05f;
    private final float mouseSensitivity = 10.0f;
    
    public Camera(float posX, float posY, float posZ, float dirX, float dirY){
        position = new Vector3f();
        
        this.position.x = posX;
        this.position.y = posY;
        this.position.z = posZ;
        
        this.xRotation = dirX;
        this.yRotation = dirY;
        
        // initialise viewMatrix;
        viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.translate(position, viewMatrix, viewMatrix);
        Matrix4f.rotate(xRotation, new Vector3f(0,1,0), viewMatrix, viewMatrix);
        Matrix4f.rotate(yRotation, new Vector3f(1,0,0), viewMatrix, viewMatrix);
    }
    
    public Matrix4f update(float delta){
        viewMatrix.setIdentity();
        
        xRotation += (abstractInput.dy/mouseSensitivity);
        yRotation += (abstractInput.dx/mouseSensitivity);
        
        if(Button.BUTTON_UP.isPressed()){
            position.x-=walkingSpeed * (float) Math.sin(Math.toRadians(yRotation));
            position.z+=walkingSpeed * (float) Math.cos(Math.toRadians(yRotation));
        }
        if(Button.BUTTON_DOWN.isPressed()){
            position.x+=walkingSpeed * (float) Math.sin(Math.toRadians(yRotation));
            position.z-=walkingSpeed * (float) Math.cos(Math.toRadians(yRotation));
        }
        if(Button.BUTTON_LEFT.isPressed()){
            position.x-=walkingSpeed * (float) Math.sin(Math.toRadians(yRotation-90));
            position.z+=walkingSpeed * (float) Math.cos(Math.toRadians(yRotation-90));
        }
        if(Button.BUTTON_RIGHT.isPressed()){
            position.x-=walkingSpeed * (float) Math.sin(Math.toRadians(yRotation+90));
            position.z+=walkingSpeed * (float) Math.cos(Math.toRadians(yRotation+90));
        }      
        
        Matrix4f.rotate((float)Math.toRadians(xRotation), new Vector3f(1,0,0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float)Math.toRadians(yRotation), new Vector3f(0,1,0), viewMatrix, viewMatrix);
        
        Matrix4f.translate(position, viewMatrix, viewMatrix);
        
        return viewMatrix;
    }
    
    public Vector3f getPosition(){
        return position;
    }
    
    public static float getXRotation(){
        return xRotation;
    }
    
    public static float getYRotation(){
        return yRotation;
    }
}
