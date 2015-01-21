/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.Math.Vec3;
import com.github.kajdreef.mazerunnermvn.Util.Textures;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author kajdreef
 */
public class Cube extends GameObject{          
    
    Texture texture;
    
    public Cube(float x, float y, float z){
        position = new Vec3(x, y, z);
        init();
    }
    
    public void init(){
        texture = Textures.loadTexture("brick.png");
        
        vertexData = new float[] {            
            // Back
            2*position.x+1.0f,2*position.y+1.0f,2*position.z-1.0f,      // 1,1,-1
            2*position.x+1.0f,2*position.y-1.0f,2*position.z-1.0f,      // 1,-1,-1
            2*position.x-1.0f,2*position.y+1,2*position.z-1.0f,         // -1,+1,-1
            
            2*position.x-1,2*position.y+1.0f,2*position.z-1.0f,         // -1, 1, -1
            2*position.x+1,2*position.y-1.0f,2*position.z-1.0f,         // 1,-1,-1
            2*position.x-1,2*position.y-1.0f,2*position.z-1.0f,         // -1,-1,-1
            
            // Front
            2*position.x-1.0f,2*position.y+1.0f,2*position.z+1.0f,      // -1, 1, 1
            2*position.x-1.0f,2*position.y-1,2*position.z+1.0f,         // -1,-1,1
            2*position.x+1.0f,2*position.y+1.0f,2*position.z+1.0f,      // 1, 1, 1
            
            2*position.x+1,2*position.y+1.0f,2*position.z+1.0f,         // 1,1,1
            2*position.x-1,2*position.y-1.0f,2*position.z+1.0f,         // -1,-1,1
            2*position.x+1,2*position.y-1.0f,2*position.z+1.0f,         //1,-1,1
            
            // Left
            2*position.x-1.0f,2*position.y+1.0f,2*position.z-1.0f,      // -1,1,-1
            2*position.x-1.0f,2*position.y-1.0f,2*position.z-1.0f,      // -1,-1,-1
            2*position.x-1.0f,2*position.y+1,2*position.z+1.0f,         // -1,1,1

            2*position.x-1.0f,2*position.y+1,2*position.z+1.0f,         // -1,1,1
            2*position.x-1.0f,2*position.y-1.0f,2*position.z-1.0f,      // -1,-1,-1
            2*position.x-1.0f,2*position.y-1,2*position.z+1.0f,         // -1,-1,1
            
            // Right
            2*position.x+1.0f,2*position.y+1.0f,2*position.z+1.0f,      // 1,1,1
            2*position.x+1.0f,2*position.y-1,2*position.z+1.0f,         // 1,-1,1
            2*position.x+1.0f,2*position.y+1.0f,2*position.z-1.0f,      // 1,1,-1

            2*position.x+1.0f,2*position.y+1.0f,2*position.z-1.0f,      // 1,1,-1
            2*position.x+1.0f,2*position.y-1,2*position.z+1.0f,         // 1,-1,1
            2*position.x+1.0f,2*position.y-1.0f,2*position.z-1.0f,      // 1,-1,-1
        };
        
        textureData = new float[] {
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,
            
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,
            
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,
            
            0.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 1.0f,
            
            1.0f, 1.0f,
            0.0f, 0.0f,
            1.0f, 0.0f,
        };

        createVBO();
    }
    
    @Override
    public void updatePosition(int delta) {
        
    }
    
    @Override
    public void render() {
        GL11.glPushMatrix();
        
        texture.bind();
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOTextureHandle);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0L);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexData.length/3);
                
        GL11.glPopMatrix();
    }
}
