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
    private Thread thread;

    public ModelManager() {
        gameModel = new GameModel();
    }

    public void initialize(){
        thread = new Thread(this, "Model manager thread");
        thread.start();
        gameModel.nextLevel();
    }

    public void run() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                gameModel.tick();
                if (gameModel.levelFinished()) {
                    gameModel.nextLevel();          //screen de next kevel
                }
            }
        };
        timer.schedule(task, 0, 5);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

}
