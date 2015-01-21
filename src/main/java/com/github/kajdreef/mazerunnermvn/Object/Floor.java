package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.Math.Vec3;
import com.github.kajdreef.mazerunnermvn.Util.Textures;
import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author kajdreef
 */
public class Floor extends GameObject{          
    
    Texture texture;
    
    public Floor(float x, float y, float z){
        position = new Vec3(x, y, z);
        init();
    }
    
    public void init(){
        
        texture = Textures.getTexture("brick.png");
        
        vertexData = new float[] {
            position.x,position.y, position.z,
            position.x,position.y, 0,
            0,position.y, 0,

            0,position.y, 0,
            0,position.y, position.z,
            position.x,position.y, position.z
        };
        
        textureData = new float[] {
            0,0,
            1,0,
            1,1,
            
            1,1,
            0,1,
            0,0
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

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOVertexHandle);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,VBOTextureHandle);
        GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0L);
        
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexData.length/3);
        
        GL11.glPopMatrix();
    }

}
