/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.Math.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

/**
 *
 * @author kajdreef
 */
public class Cube extends GameObject{          
    
    public Cube(float x, float y, float z){
        position = new Vec3(x, y, z);
        init();
    }
    
    public void init(){
        vertexData = new float[] {
				2*position.x + 1.0f, 2*position.y + 1.0f, 2*position.z + -1.0f,
				2*position.x + -1.0f, 2*position.y + 1.0f, 2*position.z + -1.0f,
				2*position.x + -1.0f, 2*position.y + 1.0f, 2*position.z + 1.0f,
				2*position.x + 1.0f, 2*position.y + 1.0f, 2*position.z + 1.0f,
				
				2*position.x + 1.0f, 2*position.y + -1.0f, 2*position.z + 1.0f,
				2*position.x + -1.0f, 2*position.y + -1.0f, 2*position.z + 1.0f,
				2*position.x + -1.0f, 2*position.y + -1.0f, 2*position.z + -1.0f,
				2*position.x + 1.0f, 2*position.y + -1.0f, 2*position.z + -1.0f,
				
				2*position.x + 1.0f, 2*position.y + 1.0f, 2*position.z + 1.0f,
				2*position.x + -1.0f, 2*position.y + 1.0f, 2*position.z + 1.0f,
				2*position.x + -1.0f, 2*position.y + -1.0f, 2*position.z + 1.0f,
				2*position.x + 1.0f, 2*position.y + -1.0f, 2*position.z + 1.0f,
				
				2*position.x + 1.0f, 2*position.y + -1.0f, 2*position.z + -1.0f,
				2*position.x + -1.0f, 2*position.y + -1.0f, 2*position.z + -1.0f,
				2*position.x + -1.0f, 2*position.y + 1.0f, 2*position.z + -1.0f,
				2*position.x + 1.0f, 2*position.y + 1.0f, 2*position.z + -1.0f,
				
				2*position.x + -1.0f, 2*position.y + 1.0f, 2*position.z + 1.0f,
				2*position.x + -1.0f, 2*position.y + 1.0f, 2*position.z + -1.0f,
				2*position.x + -1.0f, 2*position.y + -1.0f, 2*position.z + -1.0f,
				2*position.x + -1.0f, 2*position.y + -1.0f, 2*position.z + 1.0f,
				
				2*position.x + 1.0f, 2*position.y + 1.0f, 2*position.z + -1.0f,
				2*position.x + 1.0f, 2*position.y + 1.0f, 2*position.z + 1.0f,
				2*position.x + 1.0f, 2*position.y + -1.0f, 2*position.z + 1.0f,
				2*position.x + 1.0f, 2*position.y + -1.0f, 2*position.z + -1.0f
        };
        
        colorData = new float[] {   1, 1, 0,
                                    1, 0, 1,
                                    0, 0, 1,
                                    0, 1, 1,
                                    
                                    1, 1, 0,
                                    1, 0, 1,
                                    0, 0, 1,
                                    0, 1, 1,
                                    
                                    1, 1, 0,
                                    1, 0, 1,
                                    0, 0, 1,
                                    0, 1, 1,
                                    
                                    1, 1, 0,
                                    1, 0, 1,
                                    0, 0, 1,
                                    0, 1, 1,
                                    
                                    1, 1, 0,
                                    1, 0, 1,
                                    0, 0, 1,
                                    0, 1, 1,
                                    
                                    1, 1, 0,
                                    1, 0, 1,
                                    0, 0, 1,
                                    0, 1, 1
        };
        
        createVBO();
    }
    
    @Override
    public void updatePosition(int delta) {
        
    }
    
    @Override
    public void render() {
        GL11.glPushMatrix();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOVertexHandle);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOColorHandle);
        GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
        GL11.glDrawArrays(GL11.GL_LINE_LOOP, 0, vertexData.length/3);
        GL11.glPopMatrix();
    }

}
