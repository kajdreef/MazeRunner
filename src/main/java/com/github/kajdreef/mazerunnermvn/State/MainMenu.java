/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.State;

import com.github.kajdreef.mazerunnermvn.Launcher;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author kajdreef
 */
public class MainMenu extends State {
    private int i = 0;
    
    @Override
    public void init() {

    }
    
    @Override
    public void input() {

    }
    
    @Override
    public void logic(int delta) {

    }

    @Override
    public void render() {
	// Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	

        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f,0.5f,1.0f);

        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(100,100);
            GL11.glVertex2f(100+200,100);
            GL11.glVertex2f(100+200,100+200);
            GL11.glVertex2f(100,100+200);
        GL11.glEnd();
    }
    
}
