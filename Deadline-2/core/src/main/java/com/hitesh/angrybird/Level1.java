package com.hitesh.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;
import java.util.List;

public class Level1 implements ApplicationListener, Screen {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture BackgroundTexture;
    private Texture CatapultTexture;
    private Stage stage;
    private Game game;

    private List<Bird> birds;
    private List<Block> blocks;
    private List<Pig> pigs;

    public Level1(Game game) {
        this.game = game;
        Gdx.app.log("Level1", "Screen initialized");
        initialize();
    }

    private void initialize() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(10.3f, 5.2f);
        BackgroundTexture = new Texture("Background.jpeg");
        CatapultTexture = new Texture("Catapult.png");

        // Initialize stage and input processor
        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        // Initialize game objects
        birds = new ArrayList<>();
        blocks = new ArrayList<>();
        pigs = new ArrayList<>();

        // Create birds
        birds.add(new RedBird(0.8f, 0.9f));
        birds.add(new RedBird(0, 0.48f));

        // Create blocks
        blocks.add(new WoodenBlock(6, 0.69f));
        blocks.add(new WoodenBlock(9, 0.69f));
        blocks.add(new WoodenBlock(9, 2));
        blocks.add(new WoodenBlock(6, 1.18f));
        blocks.add(new WoodenBlock(6, 1.67f));
        blocks.add(new WoodenBlock(6, 2.16f));
        blocks.add(new WoodenBlock(7, 0.69f));
        blocks.add(new GlassBlock(6.72f, 1));
        blocks.add(new SteelBlock(8.84f, 1.07f));

        // Create pigs
        pigs.add(new Pig(6, 2.6f));
        pigs.add(new Pig(7, 1.68f));
        pigs.add(new Pig(9, 1.6f));

        // Create the pause button with listener
        Image pauseButton = new Image(new Texture("Pause.png"));
        pauseButton.setPosition(0, viewport.getWorldHeight() - 0.6f);
        pauseButton.setSize(0.5f, 0.5f);
        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Handle pause logic here
                return true;
            }
        });
        stage.addActor(pauseButton);
    }

    @Override
    public void show() {
        Gdx.app.log("Level1", "Level 1 screen shown");
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();
        spriteBatch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.draw(CatapultTexture, 1, 0.63f, 1, 1);

        // Draw birds
        for (Bird bird : birds) {
            bird.draw(spriteBatch);
        }

        // Draw blocks
        for (Block block : blocks) {
            block.draw(spriteBatch);
        }

        // Draw pigs
        for (Pig pig : pigs) {
            pig.draw(spriteBatch);
        }

        spriteBatch.end();

        stage.act();
        stage.draw();
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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        spriteBatch.dispose();
        BackgroundTexture.dispose();
        for (Bird bird : birds) {
            bird.texture.dispose();
        }
        for (Block block : blocks) {
            block.texture.dispose();
        }
        for (Pig pig : pigs) {
            pig.texture.dispose();
        }
        stage.dispose();
    }
}








//package com.hitesh.angrybird;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.ApplicationListener;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//
//public class Level1 implements ApplicationListener,Screen {
//    private SpriteBatch spriteBatch;
//    private FitViewport viewport;
//    private Texture BackgroundTexture, RedBirdTexture, WoodenBlockTexture, GlassBlockTexture,
//            SteelBlockTexture, CatapultTexture, PauseTexture, PigTexture;
//    private Stage stage;
//    private Game game;
//
//    public Level1(Game game) {
//        this.game = game;
//        Gdx.app.log("Level1", "Screen initialized");
//        initialize();
//    }
//
//    private void initialize() {
//        spriteBatch = new SpriteBatch();
//        viewport = new FitViewport(10.3f, 5.2f);
//        BackgroundTexture = new Texture("Background.jpeg");
//        RedBirdTexture = new Texture("Red Bird1.png");
//        WoodenBlockTexture = new Texture("Wooden Block.jpg");
//        GlassBlockTexture = new Texture("Glass Block.png");
//        SteelBlockTexture = new Texture("Steel Block.png");
//        CatapultTexture = new Texture("Catapult.png");
//        PauseTexture = new Texture("Pause.png");
//        PigTexture = new Texture("Pig2.png");
//
//        // Set up stage and pause button
//        stage = new Stage(viewport, spriteBatch);
//        Gdx.input.setInputProcessor(stage);
//
//        // Create the pause button with listener
//        Image pauseButton = new Image(PauseTexture);
//        pauseButton.setPosition(0, viewport.getWorldHeight() - 0.6f); // Adjust position
//        pauseButton.setSize(0.5f, 0.5f);
//        pauseButton.addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                // Handle pause logic here
//                return true;
//            }
//        });
//        stage.addActor(pauseButton);
//    }
//
//    @Override
//    public void show() {
//        Gdx.app.log("Level1", "Level 1 screen shown");
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(Color.BLACK);
//        viewport.apply();
//        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
//
//        spriteBatch.begin();
//        spriteBatch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight()); // Draw background
//        spriteBatch.draw(RedBirdTexture, 0.8f, 0.9f, 1, 1); // Draw birds, blocks, pigs, etc.
//        spriteBatch.draw(RedBirdTexture,0,0.48f,1,1);
//        spriteBatch.draw(CatapultTexture, 1, 0.63f, 1, 1);
//        spriteBatch.draw(WoodenBlockTexture, 6, 0.69f, 0.5f, 0.5f);
//        spriteBatch.draw(WoodenBlockTexture, 6,0.69f,0.5f,0.5f);
//        spriteBatch.draw(WoodenBlockTexture,9,0.69f,0.5f,0.5f);
//        spriteBatch.draw(WoodenBlockTexture,9,2,0.5f,0.5f);
//        spriteBatch.draw(WoodenBlockTexture,6,1.18f,0.5f,0.5f);
//        spriteBatch.draw(WoodenBlockTexture,6,1.67f,0.5f,0.5f);
//        spriteBatch.draw(WoodenBlockTexture,6,2.16f,0.5f,0.5f);
//        spriteBatch.draw(WoodenBlockTexture,7,0.69f,0.5f,0.5f);
//        spriteBatch.draw(GlassBlockTexture,6.72f,1,1,1);
//        spriteBatch.draw(SteelBlockTexture,8.84f,1.07f,0.8f,0.7f);
////        spriteBatch.draw(PauseTexture,0,4.5f,0.5f,0.5f);
//        spriteBatch.draw(PigTexture,6,2.6f,0.5f,0.5f);
//        spriteBatch.draw(PigTexture,7,1.68f,0.5f,0.5f);
//        spriteBatch.draw(PigTexture,9,1.6f,0.5f,0.5f);
//
//        spriteBatch.end();
//
//        stage.act();  // Update and render stage
//        stage.draw();
//    }
//
//    private void input() {
//        // Handle any specific input for Level 1
//    }
//
//    private void logic() {
//        // Implement any game logic for Level 1
//    }
//
////    private void draw() {
////        ScreenUtils.clear(Color.BLACK);
////        viewport.apply();
////        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
////
////        spriteBatch.begin();
////        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight()); // Draw background
////        spriteBatch.draw(redBirdTexture, 0.8f, 0.9f, 1, 1); // Draw birds, blocks, pigs, etc.
////        spriteBatch.draw(catapultTexture, 1, 0.63f, 1, 1);
////        spriteBatch.draw(woodenBlockTexture, 6, 0.69f, 0.5f, 0.5f);
////        // (Continue drawing other elements as needed...)
////        spriteBatch.end();
////
////        stage.act();  // Update and render stage
////        stage.draw();
////    }
//
//    @Override
//    public void create() {
//
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height, true);
//    }
//
//    @Override
//    public void render() {
//
//    }
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {
//        spriteBatch.dispose();
//        BackgroundTexture.dispose();
//        RedBirdTexture.dispose();
//        WoodenBlockTexture.dispose();
//        GlassBlockTexture.dispose();
//        SteelBlockTexture.dispose();
//        CatapultTexture.dispose();
//        PauseTexture.dispose();
//        PigTexture.dispose();
//        stage.dispose();
//    }
//}


