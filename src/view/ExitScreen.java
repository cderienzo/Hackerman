package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import controller.Manager;

/**
 * Created by Bianca on 29/05/2017.
 */
public class ExitScreen implements Screen {

    private HackerGame game;
    //texture
    private Texture resumeButtonActive;
    private Texture resumeButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;
    private Texture exitGame;
    //text
    private static final int EXIT_TEXT_WIDTH = 392;
    private static final int EXIT_TEXT_HEIGHT = 43;
    //buttons
    private static final int BUTTON_WIDTH = 219;
    private static final int BUTTON_HEIGHT = 73;
    private static final int CENTER_X = 318;
    private static final int TOP_Y = 375;
    private static final int BOTTOM_Y = 475;
    private static final int buttonX = CENTER_X + BUTTON_WIDTH;
    private static final int topY = TOP_Y - BUTTON_HEIGHT;
    private static final int bottomY = BOTTOM_Y - BUTTON_HEIGHT;


    public ExitScreen(HackerGame game) {
        this.game = game;
        resumeButtonActive = new Texture(Gdx.files.internal("core/assets/resumeactive.png"));
        resumeButtonInactive = new Texture(Gdx.files.internal("core/assets/resumeinactive.png"));
        exitButtonActive = new Texture(Gdx.files.internal("core/assets/exitactive.png"));
        exitButtonInactive = new Texture(Gdx.files.internal("core/assets/exitinactive.png"));
        exitGame = new Texture(Gdx.files.internal("core/assets/exitgame.png"));
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
        game.batch.draw(exitGame, 231, 450, EXIT_TEXT_WIDTH, EXIT_TEXT_HEIGHT);
        //resume button
        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > topY && Gdx.input.getY() < TOP_Y) {
            game.batch.draw(resumeButtonActive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.getUIManager().setState(Manager.STATE.RESUME);
            }
        } else {
            game.batch.draw(resumeButtonInactive, CENTER_X, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
        }
        //exit button
        if (Gdx.input.getX() < buttonX && Gdx.input.getX() > CENTER_X
                && Gdx.input.getY() > bottomY && Gdx.input.getY() < BOTTOM_Y) {
            game.batch.draw(exitButtonActive, CENTER_X, 200, BUTTON_WIDTH, BUTTON_HEIGHT );
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.getUIManager().setState(Manager.STATE.EXIT_YES);
            }
        } else {
            game.batch.draw(exitButtonInactive, CENTER_X, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
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