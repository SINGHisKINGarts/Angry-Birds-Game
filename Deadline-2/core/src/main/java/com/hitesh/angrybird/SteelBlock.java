package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
public class SteelBlock extends Block {
    public SteelBlock(float x, float y) {
        super(x, y);
        hitPoints = 3;  // Wooden blocks take 2 hits
        texture = new Texture("Steel Block.png");
        width = 0.8f;   // Customize size if needed
        height = 0.7f;
    }
}




