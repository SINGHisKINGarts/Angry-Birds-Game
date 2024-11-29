package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    protected Texture texture;
    protected float x, y, width, height;
    protected Vector2 velocity; // Velocity of the bird
    protected Rectangle bounds; // Bounding box for collisions
    protected boolean launched = false; // Track if bird has been launched

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        bounds.setX(x); // Update bounds
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        bounds.setY(y); // Update bounds
    }

    // Constructor
    public Bird(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = 1f; // Default width
        this.height = 1f; // Default height
        this.velocity = new Vector2(0, 0); // Initial velocity
        this.bounds = new Rectangle(x, y, width, height); // Bounding box
    }

    // New setPosition method to set both x and y
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        bounds.setPosition(x, y); // Update bounds for collision detection
    }

    // Draw the bird
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x, y, width, height);
    }

    // Set the bird's velocity
    public void setVelocity(float vx, float vy) {
        this.velocity.set(vx, vy);
    }

    // Update the bird's position based on velocity and gravity
    public void updatePosition(float deltaTime, Vector2 gravity) {
        // Apply gravity to the velocity
        velocity.add(gravity.x * deltaTime, gravity.y * deltaTime);

        // Update the position using the velocity
        x += velocity.x * deltaTime;
        y += velocity.y * deltaTime;

        // Keep the bird's position above the ground
        if (y < 0) {
            y = 0;
            velocity.y = 0; // Stop downward movement upon ground collision
        }

        // Update the bounds to reflect the new position
        bounds.setPosition(x, y);
    }

    // Get the bird's current position
    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    // Get the bird's velocity
    public Vector2 getVelocity() {
        return velocity;
    }

    // Check if the bird is out of bounds (off the screen)
    public boolean isOutOfBounds(float worldWidth, float worldHeight) {
        return (x + width < 0 || x > worldWidth || y + height < 0 || y > worldHeight);
    }

    // Get the bird's bounding box for collision detection
    public Rectangle getBounds() {
        return bounds;
    }

    // Dispose of the texture to release resources
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

    // New methods to support YellowBird functionality

    // Update method for frame-by-frame logic
    public void update(float delta) {
        // Base implementation - can be overridden by subclasses
    }

    // Handle click events
    public void onClick() {
        // Base implementation - can be overridden by subclasses
    }

    // Reset the bird to initial state
    public void reset() {
        launched = false;
        velocity.set(0, 0);
    }

    // Set the bird's texture
    protected void setTexture(Texture newTexture) {
        if (this.texture != null) {
            this.texture.dispose(); // Dispose of old texture
        }
        this.texture = newTexture;
    }

    // Check if bird has been launched
    public boolean isLaunched() {
        return launched;
    }

    // Set launched state
    public void setLaunched(boolean launched) {
        this.launched = launched;
    }
}