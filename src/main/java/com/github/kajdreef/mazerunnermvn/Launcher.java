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


/**
 *
 * @author kajdreef
 */
public class Launcher {
    // Screen Height/Width
    private final int HEIGHT = 800;
    private final int WIDTH = 600;
    
    public States currentState = null;
    public static States nextState = States.MAINMENU;
    
    // false if the game needs to continue, true if the game needs to end.
    public static boolean quit = false;
    
    // State (menu/game/etc) where the game is in.
    private static State state = null;
    
    // Timer used to calculate the delta time between frames
    Timer timer0 = null;
    
    // Number of frames per second
    private final int fps = 60;
    
    /**
     * Initialize the display.
     */
    public void initDisplay(){
        try {
	    Display.setDisplayMode(new DisplayMode(HEIGHT,WIDTH));
	    Display.create();
	} catch (LWJGLException e) {
	    e.printStackTrace();
            System.exit(0);
	}
    }
    
    /**
     * Initialize LWJGL.
     */
    public void initLWJGL(){
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, HEIGHT, 0, WIDTH, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
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
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args){
        System.out.println("Start Game");
        
        Launcher Mazerunner = new Launcher();
        Mazerunner.initDisplay();
        Mazerunner.initLWJGL();
        Mazerunner.gameLoop();
        Mazerunner.destroy();
    }
}
