package com.poo.hackerman.view;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.dynamicEntity.DynamicEntity;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;

/**
 * Created by cderienzo on 6/7/2017.
 */
public class Entity {

    private static final int TILE_WIDTH = 64;
    private static final int TILE_HEIGHT = 64;
    private static final int FRAME_ROWS = 4;
    private static final int FRAME_COLS = 9;
    private float x,y;

    private final Animation<TextureRegion> animation;
    private static final float FRAME_DURATION = 0.25F;
    private float animationTimer = 0;
    private DynamicEntity dynamicEntity;

    /**
     * Creates a player and assigns a texture to it.
     * Creates an animation by splitting the given sprite sheet into an array of TILE_WIDTHxTILE_HEIGHT dimension.
     * ERROR should be 9x4 but ends up being 4x2
     * @param playerTexture
     * @param dynamicEntity
     */
    public Entity(Texture playerTexture, DynamicEntity dynamicEntity) {
        int orientation = dynamicEntity.getDirection().getCode();
        TextureRegion[][] playerTextures = TextureRegion.split(playerTexture,TILE_WIDTH,TILE_HEIGHT);
        this.dynamicEntity = dynamicEntity;
        switch (orientation) {
            case Direction.UP:
                animation = new Animation<TextureRegion>(FRAME_DURATION,playerTextures[0][0], playerTextures[0][1], playerTextures[0][2],playerTextures[0][3],playerTextures[0][4]);//,playerTextures[0][5],playerTextures[0][6],playerTextures[0][7],playerTextures[0][8]);
                break;
            case Direction.DOWN:
                animation = new Animation<TextureRegion>(FRAME_DURATION,playerTextures[2][0], playerTextures[2][1], playerTextures[2][2],playerTextures[2][3],playerTextures[2][4]);//,playerTextures[0][5],playerTextures[0][6],playerTextures[0][7],playerTextures[0][8]);
                break;
            case Direction.LEFT:
                animation = new Animation<TextureRegion>(FRAME_DURATION,playerTextures[1][0], playerTextures[1][1], playerTextures[1][2],playerTextures[1][3],playerTextures[1][4]);//,playerTextures[0][5],playerTextures[0][6],playerTextures[0][7],playerTextures[0][8]);
                break;
            case Direction.RIGHT:
                animation = new Animation<TextureRegion>(FRAME_DURATION,playerTextures[3][0], playerTextures[3][1], playerTextures[3][2],playerTextures[3][3],playerTextures[3][4]);//,playerTextures[0][5],playerTextures[0][6],playerTextures[0][7],playerTextures[0][8]);
                break;
            default:
                animation = new Animation<TextureRegion>(FRAME_DURATION,playerTextures[0][0], playerTextures[0][1], playerTextures[0][2],playerTextures[0][3],playerTextures[0][4]);//,playerTextures[0][5],playerTextures[0][6],playerTextures[0][7],playerTextures[0][8]);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    /**
     * Only call if player moved, receives the character
     * @param delta
     * @param entity
     */
    public void update(float delta, DynamicEntity entity) {
        animationTimer+=delta;
        setPosition(entity.getPosition().getX(),entity.getPosition().getY());
    }

    /**
     * Sets player/guard position
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void update(float delta) {
        animationTimer += delta;
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