/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.State;

/**
 * Used to switch between states and not having the need for a switch state.
 * 
 * @author kajdreef
 */
public enum States {
    MAINMENU{
        @Override
        public State construct(){
            return new MainMenu();
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
