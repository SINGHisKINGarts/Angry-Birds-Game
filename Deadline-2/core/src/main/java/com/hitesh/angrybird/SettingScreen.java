package com.hitesh.angrybird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Set;

public class SettingScreen implements ApplicationListener{
    SpriteBatch spriteBatch;
    FitViewport viewport;
    Texture SettingScreenTexture;
    Texture BackButtonTexture;
    Stage stage;




    float playButtonX;
    float playButtonY;
    float playButtonWidth;
    float playButtonHeight;

    public void MusicOnOff(){

    }

    @Override
    public void create() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        spriteBatch = new SpriteBatch();
        viewport= new FitViewport(8, 5);
        SettingScreenTexture = new Texture("SettingsBg.png");
        BackButtonTexture = new Texture("Back.png");


        playButtonX = viewport.getWorldWidth() / 2;
        playButtonY = viewport.getWorldHeight() / 2;
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
//        Label Language=new Label("Language",mediumStyle);
//        Label info=new Label("Information",mediumStyle);
//        Label Music=new Label("Music: ON",mediumStyle);
//        Label Sound=new Label("Sound: ON",mediumStyle);


        Skin skin= new Skin(Gdx.files.internal("uiskin.json"));

        TextButton MusicButton=new TextButton("Music: ON",skin);
        TextButton SoundButton=new TextButton("Sound: ON",skin);

        TextButton CreditButton=new TextButton("Credits",skin);
        TextButton TutorialButton=new TextButton("Tutorial",skin);

        MusicButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                MusicOnOff();

                }
        });

//        Table table=new Table();
//        table.setFillParent(true);
//        table.left();
//        table.add(Language).padBottom(20).padLeft(40);
//        table.row();
//        table.add(info).padBottom(20).padLeft(40);
//
//        Table table2=new Table();
//        table2.setFillParent(true);
//        table2.right();
//        table2.add(Music).padBottom(20).padRight(40);
//        table2.row();
//        table2.add(Sound).padBottom(20).padRight(40);


//        stage.addActor(table);
//        stage.addActor(table2);
        Table table =new Table();
        table.setFillParent(true);
        table.right();
        table.padTop(100);

        table.add(MusicButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
        table.row();
        table.add(SoundButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
        table.row();

        stage.addActor(table);

        Table table2 =new Table();
        table2.setFillParent(true);
        table2.left();
        table2.padTop(100);
        table2.row();
        table2.add(CreditButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
        table2.row();
        table2.add(TutorialButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
        stage.addActor(table2);

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
        spriteBatch.draw(SettingScreenTexture, 140, 210);

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

        spriteBatch.draw(SettingScreenTexture,0,0,worldWidth,worldHeight); //draw the background
//            spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
        spriteBatch.draw(BackButtonTexture,0.5f,viewport.getWorldHeight()-1.25f,1,0.75f);




        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        SettingScreenTexture.dispose();

        stage.dispose();

    }
}
