/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kajdreef.mazerunner.Util;
 
/**
 *
 * @author kajdreef
 */
public class Logger {
    
    private boolean console = true;
    
    private static Logger log = null;
    
    /**
     * Constructor of the Logger
     * TODO: Implement write to log file
     */
    private Logger(){
    
    }
    
    /**
     * Return the Logger instance log. 
     * @return log
     */
    public static Logger getInstance(){
        if(log == null){
            log = new Logger();
            return log;
        }
        else{
            return log;
        }
    }
    
    /**
     * Log an error message str;
     * @param str 
     */
    public void logError(String str){
        if(console)
            System.out.println("Error: " + str);
    }
    
    /**
     * Log an debug message str;
     * @param str 
     */
    public void logDebug(String str){
        if(console)
            System.out.println("Debug: " + str);
    }
    
    /**
     * Log an warning message str;
     * @param str 
     */
    public void logWarning(String str){
        if(console)
            System.out.println("Warning: " + str);
    }
    
    /**
     * Log an info message str;
     * @param str 
     */
    public void logInfo(String str){
        if(console)
            System.out.println("Info: " + str);
    }
    
}
