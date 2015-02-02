/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.State;

import com.github.kajdreef.mazerunnermvn.Input.iInput;

/**
 *
 * @author kajdreef
 */
public abstract class State{
    protected iInput input = null;
    
    /**
     * Initialize the State of the game.
     */
    public abstract void init();
    
    /**
     * Process the input of the player.
     */
    public abstract void input();
    
    /**
     * Process the logic of the game with the delta as time between
     * current and last frame.
     * 
     * @param delta 
     */
    public abstract void logic(float delta);
    
    /**
     * Render the graphics to the Display.
     */
    public abstract void render();
    
    public abstract void destroy();
}
