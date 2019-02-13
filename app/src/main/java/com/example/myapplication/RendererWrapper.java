package com.example.myapplication;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import static android.opengl.GLES20.*;

import android.opengl.GLSurfaceView.Renderer;

import com.example.myapplication.JNIWrapper;


public class RendererWrapper implements Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        glClearColor(0.0f, 0.0f, 1.0f, 0.0f);
        JNIWrapper.on_surface_created();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        JNIWrapper.on_surface_changed(width, height);
        // No-op
    }

    @Override
    public void onDrawFrame(GL10 gl) {
//        glClear(GL_COLOR_BUFFER_BIT);
        JNIWrapper.on_draw_frame();
    }
}