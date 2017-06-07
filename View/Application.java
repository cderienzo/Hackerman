package com.poo.hackerman.view;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class Application extends Game {

    private final AssetManager assetManager = new AssetManager();

    @Override
    public void create() {
       // setScreen(new GameScreen2());
        setScreen(new GameScreen(this));
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
