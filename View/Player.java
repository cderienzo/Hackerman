package com.poo.hackerman.view;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.poo.hackerman.model.entity.dynamicEntity.DynamicEntity;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;

/**
 * Created by cderienzo on 6/7/2017.
 */
public class Player {

    private static final int TILE_WIDTH = 64;
    private static final int TILE_HEIGHT = 64;
    private static final int FRAME_COLS = 9, FRAME_ROWS = 4;
    private float x,y;

    private final Animation<TextureRegion> animation;
    private static final float FRAME_DURATION = 0.25F;
    private float animationTimer = 0;
    private DynamicEntity dynamicEntity;

    public Player(Texture playerTexture, DynamicEntity dynamicEntity) {
        TextureRegion[][] playerTextures = new TextureRegion(playerTexture).split(TILE_WIDTH, TILE_HEIGHT);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_ROWS * FRAME_COLS];
        animation = new Animation(FRAME_DURATION,walkFrames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        this.dynamicEntity = dynamicEntity;

    }

    /**
     * Only call if player moved
     * @param delta
     * @param playerCharacter
     */
    public void update(float delta, PlayerCharacter playerCharacter) {
        animationTimer+=delta;
        setPosition(playerCharacter.getPosition().getX(),playerCharacter.getPosition().getY());
    }

    /**
     * Sets player/guard position
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        //updateCollisionCircle();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(SpriteBatch batch) {
        TextureRegion currentFrame = animation.getKeyFrame(animationTimer);
        batch.draw(currentFrame,dynamicEntity.getPosition().getX(),dynamicEntity.getPosition().getY() );
    }
}