package com.hitesh.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

public class Level1 implements Screen {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private Texture BackgroundTexture;
    private Texture CatapultTexture;
    private Texture WinTexture;
    private Stage stage;
    private Game game;

    private List<Bird> birds;
    private List<Block> blocks;
    private List<Pig> pigs;

    private Bird activeBird;
    private Vector2 gravity = new Vector2(0, -9.8f);
    private boolean birdInFlight = false;
    private int remainingBirds;
    private int remainingPigs;
    private boolean isDragging = false;
    private Vector2 dragStart = new Vector2();
    private Vector2 dragEnd = new Vector2();
    private List<Vector2> trajectoryPoints = new ArrayList<>();
    private Texture trajectoryPointTexture;

    public Level1(Game game) {
        this.game = game;
        initialize();
    }

    private void initialize() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(10.3f, 5.2f);
        BackgroundTexture = new Texture("Background.jpeg");
        CatapultTexture = new Texture("Catapult.png");
        WinTexture = new Texture("Win.png");
        trajectoryPointTexture = new Texture("trajectoryPoints.png");

        stage = new Stage(viewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);

        birds = new ArrayList<>();
        blocks = new ArrayList<>();
        pigs = new ArrayList<>();

        // Create birds
        birds.add(new RedBird(0.8f, 0.9f));
        birds.add(new BlackBird(0, 0.48f));
        remainingBirds = birds.size();

        // Create blocks
        blocks.add(new WoodenBlock(6, 0.69f));
        blocks.add(new WoodenBlock(9, 0.69f));
        blocks.add(new WoodenBlock(9, 2));
        blocks.add(new WoodenBlock(6, 1.18f));
        blocks.add(new WoodenBlock(6, 1.67f));
        blocks.add(new WoodenBlock(6, 2.16f));
        blocks.add(new WoodenBlock(7, 0.69f));
        blocks.add(new GlassBlock(6.72f, 1));
        blocks.add(new GlassBlock(6.72f, 1));
        blocks.add(new SteelBlock(8.84f, 1.07f));
        blocks.add(new SteelBlock(8.84f, 1.07f));

        // Create pigs
        pigs.add(new Pig1(6, 2.6f));
        pigs.add(new Pig1(7, 1.68f));
        pigs.add(new Pig1(9, 1.6f));
        remainingPigs = pigs.size();

        // Pause Button
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

        // Back Button
        Image backButton = new Image(new Texture("BackButton.png"));
        backButton.setPosition(0, viewport.getWorldHeight() - 1.2f);
        backButton.setSize(0.5f, 0.5f);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HomeScreen(game)); // Navigate to Home screen
                return true;
            }
        });

        stage.addActor(backButton);

        activeBird = birds.get(0); // Set the first bird as active

        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!birdInFlight && activeBird != null) {
                    Rectangle birdBounds = activeBird.getBounds();
                    if (birdBounds.contains(x, y)) {
                        isDragging = true;
                        dragStart.set(x, y);
                    }
                }
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (isDragging) {
                    dragEnd.set(x, y);
                    updateTrajectory();
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isDragging) {
                    isDragging = false;
                    calculateLaunchParameters();
                    clearTrajectory();
                }
            }
        });
    }

    private void clearTrajectory() {
        trajectoryPoints.clear();
    }

    private void updateTrajectory() {
        trajectoryPoints.clear();

        float dragDistanceX = dragStart.x - dragEnd.x;
        float dragDistanceY = dragStart.y - dragEnd.y;

        float launchSpeed = dragDistanceX * 1.5f; // Adjust multiplier to tweak power
        float launchAngle = (float) Math.toDegrees(Math.atan2(dragDistanceY, dragDistanceX));

        Vector2 initialVelocity = new Vector2(
                launchSpeed * (float) Math.cos(Math.toRadians(launchAngle)),
                launchSpeed * (float) Math.sin(Math.toRadians(launchAngle))
        );

        Vector2 position = new Vector2(activeBird.getX(), activeBird.getY());
        Vector2 velocity = new Vector2(initialVelocity);

        float timeStep = 0.1f; // Adjust for smoother trajectory
        for (int i = 0; i < 50; i++) {
            trajectoryPoints.add(new Vector2(position));
            velocity.add(gravity.x * timeStep, gravity.y * timeStep);
            position.add(velocity.x * timeStep, velocity.y * timeStep);

            // Stop trajectory if it goes below ground level
            if (position.y < 0) break;
        }
    }

    // Calculate launch parameters from drag
    private void calculateLaunchParameters() {
//        float dragDistanceX = dragStart.x - dragEnd.x;
//        float dragDistanceY = dragStart.y - dragEnd.y;
        float dragDistance = dragStart.dst(dragEnd);
        float speed = dragDistance * 1.5f;
        float angle = (float) Math.toDegrees(Math.atan2(dragStart.y - dragEnd.y, dragStart.x - dragEnd.x));

//        float speed = dragDistanceX * 1.5f; // Adjust multiplier for speed scaling
//        float angle = (float) Math.toDegrees(Math.atan2(dragDistanceY, dragDistanceX));

        launchBird(angle, speed);
    }

    //    private void launchBird(float angle, float speed) {
//        activeBird.setVelocity(speed * (float) Math.cos(Math.toRadians(angle)),
//                speed * (float) Math.sin(Math.toRadians(angle)));
//        birdInFlight = true;
//    }
    private void launchBird(float angle, float speed) {
        if (activeBird != null) {
            activeBird.setVelocity(
                    speed * (float) Math.cos(Math.toRadians(angle)),
                    speed * (float) Math.sin(Math.toRadians(angle))
            );
            birdInFlight = true;
        }
    }


    private void updateBird(float delta) {
        if (birdInFlight && activeBird != null) {
            activeBird.updatePosition(delta, gravity);

            // Check for collisions
            checkCollisions();

            // Get the world width and height
            float worldWidth = Gdx.graphics.getWidth();
            float worldHeight = Gdx.graphics.getHeight();

            // Check if the bird is out of bounds
            if (activeBird.isOutOfBounds(worldWidth, worldHeight)) {
                birdInFlight = false;
                moveToNextBird();
            }
        }
    }

    private void moveToNextBird() {
        birds.remove(activeBird);
        remainingBirds--;
        if (!birds.isEmpty()) {
            activeBird = birds.get(0);
        } else {
            checkGameState();
        }
    }

    private void checkCollisions() {
        Rectangle birdBounds = activeBird.getBounds();

        // Check collision with blocks
        for (Block block : blocks) {
            if (birdBounds.overlaps(block.getBounds())) {
                block.takeHit();
                if (block.isDestroyed()) {
                    blocks.remove(block);
                }
                birdInFlight = false;
                break;
            }
        }

        // Check collision with pigs
        for (Pig pig : pigs) {
            if (birdBounds.overlaps(pig.getBounds())) {
                pig.takeHit();
                if (pig.isDead()) {
                    pigs.remove(pig);
                    remainingPigs--;
                }
                birdInFlight = false;
                break;
            }
        }

        checkGameState();
    }

    private void checkGameState() {
        if (remainingPigs == 0) {
            game.setScreen(new WinScreen(game));
        } else if (remainingBirds == 0) {
            game.setScreen(new LoseScreen(game));
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        stage.addListener(new InputListener() {
            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                if (!birdInFlight && activeBird != null) {
//                    float angle = 45f; // Example angle, replace with touch-based input if needed
//                    float speed = 5f;  // Example speed, adjust as per requirements
//                    launchBird(angle, speed);
//                }
//                return true;
//            }
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!birdInFlight && activeBird != null) {
                    Rectangle birdBounds = activeBird.getBounds();
                    if (birdBounds.contains(x, y)) {
                        isDragging = true;
                        dragStart.set(x, y);
                    }
                }
                return true;
            }

        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);

        spriteBatch.begin();
        spriteBatch.draw(BackgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        spriteBatch.draw(CatapultTexture, 1, 0.63f, 1, 1);

        // Draw birds, blocks, and pigs
        for (Bird bird : birds) bird.draw(spriteBatch);
        for (Block block : blocks) block.draw(spriteBatch);
        for (Pig pig : pigs) pig.draw(spriteBatch);

        // Draw trajectory points
        for (Vector2 point : trajectoryPoints) {
            spriteBatch.draw(trajectoryPointTexture, point.x, point.y, 0.1f, 0.1f);
        }

        spriteBatch.end();

        if (birdInFlight) {
            updateBird(delta);
        }

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        Gdx.app.log("Level1", "Game paused");
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
        BackgroundTexture.dispose();
        CatapultTexture.dispose();
        for (Bird bird : birds) bird.dispose();
        for (Block block : blocks) block.dispose();
        for (Pig pig : pigs) pig.dispose();
        stage.dispose();
//        super.dispose();
        trajectoryPointTexture.dispose();
    }
}
