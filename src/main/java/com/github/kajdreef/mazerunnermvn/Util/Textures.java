package com.github.kajdreef.mazerunnermvn.Util;

import java.io.IOException;
import java.util.HashMap;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author kajdreef
 */
public class Textures {
    
    private static HashMap<String, Texture> hashTexture;
    private static final String textureLocations = "res/texture/";
    
    public Textures(){
        hashTexture = new HashMap<>();
    }
    
    public static  Texture getTexture(String textureName){
        Texture texture = hashTexture.get(textureName);
        if(texture == null){
            hashTexture.put(textureName, loadTexture(textureLocations + textureName));
            return hashTexture.get(textureName);
        }
        return texture;
    }
    
    public static Texture loadTexture(String textureLocation){
        try {
            return TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/textures/brickwall.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
