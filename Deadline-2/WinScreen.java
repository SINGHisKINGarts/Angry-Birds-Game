package com.hitesh.angrybird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.logging.Level;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class WinScreen implements ApplicationListener {
    SpriteBatch spriteBatch;
    FitViewport viewport;
    Texture WinScreenTexture;
    Texture NextTexture;
    Texture BackTexture;
    Stage stage,stage2;


    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        Skin skin= new Skin(Gdx.files.internal("uiskin.json"));

        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        viewport= new FitViewport(10.3f, 5.2f);
        WinScreenTexture = new Texture("WinScreen.png");
        NextTexture=new Texture("NextLevel.png");
        BackTexture=new Texture("Back2.png");


        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("angrybirds.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=32;
        parameter.color=Color.BLACK;
        parameter.borderWidth=1;
        parameter.borderColor=Color.BROWN;
        BitmapFont mediumFont = generator.generateFont(parameter);

        generator.dispose();

        Label.LabelStyle mediumStyle=new Label.LabelStyle();
        mediumStyle.font=mediumFont;


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
        spriteBatch.draw(WinScreenTexture, 140, 210);


        spriteBatch.end();
        stage.act();
        stage.draw();

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

        float iconWidth = worldWidth * 1.2f; // 20% of world width
        float iconHeight = worldHeight * 1.2f; // 20% of world height
        float iconX = worldWidth / 2 ; // Center horizontally
        float iconY = worldHeight / 2 ; // Center vertically

        spriteBatch.draw(WinScreenTexture,0,0,worldWidth,worldHeight); //draw the background
        spriteBatch.draw(NextTexture,iconX+1,iconY-2f,3.5f,3.5f);
        spriteBatch.draw(BackTexture,iconX-3,iconY-1.75f,1.75f,1.45f);


//            spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        WinScreenTexture.dispose();
        NextTexture.dispose();
        stage.dispose();
    }
}
