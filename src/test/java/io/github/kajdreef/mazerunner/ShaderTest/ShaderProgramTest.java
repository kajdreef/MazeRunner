///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package io.github.kajdreef.mazerunner.ShaderTest;
//
//import static io.github.kajdreef.mazerunner.Launcher.HEIGHT;
//import static io.github.kajdreef.mazerunner.Launcher.WIDTH;
//import io.github.kajdreef.mazerunner.MazeRunner.ShaderProgram;
//import org.junit.After;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.lwjgl.LWJGLException;
//import org.lwjgl.opengl.ContextAttribs;
//import org.lwjgl.opengl.Display;
//import org.lwjgl.opengl.DisplayMode;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL20;
//
//import org.lwjgl.opengl.PixelFormat;
//
///**
// *
// * @author kajdreef
// */
//public class ShaderProgramTest {
//
//    static ShaderProgram sp;
//
//    @Before
//    public void init(){
//        PixelFormat pixelFormat = new PixelFormat();
//        ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
//            .withForwardCompatible(true)
//            .withProfileCore(true);
//
//        try {
//            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
//            Display.setTitle("MazeRunner");
//            Display.create(pixelFormat, contextAtrributes);
//        }
//        catch(LWJGLException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
//
//        GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);
//        GL11.glViewport(0, 0, WIDTH, HEIGHT);
//
//        // Enable depth test so the objects are rendered in the right way.
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
//        GL11.glDepthFunc(GL11.GL_LEQUAL);
//        GL11.glDepthMask(true);
//
//        GL11.glEnable(GL11.GL_CULL_FACE);
//
//        sp = new ShaderProgram();
//    }
//
//    @Test
//    public void testTest(){
//        assertTrue(true);
//    }
//
//    @Test
//    public void compileShaderTestSuccess(){
//        int vertexId = sp.compileShader(GL20.GL_VERTEX_SHADER, "vertex.glsl");
//        assertTrue(vertexId != -1);
//    }
//
//    @Test
//    public void compileShaderTestFail(){
//        int fragmentId = sp.compileShader(GL20.GL_FRAGMENT_SHADER, "Test");
//        assertTrue(fragmentId == -1);
//    }
//
//    @After
//    public void destroy(){
//        Display.destroy();
//    }
//}
