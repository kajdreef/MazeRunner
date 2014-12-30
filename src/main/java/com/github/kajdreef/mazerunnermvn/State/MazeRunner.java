/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.State;

/**
 *
 * @author kajdreef
 */
public class MazeRunner extends State {


    @Override
    public void init() {

    }
    
    @Override
    public void input() {
        System.out.println("MazeRunner Input");

    }

    @Override
    public void logic(int delta) {
        System.out.println("MazeRunner Logic");
    }

    @Override
    public void render() {
        System.out.println("MazeRunner Render");
    }
}
