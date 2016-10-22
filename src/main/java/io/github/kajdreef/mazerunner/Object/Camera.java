/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kajdreef.mazerunner.Object;

import io.github.kajdreef.mazerunner.Input.abstractInput;
import io.github.kajdreef.mazerunner.Input.Button;
import io.github.kajdreef.mazerunner.MazeRunner.ShaderProgram;
import io.github.kajdreef.mazerunner.Util.Logger;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Player class contains the location and viewing direction of the player.
 * 
 * @author kajdreef
 */
public class Camera implements aabb {
    private Logger log = Logger.getInstance();
    private Vector3f position;
    private Vector3f newPosition;
    private Vector3f scale = new Vector3f(.2f,.2f,.2f);
    private Matrix4f viewMatrix;
    
    private static float xRotation = 0, yRotation = 0;
    
    private final float walkingSpeed = 0.025f;
    private final float mouseSensitivity = 10.0f;
    
    public Camera(float posX, float posY, float posZ, float dirX, float dirY){
        position = new Vector3f();
        newPosition = new Vector3f();
        
        this.position.x = posX;
        this.position.y = posY;
        this.position.z = posZ;
        
        this.newPosition.x = posX;
        this.newPosition.y = posY;
        this.newPosition.z = posZ;
        
        this.xRotation = dirX;
        this.yRotation = dirY;
        
        // initialise viewMatrix;
        viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.translate(position, viewMatrix, viewMatrix);
        Matrix4f.rotate(xRotation, new Vector3f(0,1,0), viewMatrix, viewMatrix);
        Matrix4f.rotate(yRotation, new Vector3f(1,0,0), viewMatrix, viewMatrix);
    }
    
    public void newLocation(){
        xRotation += (abstractInput.dy/mouseSensitivity);
        yRotation += (abstractInput.dx/mouseSensitivity);
        
        newPosition.set(position);
        
        if(Button.BUTTON_UP.isPressed()){
            newPosition.setX(newPosition.getX() - walkingSpeed * (float) Math.sin(Math.toRadians(yRotation)));
            newPosition.setZ(newPosition.getZ() + walkingSpeed * (float) Math.cos(Math.toRadians(yRotation)));
        }
        if(Button.BUTTON_DOWN.isPressed()){
            newPosition.setX(newPosition.getX() + walkingSpeed * (float) Math.sin(Math.toRadians(yRotation)));
            newPosition.setZ(newPosition.getZ() - walkingSpeed * (float) Math.cos(Math.toRadians(yRotation)));
        }
        if(Button.BUTTON_LEFT.isPressed()){
            newPosition.setX(newPosition.getX() - walkingSpeed * (float) Math.sin(Math.toRadians(yRotation-90)));
            newPosition.setZ(newPosition.getZ() + walkingSpeed * (float) Math.cos(Math.toRadians(yRotation-90)));
        }
        if(Button.BUTTON_RIGHT.isPressed()){
            newPosition.setX(newPosition.getX() - walkingSpeed * (float) Math.sin(Math.toRadians(yRotation+90)));
            newPosition.setZ(newPosition.getZ() + walkingSpeed * (float) Math.cos(Math.toRadians(yRotation+90)));
        }        
    }
    
    public void update(float delta, boolean collision){
        viewMatrix.setIdentity();
        
        if(!collision)
            position.set(newPosition);
        
        Matrix4f.rotate((float)Math.toRadians(xRotation), new Vector3f(1,0,0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float)Math.toRadians(yRotation), new Vector3f(0,1,0), viewMatrix, viewMatrix);
        
        Matrix4f.translate(position, viewMatrix, viewMatrix);
        
        GL20.glUseProgram(ShaderProgram.getProgram());
        
        FloatBuffer matrix44Buffer = BufferUtils.createFloatBuffer(16);
        viewMatrix.store(matrix44Buffer); 
        matrix44Buffer.flip();
        GL20.glUniformMatrix4(ShaderProgram.getVML(), false, matrix44Buffer);
        
        GL20.glUseProgram(0);
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

    @Override
    public float getX(){
        return position.getX();
    }
    
    @Override
    public float getZ(){
        return position.getZ();
    }
    
    @Override
    public float getWidth(){
        return scale.getX();
    }
    
    @Override
    public float getHeight(){
        return scale.getZ();
    }
    
    @Override
    public boolean detectCollision(aabb obj){
       return obj.getWidth() > 0 && obj.getHeight() > 0 && scale.getX() > 0 && scale.getZ() > 0 &&
       obj.getX() < -newPosition.getX() + this.getWidth() && obj.getX() + obj.getWidth() > -newPosition.getX() - this.getWidth()
       && obj.getZ() < -newPosition.getZ() + this.getHeight() && obj.getZ() + obj.getWidth() > -newPosition.getZ() - this.getHeight();
    }
}