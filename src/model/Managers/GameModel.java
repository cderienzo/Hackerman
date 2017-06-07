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
    private ComputerManager computerManager;

    private boolean paused = false;
    private List<Level> levels;
    private int currentLevel;

    private int lives;
    private static final int MAX_LIVES = 3;


    public GameModel() {
        levels = new ArrayList<Level>();
        levels.add(new Level1());           //path con el level que quiero levantar
        levels.add(new Level2());
        levels.add(new Level3());
        lives = MAX_LIVES;
        currentLevel = 0;

    }

    public void nextLevel() {
        map = new GameMap(levels.get(currentLevel).createEntityManager());
        computerManager = new ComputerManager(map.getEntityManager().getDoor(), map.getEntityManager().getComputers());
        paused = false;
        currentLevel++;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void tick() {
        if(!paused){
            map.getEntityManager().tick();
            computerManager.updateComputers();
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
        return (currentLevel == levels.size()) && passedLevel();//
    }

    public boolean passedLevel() {
        return map.getEntityManager().getDoor().hasBeenPassed();
    }

    public void retryLevel() {
        currentLevel--;
        nextLevel();            //no aumento el currentLevel
    }

    public int getLives() {
        return lives;
    }

    public boolean gameOver() {
        return lives == 0;
    }

    public boolean playerCaught() {
        return map.getEntityManager().playerCaught();
    }

}
