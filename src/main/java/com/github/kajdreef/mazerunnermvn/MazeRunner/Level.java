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
import com.github.kajdreef.mazerunnermvn.Util.Logger;
import com.github.kajdreef.mazerunnermvn.Util.Textures;
import java.util.ArrayList;

/**
 *
 * @author kajdreef
 */
public class Level {
    private Logger log = Logger.getInstance();
    private Textures textures = null;
    private Camera player = null;
    private boolean collision = false;
    
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
                    
    ArrayList<GameObject> noCollision = new ArrayList<GameObject>();
    public Level(){
        player = new Camera(-1.5f,-0.5f,-1.5f,0f,135f);
        
        textures = new Textures();
        for(int i = 0; i < WIDTH_LEVEL; i++){
            for(int j = 0; j < HEIGHT_LEVEL; j++){
                if(level[i][j]){
                    gameObjects.add(new Cube(i,0,j));
                }
            }
        }
        
        noCollision.add(new Floor(10f,10f));
        noCollision.add(new Roof(10f,10f));
    }
    
    
    public void update(float delta){
        player.newLocation();
        int i =0;
        for(GameObject temp: gameObjects){
            temp.update(delta);
            i++;
            collision = player.detectCollision(temp);
            if(collision){
                break;
            }
        }

        for(GameObject temp: noCollision){
            temp.update(delta);
        }
        
        if(!collision){
            player.update(delta);
        }
        else{
            log.logDebug("----------------> Collision <----------------");
        }
    }
    
    public void render(){
        for(GameObject temp: gameObjects){
            temp.render();
        }
        for(GameObject temp: noCollision){
            temp.render();
        }
    }
}
