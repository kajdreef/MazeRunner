/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kajdreef.mazerunner.MazeRunner;

import io.github.kajdreef.mazerunner.Util.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 * Initialize shader programs to render with Shaders.
 * @author kajdreef
 */
public class ShaderProgram {
    Logger log = Logger.getInstance();
    private final String defaultShaderLocation = "res/shaders/";
    
    private int vertexId, fragmentId;
    private static int programId;
    private static int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation;
    
    public ShaderProgram(){        
        newShaderProgram("vertex.glsl","fragment.glsl");
    }
    
    /**
     * Initialize new shader program.
     * @param vertexShaderLocation
     * @param fragmentShaderLocation
     * @return false of failure of initializing shaderProgram, true on success
     */
    public boolean newShaderProgram(String vertexShaderLocation, String fragmentShaderLocation){
        // Initialize the vertex Shader
        vertexId = compileShader(GL20.GL_VERTEX_SHADER, vertexShaderLocation);
        
        // Initialize the fragment shader
        fragmentId = compileShader(GL20.GL_FRAGMENT_SHADER, fragmentShaderLocation);
        
        programId = GL20.glCreateProgram();
        GL20.glAttachShader(programId, vertexId);
        GL20.glAttachShader(programId, fragmentId);
        
        // Position information will be attribute 0
        GL20.glBindAttribLocation(programId, 0, "in_Position");
        // Color information will be attribute 1
        GL20.glBindAttribLocation(programId, 1, "in_Color");
        // Texture information will be attribute 2
        GL20.glBindAttribLocation(programId, 2, "in_TextureCoord");
        
        GL20.glLinkProgram(programId);
        GL20.glValidateProgram(programId);

        // Get matrices uniform locations
        projectionMatrixLocation = GL20.glGetUniformLocation(programId, "projectionMatrix");
        viewMatrixLocation = GL20.glGetUniformLocation(programId, "viewMatrix");
        modelMatrixLocation = GL20.glGetUniformLocation(programId, "modelMatrix");
        
        return true;
    }
    
    public void useProgram(){
        GL20.glUseProgram(programId);
    }
    
    public void stopProgram(){
        GL20.glUseProgram(0);
    }
    
    public static int getProgram(){
        return programId;
    }
    
    public static int getPML(){
        return projectionMatrixLocation;
    }
    
    public static int getVML(){
        return viewMatrixLocation;
    }
    
    public static int getMML(){
        return modelMatrixLocation;
    }
    
    public StringBuilder loadShader(String fileLocation) throws IOException{
        StringBuilder shaderSource = new StringBuilder();
        try{
            try (BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
                String line;
                while((line = reader.readLine()) != null){
                    shaderSource.append(line).append("\n");
                }
                reader.close();
            }
        }
        catch(IOException ex){
            log.logError("Failed reading file");
            throw ex;
        }
        return shaderSource;
    }
    
    public int compileShader(int shaderType, String shaderName){
        // Initialize the vertex Shader
        int shaderId = GL20.glCreateShader(shaderType);
        
        // Try to load/compile shader else return -1;
        try {
            GL20.glShaderSource(shaderId, loadShader(defaultShaderLocation + shaderName));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ShaderProgram.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
        GL20.glCompileShader(shaderId);
        
        // Check if the vertex shader compiled
        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            log.logError("Failed to compile " + shaderName+ " shader");
            return -1;
        }
        
        return shaderId;
    }
}
