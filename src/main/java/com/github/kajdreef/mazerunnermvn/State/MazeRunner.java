/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.State;

import com.github.kajdreef.mazerunnermvn.Input.*;
import com.github.kajdreef.mazerunnermvn.MazeRunner.Camera;
import com.github.kajdreef.mazerunnermvn.Object.Cube;
import com.github.kajdreef.mazerunnermvn.Object.GameObject;
import java.util.ArrayList;

/**
 *
 * @author kajdreef
 */
public class MazeRunner extends State {
    ArrayList<GameObject> obj = null;
    Camera camera = null;
    abstractInput input = null;
    
    public MazeRunner(){
        init();
    }
    
    @Override
    public void init() {
        camera = new Camera(0.0f, 0.0f, -8.0f, 0.0f, 0.0f);
        input = new KeyboardMouse();
        
        obj = new ArrayList<>();
        obj.add(new Cube(0,0,0));
        obj.add(new Cube(1,0,0));
        obj.add(new Cube(2,0,0));
        obj.add(new Cube(3,0,0));
    }
    
    @Override
    public void input() {
        input.look();
        input.move();
    }

    @Override
    public void logic(float delta) {
        camera.update(delta);
    }

    @Override
    public void render() {
        for(GameObject gameObj: obj){
            gameObj.render();
        }
    }
}
