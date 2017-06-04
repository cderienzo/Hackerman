package com.mygdx.game;

import com.badlogic.gdx.*;
import com.mygdx.game.view.View;

/**
 * Created by Bianca on 03/06/2017.
 */
public class Controller extends ApplicationAdapter {
    private ModelManager model;
    private View view;
    private ScreenController screenController;
    private InputController inputController;
    private PlayerController playerController;
    private LevelController levelController;

    public Controller (ModelManager model, View view) {
        this.model = model;
        this.view = view;

    }

    public boolean CheckGameOver () {
        return model.GameOver;
    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

