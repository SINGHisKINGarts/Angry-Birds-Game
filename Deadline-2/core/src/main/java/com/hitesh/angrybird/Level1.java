package com.hitesh.angrybird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Level1 implements ApplicationListener{
     SpriteBatch spriteBatch;
     FitViewport viewport;
     Texture BackgroundTexture;
     Texture RedBirdTexture;
     Texture WoodenBlockTexture;
     Texture CatapultTexture;
     Texture PauseTexture;
     Image PauseTexture1; //Change made
     Stage stage; //change made

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport= new FitViewport(10.3f, 5.2f);
        BackgroundTexture = new Texture("Background.jpeg");
        RedBirdTexture = new Texture("Red Bird1.png");
        WoodenBlockTexture = new Texture("Wooden Block.jpg");
        CatapultTexture = new Texture("Catapult.png");
        PauseTexture = new Texture("Pause.png");
        stage = new Stage();
        PauseTexture1=new Image (PauseTexture);
        PauseTexture1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }
        });
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
        spriteBatch.draw(BackgroundTexture, 140, 210);
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

        spriteBatch.draw(BackgroundTexture,0,0,worldWidth,worldHeight); //draw the background
        spriteBatch.draw(RedBirdTexture,0.8f,0.9f,1,1);
        spriteBatch.draw(CatapultTexture,1,0.63f,1,1);
        spriteBatch.draw(WoodenBlockTexture,6,0.69f,0.5f,0.5f);
        spriteBatch.draw(WoodenBlockTexture,6,1.18f,0.5f,0.5f);
        spriteBatch.draw(WoodenBlockTexture,6,1.67f,0.5f,0.5f);
        spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);


        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        BackgroundTexture.dispose();
    }
}
