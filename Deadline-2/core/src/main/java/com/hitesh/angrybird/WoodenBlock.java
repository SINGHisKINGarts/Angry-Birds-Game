package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
public class WoodenBlock extends Block {
    public WoodenBlock(float x, float y) {
        super(new Texture("Wooden Block.jpg"), x, y, 0.5f, 0.5f,1);
    }
}