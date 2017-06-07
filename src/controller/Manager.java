package controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.view.UIManager;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Manager {

    // Managers to control
    private ModelManager modelManager;
    private UIManager viewManager;

    public enum STATE {INITIALIZE, EXIT, PAUSE, GAME_OVER, WON}
    private STATE state = STATE.INITIALIZE;


    public Manager() {
        modelManager = new ModelManager();
        viewManager = new UIManager(modelManager);
    }

    public void stateManager (STATE state) {
        switch (state) {
            case INITIALIZE: {
                modelManager.initialize();
            } break;

            case EXIT: {
                viewManager.getGame().setScreen(viewManager.getGame().getExitScreen());
            } break;

            case PAUSE: {
                modelManager.getGameModel().setPause();
                viewManager.getGame().setScreen(viewManager.getGame().getPausedScreen());
            }break;

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
}
