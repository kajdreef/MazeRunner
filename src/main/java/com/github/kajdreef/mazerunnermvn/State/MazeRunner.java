/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.State;

import com.github.kajdreef.mazerunnermvn.Object.Cube;
import com.github.kajdreef.mazerunnermvn.Object.GameObject;
import java.util.ArrayList;

/**
 *
 * @author kajdreef
 */
public class MazeRunner extends State {
    ArrayList<GameObject> obj = null;

    public MazeRunner(){
        init();
    }
    
    @Override
    public void init() {
        obj = new ArrayList<>();
        obj.add(new Cube(0,0,0));
    }
    
    @Override
    public void input() {

    }

    @Override
    public void logic(int delta) {

    }

    @Override
    public void render() {
        for(GameObject gameObj: obj){
            gameObj.render();
        }
    }
}
