package controller;

import model.Managers.GameModel;
import model.entity.dynamicEntity.character.GameCharacter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by franciscosanguineti on 4/6/17.
 */
public class ModelManager implements Runnable{

    private GameModel gameModel;
    private Manager manager;
    private Thread thread;

    public ModelManager(Manager manager) {
        this.manager = manager;
        gameModel = new GameModel();
    }

    public void initialize(){
        thread = new Thread(this, "Model manager thread");
        thread.start();
    }

    public void run() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                gameModel.tick();
                if(gameModel.gameOver()) {
                    manager.gameOver();
                }
                if (gameModel.passedLevel() && gameModel.hasNextLevel()) {
                    gameModel.setPaused();
                    manager.passedLevel();                              //screen de next level
                }
                if (gameModel.passedLevel() && !gameModel.hasNextLevel()) {
                    gameModel.setPaused();
                    manager.gameWon();
                }
            }
        };
        timer.schedule(task, 0, 50);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

}
