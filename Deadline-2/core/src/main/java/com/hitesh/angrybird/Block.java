package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Block {
    protected Texture texture;
    protected float x, y, width, height;
    protected int health; // Represents the block's durability (number of hits it can take)
    protected Rectangle bounds; // Bounding box for collision detection

    public Block(Texture texture, float x, float y, float width, float height, int health) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health; // Set block's health/durability
        this.bounds = new Rectangle(x, y, width, height); // Initialize bounds
    }

    // Draw the block
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x, y, width, height);
    }

    // Get the block's bounding box for collision detection
    public Rectangle getBounds() {
        return bounds;
    }

    // Reduce the block's health when it takes a hit
    public void takeHit() {
        health--;
    }

    // Check if the block is destroyed (health <= 0)
    public boolean isDestroyed() {
        return health <= 0;
    }

    // Dispose of the texture to free resources
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }
}






//package com.hitesh.angrybird;
//
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//public class Block {
//    protected Texture texture;
//    protected float x, y, width, height;
//
//    public Block(Texture texture, float x, float y, float width, float height) {
//        this.texture = texture;
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
//    }
//
//    public void draw(SpriteBatch spriteBatch) {
//        spriteBatch.draw(texture, x, y, width, height);
//    }
//}
