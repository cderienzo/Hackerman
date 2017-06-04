package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SnakeGame;

/**
 * Created by Bianca on 27/05/2017.
 */
public class GameScreen implements Screen {

    private SpriteBatch batch;
    private Texture snakeHead;
    private SnakeGame game;

    private static final float MOVE_TIME = 0.3F;
    private float timer = MOVE_TIME;
    private static final int SNAKE_MOVEMENT = 45;

    private int snakeX, snakeY = 0;
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = RIGHT;

    private Texture apple;
    private boolean appleAvailable = false;
    private int appleX, appleY;

    private Texture snakeBody;
    private Array<BodyPart> bodyParts = new Array<BodyPart>();
    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;

    private boolean directionSet = false;
    private boolean hasHit = false;

    public enum STATE {PLAYING, PAUSED, EXIT, EXIT_YES, GAME_OVER}
    private STATE state = STATE.PLAYING;

    public GameScreen(SnakeGame game){

        this.game = game;
        batch = new SpriteBatch();
        snakeHead = new Texture("core/assets/snakehead.png");
        apple = new Texture("core/assets/apple.png");
        snakeBody = new Texture("core/assets/snakehead.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        switch(state) {
            case PLAYING: {
                queryInput();
                updateSnake(delta);
                checkAppleCollision();
                checkAndPlaceApple();
                clearScreen();
                draw();
            }
            break;
            case GAME_OVER: {
                game.setScreen(game.gameOverScreen);
            }
            break;
            case PAUSED: {
                game.setScreen(game.pausedScreen);
            }
            break;

            case EXIT: {
                game.setScreen(game.exitScreen);
            }
            break;

            case EXIT_YES: {
               Gdx.app.exit();
            }
            break;

        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        state = state.PAUSED;
    }

    @Override
    public void resume() {
        state = state.PLAYING;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void setState(STATE newState) {
        state = newState;
    }

    private void updateSnake (float delta) {
        if(!hasHit) {
            timer -= delta;
            if (timer <= 0) {
                timer = MOVE_TIME;
                moveSnake();
                checkForOutOfBounds();
                updateBodyPartsPosition();
                checkSnakeBodyCollision();
                directionSet = false;
            }
        }
    }

    private void checkForOutOfBounds() {
        if (snakeX >= Gdx.graphics.getWidth()) {
            snakeX = 0;
        }
        if (snakeX < 0) {
            snakeX = Gdx.graphics.getWidth() - SNAKE_MOVEMENT;
        }
        if (snakeY >= Gdx.graphics.getHeight()) {
            snakeY = 0;
        }
        if (snakeY < 0) {
            snakeY = Gdx.graphics.getHeight() - SNAKE_MOVEMENT;
        }
    }

    private void moveSnake() {
        snakeXBeforeUpdate = snakeX;
        snakeYBeforeUpdate = snakeY;

        switch (snakeDirection) {
            case RIGHT: {
                snakeX += SNAKE_MOVEMENT;
                return;
            }
            case LEFT: {
                snakeX -= SNAKE_MOVEMENT;
                return;
            }
            case UP: {
                snakeY += SNAKE_MOVEMENT;
                return;
            }
            case DOWN: {
                snakeY -= SNAKE_MOVEMENT;
                return;
            }
        }
    }

    private void queryInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean pausePressed = Gdx.input.isKeyPressed(Input.Keys.P);
        boolean exitGamePressed = Gdx.input.isKeyPressed(Input.Keys.E);

        if (lPressed) updateDirection(LEFT);
        if (rPressed) updateDirection(RIGHT);
        if (uPressed) updateDirection(UP);
        if (dPressed) updateDirection(DOWN);
        if (pausePressed) state = STATE.PAUSED;
        if (exitGamePressed) state = STATE.EXIT;
    }

    private void checkAndPlaceApple() {
        if (!appleAvailable) {
            do {
                appleX = MathUtils.random(Gdx.graphics.getWidth()
                        /SNAKE_MOVEMENT -1) *SNAKE_MOVEMENT;
                appleY = MathUtils.random(Gdx.graphics.getHeight()
                        /SNAKE_MOVEMENT -1) *SNAKE_MOVEMENT;
                appleAvailable = true;
            } while (appleX == snakeX && appleY == snakeY);
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void draw() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (state == STATE.PLAYING) {
            batch.draw(snakeHead, snakeX, snakeY);
            for (BodyPart bodyPart : bodyParts) {
                bodyPart.draw(batch);
            }
            if (appleAvailable) {
                batch.draw(apple, appleX, appleY);
            }
        }
        batch.end();
    }

    private void checkAppleCollision() {
        if (appleAvailable && appleX == snakeX && appleY == snakeY) {
            BodyPart bodyPart = new BodyPart(snakeBody);
            bodyPart.updateBodyPosition(snakeX, snakeY);
            bodyParts.insert(0, bodyPart);
            appleAvailable = false;
        }
    }

    private class BodyPart {
        private int x, y;
        private Texture texture;

        public BodyPart(Texture texture) {
            this.texture = texture;
        }
        public void updateBodyPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void draw(Batch batch) {
            if (!(x == snakeX && y == snakeY)) batch.draw(texture,
                    x, y);
        }
    }

    private void updateBodyPartsPosition() {
        if(bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.updateBodyPosition(snakeXBeforeUpdate,
                    snakeYBeforeUpdate);
            bodyParts.add(bodyPart);
        }
    }

    private void updateIfNotOppositeDirection(int newSnakeDirection, int
            oppositeDirection) {
        if (snakeDirection != oppositeDirection || bodyParts.size == 0)
            snakeDirection = newSnakeDirection;
    }

    private void updateDirection(int newSnakeDirection) {
        if (!directionSet && snakeDirection != newSnakeDirection) {
            directionSet = true;
            switch (newSnakeDirection) {
                case LEFT: {
                    updateIfNotOppositeDirection(newSnakeDirection, RIGHT);
                }
                break;
                case RIGHT: {
                    updateIfNotOppositeDirection(newSnakeDirection, LEFT);
                }
                break;
                case UP: {
                    updateIfNotOppositeDirection(newSnakeDirection, DOWN);
                }
                break;
                case DOWN: {
                    updateIfNotOppositeDirection(newSnakeDirection, UP);
                }
                break;
            }
        }
    }

    private void checkSnakeBodyCollision() {
        for (BodyPart bodyPart : bodyParts) {
            if (bodyPart.x == snakeX && bodyPart.y == snakeY) state = STATE.GAME_OVER;
        }
    }

}

