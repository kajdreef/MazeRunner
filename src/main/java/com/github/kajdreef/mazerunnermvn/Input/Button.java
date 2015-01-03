/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Input;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author kajdreef
 */
public enum Button {
    BUTTON_UP(false, Keyboard.KEY_W), 
    BUTTON_DOWN(false, Keyboard.KEY_S), 
    BUTTON_LEFT(false, Keyboard.KEY_A), 
    BUTTON_RIGHT(false, Keyboard.KEY_D);
    
    private boolean isPressed;
    private int key;
    
    Button(boolean pressed, int key){
        this.isPressed = pressed;
        this.key = key;
    }
    
    public void setPressed(boolean pressed){
        this.isPressed = pressed;
    }
    
    public boolean isPressed(){
        return this.isPressed;
    }
    
    public void setKey(int newKey){
        this.key = newKey;
    }
    
    public int getKey(){
        return key;
    }
}
