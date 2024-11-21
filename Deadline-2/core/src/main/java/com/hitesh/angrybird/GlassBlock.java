package com.hitesh.angrybird;

import com.badlogic.gdx.graphics.Texture;
public class GlassBlock extends Block {
    public GlassBlock(float x, float y) {
        super(new Texture("Glass Block.png"), x, y, 1, 1,2);
    }
}
