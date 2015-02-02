package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.Object.Vertex.Vertex;
import com.github.kajdreef.mazerunnermvn.Util.Textures;
import com.github.kajdreef.mazerunnermvn.Util.Logger;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Vector3f;


/**
 *
 * @author kajdreef
 */
public class Roof extends GameObject{          
    private Logger log = Logger.getInstance();
    
    public Roof(float scaleX, float scaleZ){
        scale.x=scaleX;
        scale.z=scaleZ;
        
        init();
    }
    
    public void init(){
        Vertex v0 = new Vertex(); 
        v0.setXYZ(0f, 1f, 1.f); v0.setRGB(0, 1, 0); v0.setST(0, 0);
        Vertex v1 = new Vertex(); 
        v1.setXYZ(1.f, 1f, 1.f); v1.setRGB(0, 0, 1); v1.setST(1, 0);
        Vertex v2 = new Vertex(); 
        v2.setXYZ(1.f, 1f, 0f); v2.setRGB(0, 1, 0); v2.setST(1, 1);
        Vertex v3 = new Vertex(); 
        v3.setXYZ(0f, 1f, 0f); v3.setRGB(0, 0, 1); v3.setST(0, 1);
        
        vertices = new Vertex[] {v0, v1, v2, v3};
        
        indicesData = new byte[]{
            // front
            1,0,3,
            3,2,1
        };
        
        createVBO();
        
        // Get texture from the HashMap and place it in the right texture unit
        textureDiff = Textures.getTexture("brickwall.png");   
        if(textureDiff == null){
            log.logError("Error loading texture!");
        }
        setTextureUnit(textureDiff, GL13.GL_TEXTURE0);
        createVBO();
    }
}
