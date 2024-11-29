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

public class SettingScreen implements ApplicationListener, Screen {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture SettingScreenTexture;
    private Texture BackButtonTexture;
    private Image BackButtonImage;
    private Stage stage;
    private Game game;

    public SettingScreen(Game game) {
        this.game = game;
        Gdx.app.log("Settings", "Screen initialized");
        initialize();
    }

    private void initialize() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(10.3f, 5.2f);
        SettingScreenTexture = new Texture("SettingsBg.png");
        BackButtonTexture = new Texture("Back2.png");

        // Font setup
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("angrybirds.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.color = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BROWN;
        BitmapFont mediumFont = generator.generateFont(parameter);
        generator.dispose();

        // Skin for buttons
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Buttons
        TextButton BackButton = new TextButton("Back", skin);
        TextButton MusicButton = new TextButton("Music: ON", skin);
        TextButton SoundButton = new TextButton("Sound: ON", skin);
        TextButton CreditButton = new TextButton("Credits", skin);
        TextButton TutorialButton = new TextButton("Tutorial", skin);

        // New Text Back Button
        TextButton BackTextButton = new TextButton("Back", skin);
        BackTextButton.setPosition(0.5f, 0.5f);  // Position it at the bottom
        BackTextButton.setSize(2f, 1f);  // Set a reasonable size
        BackTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SettingScreen", "Back text button clicked");
                game.setScreen(new HomeScreen(game));
                dispose();
            }
        });
        stage.addActor(BackTextButton);

        // Back Button - Improved Positioning
        BackButtonImage = new Image(BackButtonTexture);
        BackButtonImage.setSize(2f, 2f);  // Slightly larger size
        BackButtonImage.setPosition(0.5f, 4.5f);  // Top left, adjusted
        BackButtonImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SettingScreen", "Back button clicked");
                game.setScreen(new HomeScreen(game));
                dispose();
            }
        });
        stage.addActor(BackButtonImage);

        // Music Button Listener
        MusicButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                // Add music toggle logic here
            }
        });

        // Back Button Listener
        BackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SettingScreen", "Back button clicked");
                game.setScreen(new HomeScreen(game));
                dispose();
            }
        });

        // Right Side Table
        Table table = new Table();
        table.setFillParent(true);
        table.right();
        table.padTop(100);
        table.add(MusicButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
        table.row();
        table.add(SoundButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
        stage.addActor(table);

        // Left Side Table
//        Table table2 = new Table();
//        table2.setFillParent(true);
//        table2.left();
//        table2.padTop(100);
//        table2.add(BackButton).padBottom(20).size(50,50);
//        table2.row();
//        table2.add(CreditButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
//        table2.row();
//        table2.add(TutorialButton).padBottom(20).padLeft(20).padRight(20).size(150,50);
//        stage.addActor(table2);
        Table table2 = new Table();
        table2.setFillParent(true);
        table2.left().top();
        table2.padTop(20);
        table2.padLeft(20);
        table2.add(BackButton).size(50,50).left().colspan(2);
        table2.row();
        table2.add(CreditButton).padTop(20).size(150,50);
        table2.row();
        table2.add(TutorialButton).padTop(20).size(150,50);
        stage.addActor(table2);



    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        spriteBatch.draw(SettingScreenTexture, 0, 0, worldWidth, worldHeight);

        spriteBatch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        SettingScreenTexture.dispose();
        BackButtonTexture.dispose();
        stage.dispose();
    }

    // Implement other required methods...
    @Override public void create() {}
    @Override public void show() {}
    @Override public void render() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}