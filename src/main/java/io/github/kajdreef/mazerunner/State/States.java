/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kajdreef.mazerunner.State;

/**
 * Used to switch between states and not having the need for a switch state.
 * 
 * @author kajdreef
 */
public enum States {
    MAINMENU{
        @Override
        public State construct(){
            return null;
        }
    },
    MAZERUNNER{
        @Override
        public State construct(){
            return new MazeRunner();
        }
    };
    
    /**
     * This methods returns the constructor of the associated enum state.
     * @return 
     */
    public abstract State construct();

}
