package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pig {
    protected Texture texture;
    protected float x, y, width, height;
    protected int health; // Represents the pig's health
    protected Rectangle bounds; // Bounding box for collision detection

    public Pig(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = 0.5f; // Default width
        this.height = 0.5f; // Default height
        this.health = 1; // Default health (can be adjusted)
        this.bounds = new Rectangle(x, y, width, height); // Initialize bounds
    }

    // Draw the pig
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x, y, width, height);
    }

    // Get the pig's bounding box for collision detection
    public Rectangle getBounds() {
        return bounds;
    }

    // Reduce the pig's health when it takes a hit
    public void takeHit() {
        health--;
    }

    // Check if the pig is dead (health <= 0)
    public boolean isDead() {
        return health <= 0;
    }

    // Dispose of the texture to free resources
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}








