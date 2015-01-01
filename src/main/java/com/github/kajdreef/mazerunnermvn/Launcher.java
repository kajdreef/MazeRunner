/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn;

import com.github.kajdreef.mazerunnermvn.State.*;
import com.github.kajdreef.mazerunnermvn.Util.Timer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 * Launcher manages the states and game loop.
 * 
 * @author kajdreef
 */
public class Launcher {
    // Display mode
    private DisplayMode dm;
    
    // Screen Height/Width
    private final int HEIGHT = 800;
    private final int WIDTH = 600;
    
    public States currentState = null;
    public static States nextState = States.MAZERUNNER;
    
    // false if the game needs to continue, true if the game needs to end.
    public static boolean quit = false;
    
    // State (menu/game/etc) where the game is in.
    private static State state = null;
    
    // Timer used to calculate the delta time between frames
    private Timer timer0 = null;
    
    // Number of frames per second
    private final int fps = 60;
    
    // Rotate the cube
    private int RotateYaw = 0;

    
    /**
     * Initialize the display.
     */
    public void initDisplay(){
        try {
            Display.setFullscreen(false);
            for (DisplayMode d : Display.getAvailableDisplayModes()) {
                if (d.getWidth() == HEIGHT && d.getHeight() == WIDTH && d.getBitsPerPixel() == 32) {
                    dm = d;
                    break;
                }
            }
            Display.setDisplayMode(dm);
	    Display.create();
	} catch (LWJGLException e) {
            System.exit(0);
	}
    }
    
    /**
     * Initialize LWJGL.
     */
    public void initLWJGL(){
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glClearDepth(1.0);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        
        GLU.gluPerspective( 
                45.0f,
                (float) dm.getWidth()/(float) dm.getHeight(),
                0.1f,
                300.0f
        );
        
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
    }
    
    /**
     * Request new State.
     * @param newState 
     */
    public static void requestNewState(States newState){
        nextState = newState;
    }
    
    /**
     * See if there is a new state ready.
     */
    public void checkState(){
        if(currentState != nextState){
            state = nextState.construct();
            currentState = nextState;
        }
    }
    
    /**
     * Here the game is processed.
     */
    public void gameLoop(){   
        timer0 = new Timer();
        timer0.start();
        while(!Display.isCloseRequested()){
            checkState();
            
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glLoadIdentity();

            GL11.glTranslatef((float)Math.sin(RotateYaw/180*Math.PI), 0f, -4f);
            GL11.glRotatef(45f, 0.4f, 1.0f, 0.1f);
            GL11.glRotatef(RotateYaw, 1f, 1.0f, 1f);
            RotateYaw+=2;
            
            state.input();
            state.logic(timer0.getDelta());
            state.render();
            
            Display.update();
            Display.sync(fps);
        }
    }
    
    /**
     * Destroy all remaining components.
     */
    public void destroy(){  
        Display.destroy();
    }
    
    public static void main(String[] args){
        System.out.println("Start Game");
        
        Launcher Mazerunner = new Launcher();
        Mazerunner.initDisplay();
        Mazerunner.initLWJGL();
        Mazerunner.gameLoop();
        Mazerunner.destroy();
    }
}
