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


    public GameModel() {
        levels = new ArrayList<Level>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        currentLevel = 0;
        nextLevel();
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
            if(map.playerCaught()) {
                                        //terminar
            }
        }
    }

    public boolean levelFinished() {

    }


}
