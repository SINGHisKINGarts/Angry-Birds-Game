package com.hitesh.angrybird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.logging.Level;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SelectLevelScreen implements ApplicationListener {
    SpriteBatch spriteBatch;
    FitViewport viewport;
    Texture LevelScreenTexture;
    Texture PauseTexture;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport= new FitViewport(10.3f, 5.2f);
        LevelScreenTexture = new Texture("Level Background.jpg");
//        PauseTexture = new Texture("Pause.png");
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        // organize code into three methods
        input();
        logic();
        draw();
        spriteBatch.begin();
        spriteBatch.draw(LevelScreenTexture, 140, 210);
        spriteBatch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    private void input() {
    }

    private void logic() {
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        // store the worldWidth and worldHeight as local variables for brevity
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(LevelScreenTexture,0,0,worldWidth,worldHeight); //draw the background
//            spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
            spriteBatch.end();
        }

        @Override
        public void dispose() {
        spriteBatch.dispose();
        LevelScreenTexture.dispose();
        }
    }





