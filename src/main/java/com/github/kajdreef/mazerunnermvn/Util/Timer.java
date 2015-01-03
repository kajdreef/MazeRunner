/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Util;

import org.lwjgl.Sys;

/**
 *
 * @author kajdreef
 */
public class Timer {
    public int fps = 0;
    private long lastFPS = 0;
    public long lastFrame = 0;

    /**
     * Record the starting time.
     */
    public void start() {
        lastFrame = getTime();
    }
    
    /**
    * Get the time in milliseconds
    * 
    * @return The system time in milliseconds
    */
   public long getTime() {
       return (Sys.getTime() * 1000) / Sys.getTimerResolution();
   }

   /**
    * Return the time between the last frame and new frame.
    * 
    * @return delta
    */
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
         
        return delta;
   }
}
