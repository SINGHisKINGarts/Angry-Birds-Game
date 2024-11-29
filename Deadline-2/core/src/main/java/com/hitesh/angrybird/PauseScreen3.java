package com.hitesh.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hitesh.angrybird.Level1;
import com.hitesh.angrybird.SelectLevelScreen;

public class PauseScreen3 implements Screen {
    private Game game;
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;

    // Textures
    private Texture backgroundTexture;
    private Texture resumeButtonTexture;
    private Texture menuButtonTexture;

    public PauseScreen3(Game game) {
        this.game = game;

        // Initialize camera and viewport similar to Level1
        camera = new OrthographicCamera();
        viewport = new FitViewport(10.3f, 5.2f, camera);
        viewport.apply();
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        // Initialize batch and stage
        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
    }

    @Override
    public void show() {
        // Make sure the stage receives input events
        Gdx.input.setInputProcessor(stage);

        // Load textures
        try {
            backgroundTexture = new Texture("PauseScreen.png");
            resumeButtonTexture = new Texture("Retry.png");
            menuButtonTexture = new Texture("GotoMenu.png");

            // Create background
            Image background = new Image(backgroundTexture);
            background.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
            stage.addActor(background);

            // Create Resume Button
            ImageButton resumeButton = new ImageButton(
                    new TextureRegionDrawable(resumeButtonTexture)
            );
            resumeButton.setSize(1f, 1f); // Adjust size to match Level1's style
            resumeButton.setPosition(
                    viewport.getWorldHeight() / 2 + 5.3f,viewport.getWorldWidth() / 2 - 3.6f
            );
            resumeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new Level3(game));
                }
            });
            stage.addActor(resumeButton);

            // Create Menu Button
            ImageButton menuButton = new ImageButton(
                    new TextureRegionDrawable(menuButtonTexture)
            );
            menuButton.setSize(1f, 1f); // Adjust size to match Level1's style
            menuButton.setPosition(
                    viewport.getWorldHeight() / 2 - 1,viewport.getWorldWidth() / 2 - 3.5f

            );
            menuButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new SelectLevelScreen(game));
                }
            });
            stage.addActor(menuButton);

        } catch (Exception e) {
            Gdx.app.error("PauseScreen", "Error loading textures", e);
        }
    }

    @Override
    public void render(float delta) {
        // Clear the screen with Sky color to match Level1
        Gdx.gl.glClearColor(0.529f, 0.808f, 0.922f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set projection matrix
        batch.setProjectionMatrix(camera.combined);

        // Update and draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
        if (batch != null) batch.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (resumeButtonTexture != null) resumeButtonTexture.dispose();
        if (menuButtonTexture != null) menuButtonTexture.dispose();
    }
}