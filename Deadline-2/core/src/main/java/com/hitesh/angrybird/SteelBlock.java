package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
public class SteelBlock extends Block {
    public SteelBlock(float x, float y) {
        super(new Texture("Steel Block.png"), x, y, 0.8f, 0.7f,3);
    }
}