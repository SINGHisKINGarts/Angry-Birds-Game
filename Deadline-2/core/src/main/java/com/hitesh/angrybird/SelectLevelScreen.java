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

public class SelectLevelScreen implements ApplicationListener, Screen {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture levelScreenTexture;
    private Texture  Level1;
    private Stage stage;
    private Image Level_1;
    private Game game; // Reference to the Game instance

    public SelectLevelScreen(Game game) {
        this.game = game; // Initialize the Game reference
        Gdx.app.log("SelectLevelScreen", "Screen initialized");
        initialize(); // Call initialization method
    }

    // Initialize all the UI elements
    private void initialize() {
        spriteBatch = new SpriteBatch();
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();
        viewport = new FitViewport(10.3f, 5.2f);
        levelScreenTexture = new Texture("Level Background.jpg");
        Level1 = new Texture("Level1icon.png");


        // Create level buttons
        TextButton lvl1 = new TextButton("1", skin);
//        Image level1 = new Image(Level1);
        Level_1=new Image(Level1);
        TextButton lvl2 = new TextButton("2", skin);
        TextButton lvl3 = new TextButton("3", skin);

        // Set up the table for layout
        Table table = new Table();
        table.setFillParent(true);
        table.left();
        table.padBottom(120);
        table.add(lvl1).size(60, 60).padRight(30).padLeft(250);
        table.add(lvl2).size(60, 60).padRight(30).padLeft(30);
        table.add(lvl3).size(60, 60).padRight(30).padLeft(30);

        lvl1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SelectLevelScreen", "Level-1 clicked");
                game.setScreen((Screen) new Level1(game)); // Correctly transitioning to SelectLevelScreen
            }
        });
        stage.addActor(table);

        lvl2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SelectLevelScreen", "Level-2 clicked");
                game.setScreen((Screen) new Level2(game)); // Correctly transitioning to SelectLevelScreen
            }
        });
        stage.addActor(table);

        lvl3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("SelectLevelScreen", "Level-2 clicked");
                game.setScreen((Screen) new Level3(game)); // Correctly transitioning to SelectLevelScreen
            }
        });
        stage.addActor(table);

        // Create custom font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("angrybirds.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.color = Color.BLACK;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.BROWN;
        BitmapFont mediumFont = generator.generateFont(parameter);
        generator.dispose();

        // Create label for the title
        Label.LabelStyle mediumStyle = new Label.LabelStyle();
        mediumStyle.font = mediumFont;
        Label label = new Label("Levels", mediumStyle);
        Table head = new Table();
        head.top();
        head.center();
        head.add(label);
        stage.addActor(head);
    }

    @Override
    public void show() {
        Gdx.app.log("SelectLevelScreen", "Screen shown");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();
        spriteBatch.draw(levelScreenTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight()); // Draw the background
        spriteBatch.end();

        stage.act(delta); // Update the stage
        stage.draw(); // Draw the stage
    }

    @Override
    public void create() {

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

    @Override
    public void dispose() {
        spriteBatch.dispose();
        levelScreenTexture.dispose();
        stage.dispose();
    }
}