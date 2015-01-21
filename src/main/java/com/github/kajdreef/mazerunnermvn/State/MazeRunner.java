/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.State;

import com.github.kajdreef.mazerunnermvn.Input.*;
import com.github.kajdreef.mazerunnermvn.MazeRunner.Camera;
import com.github.kajdreef.mazerunnermvn.MazeRunner.Level;
import com.github.kajdreef.mazerunnermvn.MazeRunner.Renderer;
import com.github.kajdreef.mazerunnermvn.Util.Textures;

/**
 *
 * @author kajdreef
 */
public class MazeRunner extends State {
    
    private Camera player = null;
    private abstractInput input = null;
    private Level level = null;
    
    public MazeRunner(){
        init();
    }
    
    @Override
    public void init() {
        Textures textures = new Textures();
        Renderer render = new Renderer();
        
        player = new Camera(-9.0f, 0.0f, -9.0f, 0.0f, 90.0f);
        input = new KeyboardMouse();
        
        level = new Level();
    }
    
    @Override
    public void input() {
        input.look();
        input.move();
    }

    @Override
    public void logic(float delta) {
        if(!level.collision(player)){
            player.update(delta);
        }
        
    }

    @Override
    public void render() {
        level.render();
    }
}
