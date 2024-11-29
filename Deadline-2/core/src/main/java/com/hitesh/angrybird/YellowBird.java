package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class YellowBird extends Bird {
    private boolean speedBoostActive = false;
    private final float SPEED_BOOST_MULTIPLIER = 1.5f;

    public YellowBird(float x, float y) {
        super(new Texture("Yellow Bird1.png"), x, y);
        // Specific yellow bird characteristics
        this.width = 0.3f;
        this.height = 0.3f;
    }

    @Override
    public void update(float delta) {
        if (speedBoostActive) {
            // Get current velocity and boost it
            Vector2 currentVelocity = getVelocity();
            float boostedVelocityX = currentVelocity.x * SPEED_BOOST_MULTIPLIER;
            float boostedVelocityY = currentVelocity.y * SPEED_BOOST_MULTIPLIER;
            setVelocity(boostedVelocityX, boostedVelocityY);
            speedBoostActive = false; // Only apply boost once
        }
    }

    @Override
    public void onClick() {
        // Only activate speed boost if bird is in flight
        if (isLaunched() && !speedBoostActive) {
            speedBoostActive = true;
            // Change texture to indicate speed boost
            setTexture(new Texture("Yellow Bird2.png")); // Assuming you have a speed boost texture
        }
    }

    @Override
    public void reset() {
        super.reset();
        speedBoostActive = false;
        setTexture(new Texture("Yellow Bird1.png"));
    }
}