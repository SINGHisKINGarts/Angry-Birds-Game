package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;

public class WoodenBlock extends Block {
    public WoodenBlock(float x, float y) {
        super(x, y);
        hitPoints = 2;  // Wooden blocks take 2 hits
        texture = new Texture("Wooden Block.jpg");
        width = 0.5f;   // Customize size if needed
        height = 0.5f;
    }
}

