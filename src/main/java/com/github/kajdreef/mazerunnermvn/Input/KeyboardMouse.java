/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author kajdreef
 */
public class KeyboardMouse extends abstractInput {
    
    public KeyboardMouse(){
        Mouse.setGrabbed(true);
    }
    
    @Override
    public void move() {
        while(Keyboard.next()){
            if(Keyboard.getEventKey() ==  Button.BUTTON_UP.getKey()){
                    Button.BUTTON_UP.setPressed(Keyboard.getEventKeyState());
            }
            if(Keyboard.getEventKey() ==  Button.BUTTON_DOWN.getKey()){
                    Button.BUTTON_DOWN.setPressed(Keyboard.getEventKeyState());
            }
            if(Keyboard.getEventKey() ==  Button.BUTTON_LEFT.getKey()){
                    Button.BUTTON_LEFT.setPressed(Keyboard.getEventKeyState());
            }
            if(Keyboard.getEventKey() ==  Button.BUTTON_RIGHT.getKey()){
                    Button.BUTTON_RIGHT.setPressed(Keyboard.getEventKeyState());
            }
        }
    }

    @Override
    public void look() {
        dx = Mouse.getDX();
        dy = -Mouse.getDY();
    }
}
