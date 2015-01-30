/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.MazeRunner;

import com.github.kajdreef.mazerunnermvn.Object.Cube;
import com.github.kajdreef.mazerunnermvn.Object.Floor;
import com.github.kajdreef.mazerunnermvn.Object.GameObject;
import com.github.kajdreef.mazerunnermvn.Object.Roof;
import com.github.kajdreef.mazerunnermvn.Util.Textures;
import java.util.ArrayList;

/**
 *
 * @author kajdreef
 */
public class Level {
    private Textures textures = null;
    final int WIDTH_LEVEL = 10; 
    final int HEIGHT_LEVEL = 10;
    boolean[][] level = {{true, true, true, true, true, true, true, true, true, true},
                         {true, false, false, false, false, false, false, false, false, true},
                         {true, false, true, true, true, true, true, true, false, true},
                         {true, false, true, true, false, false, true, true, false, true},
                         {true, false, false, false, false, false, false, false, false, true},
                         {true, false, true, true, false, false, true, true, false, true},
                         {true, false, true, true, true, true, true, true, false, true},
                         {true, false, true, true, true, true, true, true, false, true},
                         {true, false, false, false, false, false, false, false, false, true},
                         {true, true, true, true, true, true, true, true, true, true}
                        };
    
    ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
                    
    public Level(){
        textures = new Textures();
        for(int i = 0; i < WIDTH_LEVEL; i++){
            for(int j = 0; j < HEIGHT_LEVEL; j++){
                if(level[i][j]){
                    gameObjects.add(new Cube(i,0,j));
                }
            }
        }
        gameObjects.add(new Floor(10f,10f));
        gameObjects.add(new Roof(10f,10f));
    }
    
    public void update(){
        for(GameObject temp: gameObjects){
            temp.update();
        }
    }
    
    public void render(){
        for(GameObject temp: gameObjects){
            temp.render();
        }
    }
    
    public boolean collision(Camera player){
        for(GameObject obj: gameObjects){
            if(obj.collision(player))
                return true;
        }
        return false;
    }
}
