/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.MazeRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.opengl.GL20;

/**
 * Initialize shader programs to render with Shaders.
 * @author kajdreef
 */
public class Renderer {
    
    public Renderer(){
        int shaderProgram = GL20.glCreateProgram();
        int vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        int fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        
        StringBuilder vertexSS = new StringBuilder();
        StringBuilder fragmentSS = new StringBuilder();
        
        vertexSS = loadShader(vertexSS, "res/shaders/shader.vert" );
        fragmentSS = loadShader(fragmentSS, "res/shaders/shader.frag" );
        
        GL20.glShaderSource(vertexShader, vertexSS);
        GL20.glCompileShader(vertexShader);
                
        GL20.glShaderSource(fragmentShader, fragmentSS);
        GL20.glCompileShader(fragmentShader);
        
        GL20.glAttachShader(shaderProgram, vertexShader);
        GL20.glAttachShader(shaderProgram, fragmentShader);
        
        GL20.glLinkProgram(shaderProgram);
        
        GL20.glUseProgram(shaderProgram);
    }
    
    public StringBuilder loadShader(StringBuilder shaderSS, String fileLocation){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line;
            while((line = reader.readLine()) != null){
                shaderSS.append(line).append("/n");
            }
            reader.close();
        }
        catch(IOException ex){
            System.exit(1);
        }
        return shaderSS;
    }
}
