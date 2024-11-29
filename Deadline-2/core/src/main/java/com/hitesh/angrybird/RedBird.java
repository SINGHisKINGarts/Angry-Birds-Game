package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
public class RedBird extends Bird {
    public RedBird(float x, float y) {
        super(new Texture("Red Bird1.png"), x, y);
        // Specific red bird characteristics
        this.width = 0.4f;
        this.height = 0.4f;
    }
}