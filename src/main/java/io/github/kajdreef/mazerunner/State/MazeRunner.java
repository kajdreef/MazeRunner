/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kajdreef.mazerunner.State;

import io.github.kajdreef.mazerunner.Input.KeyboardMouse;
import io.github.kajdreef.mazerunner.Input.abstractInput;
import io.github.kajdreef.mazerunner.Launcher;
import io.github.kajdreef.mazerunner.MazeRunner.Level;
import io.github.kajdreef.mazerunner.MazeRunner.ShaderProgram;
import io.github.kajdreef.mazerunner.Object.GameObject;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

/**
 *
 * @author kajdreef
 */
public class MazeRunner extends State {
    
    private ArrayList<GameObject> objList = null;
    private ShaderProgram shaderProgram = null;
    private Level level = null;
    private abstractInput input = null;
    
    // Matrices
    Matrix4f projectionMatrix = null;
    Matrix4f viewMatrix = null;
    Matrix4f modelMatrix = new Matrix4f();
    private FloatBuffer matrix44Buffer = null;
    
    public MazeRunner(){
        init();
    }
    
    @Override
    public void init() {
        input = new KeyboardMouse();
        level = new Level();
        
        // create the shaders
        shaderProgram = new ShaderProgram();
        
        // initialise projectionMatrix
        projectionMatrix = new Matrix4f();
        float fieldOfView = 60f;
        float aspectRatio = (float)Launcher.WIDTH / (float)Launcher.HEIGHT;
        float near_plane = 0.1f;
        float far_plane = 100f;
        float y_scale = 1/ (float)Math.tan((Math.toRadians(fieldOfView / 2f)));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = far_plane - near_plane;
        
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
        projectionMatrix.m33 = 0;
        
        // initialise modelMatrix;
        modelMatrix = new Matrix4f();
        modelMatrix.setIdentity();
        
        matrix44Buffer = BufferUtils.createFloatBuffer(16);
    }
    
    @Override
    public void input() {
        input.move();
        input.look();
    }

    @Override
    public void logic(float delta) {
        // Update the model positions
        level.update(delta);
        
        
        GL20.glUseProgram(shaderProgram.getProgram());

        projectionMatrix.store(matrix44Buffer); matrix44Buffer.flip();
        GL20.glUniformMatrix4(shaderProgram.getPML(), false, matrix44Buffer);

        GL20.glUseProgram(0);
    }

    @Override
    public void render() {
        shaderProgram.useProgram();
        
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        
        level.render();
        
        shaderProgram.stopProgram();
    }
    
    @Override
    public void destroy(){
        
    }
}
