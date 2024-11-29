package com.hitesh.angrybird;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Level2 implements Screen {
    private Game game;
    private SpriteBatch spriteBatch;
    private Texture blackBirdTexture, redBirdTexture, backgroundTexture, pigTexture, slingTexture,pigTexture2;
    Vector2 birdPosition;
    Vector2 birdVelocity;
    Vector2 slingStart;
    private Vector2 slingEnd;
    boolean birdLaunched;
    private float gravity = -9.8f;
    private ShapeRenderer shapeRenderer;
    List<Block> blocks;
    List<Vector2> pigs;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Texture pauseTexture;
    Stage stage;
//    private List<Block> blocks;

    // Game state
    private ImageButton pauseButton;
    private boolean isDragging = false;
    private float dragStartTime = 0;
    private float maxDragDistance = 1.5f;
    private float launchPowerMultiplier = 7.5f;
    private float birdRadius = 0.2f;
    private float pigRadius = 0.25f;
    private boolean gameOver = false;
    int score = 0;

    // Bird management
    private Queue<Texture> birdQueue;
    private Texture currentBirdTexture;
    int remainingBirds;

    // Physics constants
    private float airResistance = 0.999f;
    private float groundFriction = 0.8f;
    private float restitution = 0.5f;
    private float minVelocity = 0.1f;

    // Ground level constants
    private final float GROUND_LEVEL = 0.8f;
    private final float SLINGSHOT_HEIGHT = 1.8f;
    private boolean LoseScreenShown;


    public Level2(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Initialize camera and viewport
        camera = new OrthographicCamera();
        viewport = new FitViewport(10.3f, 5.2f, camera);
        viewport.apply();
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        // Initialize graphics
        spriteBatch = new SpriteBatch();
        blackBirdTexture = new Texture("BLack Bird1.png");
        redBirdTexture = new Texture("Red Bird1.png");
        backgroundTexture = new Texture("Background.jpeg");
        pigTexture = new Texture("Pig5.png");
        pigTexture2 = new Texture("Pig5.png");
        slingTexture = new Texture("slingshot.png");
//        shapeRenderer = new ShapeRenderer();

        // Use the existing pauseTexture field instead of creating a new one
        pauseTexture = new Texture("Pause.png");


        stage=new Stage(viewport);
        shapeRenderer = new ShapeRenderer();

        // Set the projection matrix for shapeRenderer to match the camera
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Create the pause button
        TextureRegionDrawable pauseDrawable = new TextureRegionDrawable(new TextureRegion(pauseTexture));
        pauseButton = new ImageButton(pauseDrawable);
        pauseButton.setSize(0.5f, 0.5f);
        pauseButton.setPosition(
                0.1f,
                viewport.getWorldHeight() - 1f
        );
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Pause the game and transition to PauseScreen
                game.setScreen(new PauseScreen2(game));
                // Clean up resources of the current screen
            }
        });

        // Set input processor
        Gdx.input.setInputProcessor(stage);

        // Add pause button to stage
        stage.addActor(pauseButton);

        // Initialize game objects
        resetLevel();
    }

    private void resetLevel() {
        // Set initial positions higher off the ground
        slingStart = new Vector2(2.0f, SLINGSHOT_HEIGHT);
        birdPosition = new Vector2(slingStart.x, slingStart.y);
        birdVelocity = new Vector2(0, 0);
        slingEnd = new Vector2(slingStart);
        birdLaunched = false;
        isDragging = false;
        gameOver = false;
        score = 0;

        blocks = new ArrayList<>();
        blocks.add(new WoodenBlock(6, 0.69f));
        blocks.add(new WoodenBlock(8, 0.69f));

        blocks.add(new WoodenBlock(6.98f, 1.65f));

        blocks.add(new WoodenBlock(7, 0.69f));

        blocks.add(new GlassBlock(7.24f, 1));

        blocks.add(new SteelBlock(6.35f, 1.05f));


        // Initialize bird queue
        birdQueue = new LinkedList<>();
        birdQueue.add(blackBirdTexture);
        birdQueue.add(redBirdTexture);

        birdQueue.add(redBirdTexture);
        currentBirdTexture = birdQueue.poll();
        remainingBirds = birdQueue.size();

        // Initialize pigs with adjusted heights
        pigs = new ArrayList<>();
        pigs.add(new Vector2(6.75f, 0.95f));
        pigs.add(new Vector2(7.75f, 0.95f));
        pigs.add(new Vector2(7.25f, 1.4f));
        pigs.add(new Vector2(7.23f, 2.32f));    // Fourth pig with pigTexture2 (added at a different height)
    }

    private void resetBird() {
        // Prevent resetting if game is over
        if (gameOver) return;

        // If there are more birds, switch to the next one
        if (!birdQueue.isEmpty()) {
            currentBirdTexture = birdQueue.poll();
            remainingBirds--;
        }
        else{
            currentBirdTexture=null;
            gameOver = true;
        }

        birdPosition.set(slingStart);
        birdVelocity.setZero();
        birdLaunched = false;
    }

    @Override
    public void render(float delta) {
        // Check game over conditions more comprehensively
        if (!gameOver) {
            if ((remainingBirds == 0 && !birdLaunched) ||
                    (currentBirdTexture == null && pigs.size() > 0)) {
                gameOver = true;
            }
        }

        // Transition to lose screen only once
        if (gameOver && !LoseScreenShown) {
            if (pigs.size() > 0) {  // Only transition to lose screen if pigs remain
                game.setScreen(new LoseScreen2(game));
                LoseScreenShown = true;
                dispose();
                return;
            }
        }

        // Existing render logic remains the same
        ScreenUtils.clear(Color.SKY);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        // Set projection matrices
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Update physics if not game over
        if (!gameOver) {
            updatePhysics(delta);
        }

        // Draw background
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        spriteBatch.draw(pauseTexture,1,2);
        // Draw blocks
        for (Block block : blocks) {
            block.draw(spriteBatch);
        }

        // Draw slingshot
        float slingshotWidth = 0.6f;
        float slingshotHeight = 1.2f;
        spriteBatch.draw(slingTexture,
                slingStart.x - slingshotWidth/2,
                GROUND_LEVEL,
                slingshotWidth,
                slingshotHeight
        );

        // Draw bird
        if (!birdLaunched || birdPosition.y > -1) {
            spriteBatch.draw(currentBirdTexture,
                    birdPosition.x - birdRadius,
                    birdPosition.y - birdRadius,
                    birdRadius * 2,
                    birdRadius * 2
            );
        }

        // Draw pigs
        for (Vector2 pig : pigs) {
            spriteBatch.draw(pigTexture,
                    pig.x - pigRadius,
                    pig.y - pigRadius,
                    pigRadius * 2,
                    pigRadius * 2
            );
        }
        spriteBatch.end();

        // Draw trajectory when bird is being dragged and not yet launched
        if (!birdLaunched && isDragging) {
            drawTrajectory();
        }

        stage.act(delta);
        stage.draw();
        handleInput();

        // Check for level completion
        if (pigs.isEmpty()) {
            gameOver = true;
            game.setScreen(new WinScreen2(game));
            dispose();

        }
    }

    private boolean checkWinCondition() {
        return pigs.isEmpty() && blocks.stream().allMatch(Block::isDestroyed);
    }

    private void checkBlockCollisions() {
        List<Block> blocksToRemove = new ArrayList<>();

        for (Block block : blocks) {
            // Calculate distance between bird center and block center
            float blockCenterX = block.getX() + block.getWidth() / 2;
            float blockCenterY = block.getY() + block.getHeight() / 2;

            Vector2 blockCenter = new Vector2(blockCenterX, blockCenterY);
            float distance = birdPosition.dst(blockCenter);

            // Check for collision
            if (distance < birdRadius + Math.max(block.getWidth(), block.getHeight()) / 2) {
                block.takeHit();

                // Add impact effect to bird velocity
                Vector2 collisionNormal = birdPosition.cpy().sub(blockCenter).nor();
                birdVelocity.mulAdd(collisionNormal, birdVelocity.len() * 0.3f);

                // Check if block should be destroyed
                if (block.isDestroyed()) {
                    blocksToRemove.add(block);
                    score += 50;
                }

                // Check for chain reaction with nearby blocks
                checkBlocksAbove(block.getY() + block.getHeight());
            }
        }

        blocks.removeAll(blocksToRemove);
    }

    private void updatePhysics(float delta) {
        if (birdLaunched) {
            // Apply gravity


            birdVelocity.y += gravity * delta;

            // Apply air resistance
            birdVelocity.scl(airResistance);

            // Update position
            birdPosition.add(
                    birdVelocity.x * delta,
                    birdVelocity.y * delta
            );

            // Ground collision
            if (birdPosition.y < GROUND_LEVEL + birdRadius) {
                birdPosition.y = GROUND_LEVEL + birdRadius;
                birdVelocity.y *= -restitution;
                birdVelocity.x *= groundFriction;

                // Stop bird if moving too slow
                if (Math.abs(birdVelocity.x) < minVelocity &&
                        Math.abs(birdVelocity.y) < minVelocity) {
                    birdVelocity.setZero();
                    resetBird();
                }
            }

            // Wall collisions
            if (birdPosition.x < birdRadius) {
                birdPosition.x = birdRadius;
                birdVelocity.x *= -restitution;
            } else if (birdPosition.x > viewport.getWorldWidth() - birdRadius) {
                birdPosition.x = viewport.getWorldWidth() - birdRadius;
                birdVelocity.x *= -restitution;
            }

            // Check pig collisions
            checkBlockCollisions();
            checkPigCollisions();

            // Reset if bird goes too low
            if (birdPosition.y < -1) {
                resetBird();
            }
        }

    }
    private void saveGame() {
        GAmeSavelev2.saveGame(this);
    }

    private void loadGame() {
        GAmeSavelev2.loadGame(this);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            saveGame();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            loadGame();
        }
        if (!birdLaunched && Gdx.input.isTouched()) {
            Vector2 touch = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));

            if (!isDragging) {
                // Start dragging if touching near the bird
                if (touch.dst(birdPosition) < birdRadius * 2) {
                    isDragging = true;
                    dragStartTime = 0;
                }
            }

            if (isDragging) {
                dragStartTime += Gdx.graphics.getDeltaTime();

                // Update bird position while dragging
                Vector2 dragVector = touch.cpy().sub(slingStart);
                float dragDistance = dragVector.len();

                // Limit drag distance
                if (dragDistance > maxDragDistance) {
                    dragVector.nor().scl(maxDragDistance);
                }

                // Update bird position
                birdPosition.set(slingStart).add(dragVector);

                // Ensure bird stays above ground while dragging
                if (birdPosition.y < GROUND_LEVEL + birdRadius) {
                    birdPosition.y = GROUND_LEVEL + birdRadius;
                }
            }
        } else if (isDragging) {
            // Launch bird
            Vector2 launchVector = slingStart.cpy().sub(birdPosition);
            birdVelocity.set(launchVector.scl(launchPowerMultiplier));
            birdLaunched = true;
            isDragging = false;
        }

        // Reset level with R key
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.R)) {
            resetLevel();
        }
    }

    private void drawTrajectory() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.LIGHT_GRAY);

        Vector2 pos = birdPosition.cpy();
        Vector2 vel = slingStart.cpy().sub(birdPosition);

        // Apply the same upward boost as in launch
        vel.y *= 1.2f; // 30% boost
        vel.scl(launchPowerMultiplier);

        float timeStep = 0.1f;
        for (int i = 0; i < 30; i++) {
            Vector2 nextPos = pos.cpy();
            vel.y += gravity * timeStep;
            vel.scl(airResistance);
            nextPos.add(vel.x * timeStep, vel.y * timeStep);

            shapeRenderer.line(pos, nextPos);
            pos = nextPos;
        }

        shapeRenderer.end();
    }

    private void checkPigCollisions() {
        List<Vector2> pigsToRemove = new ArrayList<>();

        for (Vector2 pig : pigs) {
            float distance = birdPosition.dst(pig);
            if (distance < birdRadius + pigRadius) {
                pigsToRemove.add(pig);
                score += 100;

                // Add impact effect to bird velocity
                Vector2 collisionNormal = birdPosition.cpy().sub(pig).nor();
                birdVelocity.mulAdd(collisionNormal, birdVelocity.len() * 0.5f);
            }
        }

        pigs.removeAll(pigsToRemove);
    }

    private void checkBlocksAbove(float yPosition) {
        for (Block block : blocks) {
            if (block.getY() > yPosition) {
                block.takeHit();
            }
        }
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
        spriteBatch.dispose();
        blackBirdTexture.dispose();
        redBirdTexture.dispose();
        backgroundTexture.dispose();
        pigTexture.dispose();
        slingTexture.dispose();
        shapeRenderer.dispose();
    }
}