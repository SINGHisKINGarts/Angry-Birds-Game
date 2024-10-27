package com.hitesh.angrybird;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    private final MyGame game;
    public GameScreen(MyGame game) {
        this.game = game;
    }
    @Override
    public void show() { }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Game rendering logic goes here
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {}


}
