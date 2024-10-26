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
public class SelectLevelScreen implements ApplicationListener {
    SpriteBatch spriteBatch;
    FitViewport viewport;
    Texture LevelScreenTexture;
    Texture PauseTexture;
    Texture Level1icon;
    Stage stage;


    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        Skin skin= new Skin(Gdx.files.internal("uiskin.json"));
        stage=new Stage();
        viewport= new FitViewport(10.3f, 5.2f);
        LevelScreenTexture = new Texture("Level Background.jpg");
        TextButton lvl1=new TextButton("1",skin);
        TextButton lvl2=new TextButton("2",skin);
        TextButton lvl3=new TextButton("3",skin);



        Table table=new Table();
        table.setFillParent(true);
        table.left();
        table.padBottom(100);
        table.add(lvl1).size(60,60).padRight(20).padLeft(20);
        table.add(lvl2).size(60,60).padRight(20).padLeft(20);
        table.add(lvl3).size(60,60).padRight(20).padLeft(20);
        stage.addActor(table);

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

        Label label=new Label("Levels",mediumStyle);
        Table head=new Table();
        head.top();
        head.center();
        head.add(label);
        stage.addActor(head);

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

        spriteBatch.draw(LevelScreenTexture,0,0,worldWidth,worldHeight); //draw the background

//            spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        LevelScreenTexture.dispose();
        stage.dispose();
    }
}
