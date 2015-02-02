/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.Object.Vertex.Vertex;
import com.github.kajdreef.mazerunnermvn.Util.Logger;
import com.github.kajdreef.mazerunnermvn.Util.Textures;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author kajdreef
 */
public class Cube extends GameObject{          
    private Logger log = Logger.getInstance();
    
    public Cube(float x, float y, float z){
        position = new Vector3f(x, y, z);
        init();
    }
    
    public void init(){
        Vertex v0 = new Vertex(); 
        v0.setXYZ(0f, 1f, 1f); v0.setRGB(1, 0, 0);  v0.setST(0, 0);
        Vertex v1 = new Vertex(); 
        v1.setXYZ(0f, 0f, 1f); v1.setRGB(0, 1, 0); v1.setST(0, 1);
        Vertex v2 = new Vertex(); 
        v2.setXYZ(1f, 0f, 1f); v2.setRGB(0, 0, 1); v2.setST(1, 1);
        Vertex v3 = new Vertex(); 
        v3.setXYZ(1f, 1f, 1f); v3.setRGB(1, 1, 1); v3.setST(1, 0);
        
        Vertex v4 = new Vertex(); 
        v4.setXYZ(0f, 1f, 0f); v4.setRGB(1, 0, 0);  v4.setST(1, 0);
        Vertex v5 = new Vertex(); 
        v5.setXYZ(0f, 0f, 0f); v5.setRGB(0, 1, 0); v5.setST(1, 1);
        Vertex v6 = new Vertex(); 
        v6.setXYZ(1f, 0f, 0f); v6.setRGB(0, 0, 1); v6.setST(0, 1);
        Vertex v7 = new Vertex(); 
        v7.setXYZ(1f, 1f, 0f); v7.setRGB(1, 1, 1); v7.setST(0, 0);
        
        vertices = new Vertex[] {v0, v1, v2, v3, v4, v5, v6, v7};
        
        indicesData = new byte[]{
            // front
            0,1,2,
            2,3,0,
            
            // right
            3,2,6,
            6,7,3,
            
            // back
            7,6,5,
            5,4,7,
            
            //Left
            4,5,1,
            1,0,4
        };
        
        createVBO();
        
        // Get texture from the HashMap and place it in the right texture unit
        textureDiff = Textures.getTexture("brickwall.png");   
        if(textureDiff == null){
            log.logError("Error loading texture!");
        }
        setTextureUnit(textureDiff, GL13.GL_TEXTURE0);
    }
}
