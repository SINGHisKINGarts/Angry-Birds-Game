package com.hitesh.angrybird;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.Set;


public class SettingScreen implements ApplicationListener, Screen {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture SettingScreenTexture;
    private Texture BackButtonTexture;
    private Image BackButtonImage;
    private Stage stage;
    private Game game;
    private float playButtonX;
    private float playButtonY;
    private float playButtonWidth;
    private float playButtonHeight;
    public SettingScreen(Game game) {
        this.game = game;
        Gdx.app.log("Settings", "Screen initialized");
        initialize();
    }

    private void initialize() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        spriteBatch = new SpriteBatch();
        viewport= new FitViewport(10.3f, 5.2f);
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

        BackButtonImage = new Image(BackButtonTexture);
        BackButtonImage.setSize(1, 0.75f);
        BackButtonImage.setPosition(0.5f, 2.5f);
        BackButtonImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SettingScreen", "Back button clicked");
                game.setScreen((Screen) new HomeScreen(game));
            }
        });
        stage.addActor(BackButtonImage);


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

    public void MusicOnOff(){

    }

    @Override
    public void create() {


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        // store the worldWidth and worldHeight as local variables for brevity
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        spriteBatch.draw(SettingScreenTexture,0,0,worldWidth,worldHeight); //draw the background
//            spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
//        spriteBatch.draw(BackButtonTexture,0.5f,viewport.getWorldHeight()-1.25f,1,0.75f);

//        spriteBatch.end();
//        spriteBatch.begin();
        spriteBatch.draw(SettingScreenTexture, 140, 210);

        spriteBatch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    private void input() {

    }

    private void logic() {

    }

    private void draw() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        SettingScreenTexture.dispose();

        stage.dispose();

    }
}
