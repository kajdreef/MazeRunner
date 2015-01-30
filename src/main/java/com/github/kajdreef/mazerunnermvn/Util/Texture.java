/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kajdreef.mazerunnermvn.Util;

import de.matthiasmann.twl.utils.PNGDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 *
 * @author kajdreef
 */
public class Texture {

    private Logger log = Logger.getInstance();
    private int tWidth = 0;
    private int tHeight = 0;
    private ByteBuffer texBuffer = null;

    public Texture(String textureLocation) {
        try {
            // Open the PNG file as an InputStream
            InputStream in = new FileInputStream(textureLocation);
            // Link the PNG decoder to this stream
            PNGDecoder decoder = new PNGDecoder(in);

            // Get the width and height of the texture
            tWidth = decoder.getWidth();
            tHeight = decoder.getHeight();

            // Decode the PNG file in a ByteBuffer
            texBuffer = ByteBuffer.allocateDirect(
                    4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(texBuffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            texBuffer.flip();

            in.close();
        } catch (IOException e) {
            log.logError("Error loading: " + textureLocation);
        }
    }

    public ByteBuffer getTexBuffer() {
        return texBuffer;
    }

    public int getWidth() {
        return this.tWidth;
    }

    public int getHeight() {
        return this.tHeight;
    }
}
