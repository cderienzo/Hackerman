package model.Managers;

import model.gameWorld.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by franciscosanguineti on 4/6/17.
 */
public class GameModel {

    private GameMap map;
    private boolean paused = false;
    private List<Level> levels;
    private int currentLevel;
    private int lives;
    private static final int MAX_LIVES = 3;


    public GameModel() {
        levels = new ArrayList<Level>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        lives = MAX_LIVES;
        currentLevel = 0;
    }

    public void nextLevel() {
        map = new GameMap(levels.get(currentLevel).createEntityManager());
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void tick() {
        if(!paused){
            map.getEntityManager().tick();
            if(playerCaught()) {
                lives--;
                if(gameOver()) {
                    paused = true;                                //terminar
                }
                else {
                    retryLevel();
                }
            }
        }
    }

    public boolean gameWon() {         //win
        return (currentLevel == levels.size()) && levelFinished();//
    }

    public boolean passedLevel() {

    }

    public boolean gameOver() {
        return lives == 0;
    }

    public boolean playerCaught() {
        return map.getEntityManager().playerCaught();
    }

    public void retryLevel() {

    }


}
