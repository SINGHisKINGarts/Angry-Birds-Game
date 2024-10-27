package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pig {
    Texture texture;
    private float x, y, width, height;

    public Pig(float x, float y) {
        this.texture = new Texture("Pig2.png");
        this.x = x;
        this.y = y;
        this.width = 0.5f; // Set appropriate width
        this.height = 0.5f; // Set appropriate height
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, x, y, width, height);
    }
}
