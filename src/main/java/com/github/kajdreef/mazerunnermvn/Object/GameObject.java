/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.Math.Vec3;
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
                    VBOColorHandle,
                    VBONormalHandle,
                    VBOIndexHandle;
    
    protected FloatBuffer   cubeVertexBuffer,
                            cubeColorBuffer,
                            cubeNormalBuffer,
                            cubeIndexBuffer;
    
    protected float[]   vertexData,
                        colorData;
    /**
     * Update the position of the object with delta as the time that has passed
     * since last frame.
     * 
     * @param delta
     */
    public abstract void updatePosition(int delta);
    
    protected void createVBO(){
        VBOColorHandle = GL15.glGenBuffers();
        VBOVertexHandle = GL15.glGenBuffers();
        
        cubeVertexBuffer = BufferUtils.createFloatBuffer(vertexData.length);
        cubeVertexBuffer.put(vertexData);
        cubeVertexBuffer.flip();
        
        cubeColorBuffer = BufferUtils.createFloatBuffer(colorData.length);
        cubeColorBuffer.put(colorData);
        cubeColorBuffer.flip();
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, cubeVertexBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, cubeColorBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    /**
     * Render the Object.
     */
    public abstract void render();
    
}
