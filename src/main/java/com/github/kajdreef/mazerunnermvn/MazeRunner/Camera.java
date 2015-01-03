/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.MazeRunner;

import com.github.kajdreef.mazerunnermvn.Input.abstractInput;
import com.github.kajdreef.mazerunnermvn.Math.Vec3;
import com.github.kajdreef.mazerunnermvn.Object.aabb;
import com.github.kajdreef.mazerunnermvn.Input.Button;
import org.lwjgl.opengl.GL11;

/**
 * Player class contains the location and viewing direction of the player.
 * 
 * @author kajdreef
 */
public class Camera implements aabb {
    public Vec3 position;
    
    public float xRotation = 0, yRotation = 0;
    
    private float speed = 0.1f;
    private float walkingSpeed = 0.1f;
    private float mouseSensitivity = 10.0f;
    
    public Camera(float posX, float posY, float posZ, float dirX, float dirY){
        position = new Vec3();
        
        this.position.x = posX;
        this.position.y = posY;
        this.position.z = posZ;
        
        this.xRotation = dirX;
        this.yRotation = dirY;
    }
    
    public void update(float delta){        
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
        //roatate the pitch around the X axis (Look up/down)
        GL11.glRotatef(xRotation, 1.0f, 0.0f, 0.0f);
        
        //roatate the yaw around the Y axis (Look left/right)
        GL11.glRotatef(yRotation, 0.0f, 1.0f, 0.0f);
        
        //translate to the position vector's location
        GL11.glTranslatef(position.x, position.y, position.z);
    }
}
