package com.hitesh.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HomeScreen implements Screen {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture homeScreenTexture;
    private Texture playButtonTexture;
    private Image playButton;
    private Game game;
    private Stage stage;

    public HomeScreen(Game game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(10.3f, 5.2f);
        homeScreenTexture = new Texture("Home Screen1.png");
        playButtonTexture = new Texture("Play.png");

        stage = new Stage(viewport, spriteBatch);

        // Initialize Play button
        playButton = new Image(playButtonTexture);
        playButton.setSize(2, 1); // Set size for visibility
        playButton.setPosition(viewport.getWorldWidth() / 2 - 1, viewport.getWorldHeight() / 24); // Center it in the viewport

        // Add Click Listener to the Play button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("HomeScreen", "Play button clicked");
                game.setScreen((Screen) new SelectLevelScreen(game)); // Correctly transitioning to SelectLevelScreen
            }
        });

        stage.addActor(playButton);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen with a black color
        spriteBatch.begin();
        spriteBatch.draw(homeScreenTexture, 0, 0, 10.3f, 5.2f); // Draw the home screen background
        spriteBatch.end();

        stage.act(delta); // Update the stage
        stage.draw(); // Draw the stage
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        homeScreenTexture.dispose();
        playButtonTexture.dispose();
        stage.dispose(); // Dispose the stage
    }
}



