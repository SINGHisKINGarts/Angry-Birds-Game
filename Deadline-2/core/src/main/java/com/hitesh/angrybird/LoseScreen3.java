package com.hitesh.angrybird;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LoseScreen3 implements Screen {
    private final Game game;
    private SpriteBatch spriteBatch;
    private Stage stage;
    private FitViewport viewport;
    private Texture winScreenTexture;
    private Texture retryTexture, backTexture, menuTexture;


    public LoseScreen3(Game game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        // Initialize viewport and sprite batch
        viewport = new FitViewport(800, 600); // Adjust dimensions to match your assets
        spriteBatch = new SpriteBatch();

        // Load textures
        winScreenTexture = new Texture("LostScreen.png");
        retryTexture = new Texture("Retry.png");
        backTexture = new Texture("Back2.png");
        menuTexture = new Texture("GotoMenu.png");

        // Initialize stage and buttons
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        // Add "Next" button
        Image retrybuttoon = new Image(retryTexture);
        retrybuttoon.setSize(150, 150); // Adjust size based on texture
        retrybuttoon.setPosition(530, 90); // Center button
        retrybuttoon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("WinScreen", "Next button clicked");
                game.setScreen(new Level3(game)); // Ensure Level2 implements Screen
            }
        });

        // Add "Back" button
        Image backButton = new Image(backTexture);
        backButton.setSize(150, 150);
        backButton.setPosition(120, 100);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("WinScreen", "Back button clicked");
                game.setScreen(new SelectLevelScreen(game)); // Transition back to menu
            }
        });

        // Add "Menu" button
        Image menuButton = new Image(menuTexture);
        menuButton.setSize(100, 100);
        menuButton.setPosition(650, 400);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("WinScreen", "Menu button clicked");
                game.setScreen(new SelectLevelScreen(game));
            }
        });

        // Add buttons to stage
        stage.addActor(retrybuttoon);
        stage.addActor(backButton);
        stage.addActor(menuButton);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        ScreenUtils.clear(Color.BLACK);

        // Draw background and stage
        spriteBatch.begin();
        spriteBatch.draw(winScreenTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        winScreenTexture.dispose();
        retryTexture.dispose();
        backTexture.dispose();
        menuTexture.dispose();
        stage.dispose();
    }
}