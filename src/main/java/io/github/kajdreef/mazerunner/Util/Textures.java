package io.github.kajdreef.mazerunner.Util;

import java.util.HashMap;


/**
 *
 * @author kajdreef
 */
public class Textures {
    
    private static HashMap<String, Texture> hashTexture;
    private static final String textureLocations = "res/textures/";
    
    public Textures(){
        hashTexture = new HashMap<>();
    }
    
    public static Texture getTexture(String textureName){
        Texture texture = hashTexture.get(textureName);
        if(texture == null){
            hashTexture.put(textureName, new Texture(textureLocations + textureName));
            return hashTexture.get(textureName);
        }
        return texture;
    }
    
    public static boolean removeTexture(String textureName){
        if(hashTexture.remove(textureName)==null)
            return false;
        else
            return true;
    }
}
