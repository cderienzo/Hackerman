package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Bianca on 29/05/2017.
 */
public class GameOverScreen implements Screen {

    private HackerGame game;
    //texture
    private Texture gameOver;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    //text
    private static final int GAME_OVER_TEXT_WIDTH = 351;
    private static final int GAME_OVER_TEXT_HEIGHT = 43;
    //buttons
    private static final int BUTTON_WIDTH = 219;
    private static final int BUTTON_HEIGHT = 73;
    private static final int CENTER_X = 318;
    private static final int TOP_Y = 375;
    private static final int buttonX = CENTER_X + BUTTON_WIDTH;
    private static final int topY = TOP_Y - BUTTON_HEIGHT;

    public GameOverScreen(HackerGame game) {
        this.game = game;
        gameOver = new Texture(Gdx.files.internal("core/assets/gameover.png"));
        exitButtonActive = new Texture(Gdx.files.internal("core/assets/exitactive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("core/assets/exitinactive.png"));
    }

    @Override
    public void render(float delta) {
        this.pause();
        clearScreen();

        game.batch.begin();
        draw();
        game.batch.end();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void draw() {
        game.batch.draw(gameOver, 231, 450, GAME_OVER_TEXT_WIDTH, GAME_OVER_TEXT_HEIGHT );
        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > topY && Gdx.input.getY() < TOP_Y) {
            game.batch.draw(exitButtonActive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT );
            if (Gdx.input.isTouched()) {
                game.gameScreen.setState(GameScreen.STATE.EXIT_YES);
                game.setScreen((Screen)game.getGameScreen());
            }
        } else {
            game.batch.draw(exitButtonInactive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}