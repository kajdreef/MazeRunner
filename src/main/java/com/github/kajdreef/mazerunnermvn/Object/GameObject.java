/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Object;

import com.github.kajdreef.mazerunnermvn.MazeRunner.ShaderProgram;
import com.github.kajdreef.mazerunnermvn.Object.Vertex.Vertex;
import com.github.kajdreef.mazerunnermvn.Util.Texture;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author kajdreef
 */
public abstract class GameObject implements aabb{
    
    protected Matrix4f modelMatrix = new Matrix4f();
    protected Vector3f scale = new Vector3f(1f,1f,1f);
    protected Vector3f position = new Vector3f(0f,0f,0f);
    protected Vector3f angle = new Vector3f(0f,0f,1f);
    
    protected Texture textureDiff;

    // VAO ID
    protected int vaoId;
    
    // VBO ID for the vertex, color texture and indices.
    protected int   texId,
                    vboIndicesId,
                    vboVerticesId;
    
    protected Vertex[] vertices;
    
    protected byte[]    indicesData;
    
    protected void createVBO(){        
        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.elementCount);
        for (int i = 0; i < vertices.length; i++) {
            verticesBuffer.put(vertices[i].getElements());
        }
        verticesBuffer.flip();
        
        // Create the indices buffer and place the data in it.
        ByteBuffer indicesBuffer = BufferUtils.createByteBuffer(indicesData.length);
        indicesBuffer.put(indicesData);
        indicesBuffer.flip();
        
        // Initialize the VAO 
        vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        
        // Initialize the VBO 
        vboVerticesId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVerticesId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
        
        // Put the positions in attribute list 0
        GL20.glVertexAttribPointer(0, Vertex.positionElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.positionByteOffset);
        // Put the color components in attribute list 1
        GL20.glVertexAttribPointer(1, Vertex.colorElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.colorByteOffset);
        // Put the texture coordinates in attribute list 2
        GL20.glVertexAttribPointer(2, Vertex.textureElementCount, GL11.GL_FLOAT, 
                false, Vertex.stride, Vertex.textureByteOffset);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        // Deselect the VAO
        GL30.glBindVertexArray(0);
        
        // Create a VBO for the indices.
        vboIndicesId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboIndicesId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    
    public void update(float delta){
        modelMatrix.setIdentity();
        
        // Scale, translate
        Matrix4f.scale(scale, modelMatrix, modelMatrix);
        Matrix4f.translate(position, modelMatrix, modelMatrix);
    }
    
    /**
     * Render the Object.
     */
    public void render() {
        FloatBuffer matrix44Buffer = BufferUtils.createFloatBuffer(16);
        modelMatrix.store(matrix44Buffer); 
        matrix44Buffer.flip();
        GL20.glUniformMatrix4(ShaderProgram.getMML(), false, matrix44Buffer);
        
        // Bind the texture
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);
        
        // Bind to the VAO that has all the information about the quad vertices
        GL30.glBindVertexArray(vaoId);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        
        // Bind to the index VBO that has all the information about the order of the vertices
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboIndicesId);
 
        // Draw the vertices
        GL11.glDrawElements(GL11.GL_TRIANGLES, indicesData.length, GL11.GL_UNSIGNED_BYTE, 0);

        // Put everything back to default (deselect)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL20.glDisableVertexAttribArray(2);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);  
    }
    
    public void destroy(){
        // Disable the VBO index from the VAO attributes list
        GL20.glDisableVertexAttribArray(0);

        // Delete the vertex VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(vboVerticesId);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,0);
        GL15.glDeleteBuffers(vboIndicesId);
        
        // Delete the VAO
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(vaoId);
    }
        
    protected void setTextureUnit(Texture texture, int textureUnit){
            // Create a new texture object in memory and bind it
        texId = GL11.glGenTextures();
        GL13.glActiveTexture(textureUnit);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);
         
        // All RGB bytes are aligned to each other and each component is 1 byte
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
         
        // Upload the texture data and generate mip maps (for scaling)
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, texture.getWidth(), texture.getHeight(), 0, 
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, texture.getTexBuffer());
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
         
        // Setup the ST coordinate system
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
         
        // Setup what to do when the texture has to be scaled
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, 
                GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, 
                GL11.GL_LINEAR_MIPMAP_LINEAR);
    }
    
    @Override
    public float getX(){
        return position.x;
    }
    
    @Override
    public float getZ(){
        return position.z;
    }
    
    @Override
    public float getWidth(){
        return scale.x;
    }
    
    @Override
    public float getHeight(){
        return scale.z;
    }
    
    @Override
    public boolean detectCollision(aabb obj){
        return obj.getWidth() > 0 && obj.getHeight() > 0 && scale.x > 0 && scale.z > 0 
            && obj.getX() < position.x + scale.x && -obj.getX() + obj.getWidth()/4 > position.x
            && obj.getZ() < position.z + scale.z && -obj.getZ() + obj.getWidth()/4 > position.z;
    }
}
