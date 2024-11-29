package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
public class GlassBlock extends Block {
    public GlassBlock(float x, float y) {
        super(x, y);
        hitPoints = 2;  // Wooden blocks take 2 hits
        texture = new Texture("Glass Block.png");
        width = 1;   // Customize size if needed
        height = 1;
    }
}