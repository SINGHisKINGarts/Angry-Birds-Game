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



//import com.badlogic.gdx.ApplicationListener;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.Game;
//import org.w3c.dom.Text;
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//
//import java.awt.*;
//
//
///** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
//public class HomeScreen extends Game implements ApplicationListener, Screen {
//    SpriteBatch spriteBatch;
//    FitViewport viewport;
//    Texture HomeScreenTexture;
//    Texture PauseTexture;
//    Texture PlayButtonTexture;
//    Image PlayButtonTexture1;
//    float playButtonX;
//    float playButtonY;
//    float playButtonWidth;
//    float playButtonHeight;
//    Game game;
//    Stage stage;
//
//    public HomeScreen(Game game){
//        spriteBatch = new SpriteBatch();
//        viewport= new FitViewport(10.3f, 5.2f);
//        HomeScreenTexture = new Texture("Home Screen1.png");
//        PlayButtonTexture = new Texture("Play.png");
//        playButtonX = viewport.getWorldWidth() / 2 -1;
//        playButtonY = viewport.getWorldHeight() / 24;
//        playButtonWidth = 2;
//        playButtonHeight = 1;
//        this.game = game;
//        stage = new Stage(viewport, spriteBatch);
//        PlayButtonTexture1=new Image(PlayButtonTexture);
//        PlayButtonTexture1.setSize(PlayButtonTexture.getWidth(), PlayButtonTexture.getHeight());
//        PlayButtonTexture1.setPosition(playButtonX, playButtonY);
//        stage.addActor(PlayButtonTexture1);
//    }
//
//    @Override
//    public void create() {
////        spriteBatch = new SpriteBatch();
////        viewport= new FitViewport(10.3f, 5.2f);
////        HomeScreenTexture = new Texture("Home Screen1.png");
////        PlayButtonTexture = new Texture("Play.png");
////
////        playButtonX = viewport.getWorldWidth() / 2 -1;
////        playButtonY = viewport.getWorldHeight() / 24;
////        playButtonWidth = 2;
////        playButtonHeight = 1;
//
////        PauseTexture = new Texture("Pause.png");
//    }
//
//    @Override
//    public void show() {
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void render(float v) {
//        ScreenUtils.clear(0, 0, 0, 1);
//        spriteBatch.begin();
//        spriteBatch.draw(HomeScreenTexture, 0, 0, 10.3f, 5.2f);
////        batch.draw(background_image, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
//        spriteBatch.end();
//        stage.act(v);
//        stage.draw();
//    }
//
//    @Override
//        public void resize(int width, int height) {
//            viewport.update(width, height, true);
//        }
//
//        @Override
//        public void render() {
//            // organize code into three methods
//            input();
//            logic();
////            draw();
//            spriteBatch.begin();
//            spriteBatch.draw(HomeScreenTexture, 140, 210);
//            spriteBatch.end();
//        }
//
//        @Override
//        public void pause() {
//
//        }
//
//        @Override
//        public void resume() {
//
//        }
//
//    @Override
//    public void hide() {
//
//    }
//
//    private void input() {
//
//        }
//
//        private void logic() {
//
//        }
//
////        private void draw() {
////            ScreenUtils.clear(Color.BLACK);
////            viewport.apply();
////            spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
////            spriteBatch.begin();
////
////            // store the worldWidth and worldHeight as local variables for brevity
////            float worldWidth = viewport.getWorldWidth();
////            float worldHeight = viewport.getWorldHeight();
////
////            spriteBatch.draw(HomeScreenTexture,0,0,worldWidth,worldHeight); //draw the background
//////            spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
////            spriteBatch.draw(PlayButtonTexture, playButtonX, playButtonY, playButtonWidth, playButtonHeight);
////
////
////
////            spriteBatch.end();
////        }
//
//        @Override
//        public void dispose() {
//            spriteBatch.dispose();
//            HomeScreenTexture.dispose();
//            PlayButtonTexture.dispose();
//        }
//    }
//
//
//
