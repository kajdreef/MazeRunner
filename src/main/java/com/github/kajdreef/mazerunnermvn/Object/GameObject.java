/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.Math.Vec3;
import com.github.kajdreef.mazerunnermvn.MazeRunner.Camera;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

/**
 *
 * @author kajdreef
 */
public abstract class GameObject implements aabb{
    
    protected Vec3 position = null;
    
    protected int   VBOVertexHandle,
                    VBOTextureHandle;
    
    protected FloatBuffer   cubeVertexBuffer,
                            cubeTextureBuffer;
    
    protected float[]   vertexData,
                        textureData;
    
    /**
     * Update the position of the object with delta as the time that has passed
     * since last frame.
     * 
     * @param delta
     */
    public abstract void updatePosition(int delta);
    
    protected void createVBO(){
        cubeVertexBuffer = BufferUtils.createFloatBuffer(vertexData.length);
        cubeVertexBuffer.put(vertexData);
        cubeVertexBuffer.flip();
        
        cubeTextureBuffer = BufferUtils.createFloatBuffer(textureData.length);
        cubeTextureBuffer.put(textureData);
        cubeTextureBuffer.flip();
        
        VBOVertexHandle = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, cubeVertexBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        VBOTextureHandle = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOTextureHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, cubeTextureBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    /**
     * Render the Object.
     */
    public abstract void render();
    
    public boolean collision(Camera player){        
        return false;
    }
}
