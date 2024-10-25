package com.hitesh.angrybird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.w3c.dom.Text;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class HomeScreen implements ApplicationListener {
    SpriteBatch spriteBatch;
    FitViewport viewport;
    Texture HomeScreenTexture;
    Texture PauseTexture;
    Texture PlayButtonTexture;

    float playButtonX;
    float playButtonY;
    float playButtonWidth;
    float playButtonHeight;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport= new FitViewport(8, 5);
        HomeScreenTexture = new Texture("Home Screen1.png");
        PlayButtonTexture = new Texture("Play.png");

        playButtonX = viewport.getWorldWidth() / 2 -1;
        playButtonY = viewport.getWorldHeight() / 24;
        playButtonWidth = 2;
        playButtonHeight = 1;

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
            spriteBatch.draw(HomeScreenTexture, 140, 210);
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

            spriteBatch.draw(HomeScreenTexture,0,0,worldWidth,worldHeight); //draw the background
//            spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
            spriteBatch.draw(PlayButtonTexture, playButtonX, playButtonY, playButtonWidth, playButtonHeight);



            spriteBatch.end();
        }

        @Override
        public void dispose() {
            spriteBatch.dispose();
            HomeScreenTexture.dispose();
            PlayButtonTexture.dispose();
        }
    }



