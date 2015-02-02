/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn;

import com.github.kajdreef.mazerunnermvn.State.*;
import com.github.kajdreef.mazerunnermvn.Util.Timer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 * Launcher manages the states and game loop.
 * 
 * @author kajdreef
 */
public class Launcher {
    // Screen Height/Width
    public final static int HEIGHT = 720;
    public final static int WIDTH = 1280;
    
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
    
    /**
     * Initialize the display.
     */
    public void initDisplayLWJGL(){
        PixelFormat pixelFormat = new PixelFormat();
        ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
            .withForwardCompatible(true)
            .withProfileCore(true);
 
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("MazeRunner");
            Display.create(pixelFormat, contextAtrributes);
        } 
        catch(LWJGLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        
        // Enable depth test so the objects are rendered in the right way.
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDepthMask(true);
        
        GL11.glEnable(GL11.GL_CULL_FACE);
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
            
            // Clear the screen
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            
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
        state.destroy();
        Display.destroy();
    }
    
    public static void main(String[] args){        
        Launcher Mazerunner = new Launcher();
        Mazerunner.initDisplayLWJGL();
        Mazerunner.gameLoop();
        Mazerunner.destroy();
    }
}