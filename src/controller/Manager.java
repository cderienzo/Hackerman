package controller;

import view.UIManager;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.view.UIManager;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Manager {

    // Managers to control
    private ModelManager modelManager;
    private UIManager uiManager;

    public enum STATE {INITIALIZE, EXIT, EXIT_YES, PAUSE, RESUME, GAME_OVER, WON}
    private STATE state = STATE.INITIALIZE;


    public Manager() {
        modelManager = new ModelManager(this);
        uiManager = new UIManager(modelManager);
    }

    public void stateManager (STATE state) {
        switch (state) {
            case INITIALIZE: {
                modelManager.initialize();
                uiManager.getGame().createGameScreen(uiManager.getGame());
                uiManager.getGame().setScreen((Screen)uiManager.getGame().getGameScreen());
            } break;

            case EXIT: {
                viewManager.getGame().setScreen(viewManager.getGame().getExitScreen());
            } break;

            case EXIT_YES: {
                Gdx.app.exit();
            }

            case PAUSE: {
                modelManager.getGameModel().setPause();
                viewManager.getGame().setScreen(viewManager.getGame().getPausedScreen());
            }break;

            case RESUME: {
                modelManager.getGameModel().resume();
                viewManager.getGame().setScreen(viewManager.getGame().getGameScreen());
            }

            case GAME_OVER: {
                modelManager.getGameModel().setPause();
                viewManager.getGame().setScreen(viewManager.getGame().getGameOverScreen());
            } break;

            case WON: {
                viewManager.getGame().setScreen(viewManager.getGame().getWonScreen());
                Gdx.app.exit();
            }
        }
    }

    public void checkGameOver() {
        if (modelManager.getGameModel().gameOver()) {
            stateManager(STATE.GAME_OVER);
        }
    }

        /**
         * Sets the current entityManager to the one specified. This method should be
         * called if and only if the game is to be loaded.
         *
         * @param entityManager The loaded entityManager.
         */

        /**
         * Start the game flow
         * @param args default params
         */
        public static void main(String[] args) {
            new Manager();
        }

}
