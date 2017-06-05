package controller;

import model.Managers.GameModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by franciscosanguineti on 4/6/17.
 */
public class ModelManager implements Runnable{

    private GameModel gameModel;
    private Thread thread;

    public ModelManager(Manager manager) {
        gameModel = manager.getGameModel();
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
                if (gameModel.levelFinished())
                    gameModel.nextLevel();
            }
        };
        timer.schedule(task, 0, 5);
    }

}
