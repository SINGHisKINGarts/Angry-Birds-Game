package com.hitesh.angrybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
        private final MyGame game;
        private Stage stage;
        private Skin skin;

        public MainMenuScreen(MyGame game) {
            this.game = game;
            stage = new Stage(new ScreenViewport());
            skin = new Skin(Gdx.files.internal("uiskin.json"));

            // Create button
            TextButton startButton = new TextButton("Start Game", skin);
            startButton.setPosition(200, 200);
            startButton.setSize(200, 60);

            // Set button listener to switch screens on click
            startButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(game)); // Switch to GameScreen
                }
            });

            stage.addActor(startButton);
            Gdx.input.setInputProcessor(stage);
        }

        @Override
        public void show() { }

        @Override
        public void render(float delta) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(delta);
            stage.draw();
        }

        @Override
        public void resize(int width, int height) {
            stage.getViewport().update(width, height, true);
        }

        @Override
        public void pause() { }

        @Override
        public void resume() { }

        @Override
        public void hide() { }

        @Override
        public void dispose() {
            stage.dispose();
            skin.dispose();
        }
    }


