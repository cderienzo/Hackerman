package model.gameWorld;

import java.util.LinkedList;

/**
 * Created by cderienzo on 6/4/20WALL7.
 */
public class Level1 {

    private final static int WIDTH = 13;
    private final static int HEIGHT = 16;

    public Level1(PlayerCharacter player) {
        int[][] matrix = {
        public enum obstacleType {FLOOR,DESK, WALL, FAKECOMPUTER};
        //13X16
        {WALL, WALL, WALL, WALL, WALL, WALL, DOOR, WALL, WALL, WALL, WALL, WALL, WALL},
        {WALL,    0,    0,    0, 0, 0, 0, 0, 0, 0, 0, 0, WALL},
        {WALL, WALL, WALL, WALL, WALL, 0, GUARD, 0, WALL, WALL, WALL, WALL, WALL},
        {WALL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, DESK, WALL},
        {WALL, 0, 0, 0, WALL, 0, 0, 0, WALL, 0, 0, 0, WALL},
        {WALL, WALL, WALL, WALL, WALL, 0, 0, 0, WALL, WALL, WALL, WALL, WALL},
        {WALL, WALL, WALL, WALL, WALL, 0, 0, 0, WALL, WALL, WALL, WALL, WALL},
        {WALL, COMPUTER,DESK , 0, 0, WALL, 0, 0, 0, WALL, DESK, 0, 0, WALL},
        {WALL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, WALL},
        {WALL, WALL, WALL, WALL, WALL, 0, 0, 0, WALL, WALL, WALL, WALL, WALL},
        {WALL, 0, 0, 0, WALL, 0, 0, 0, WALL, 0, 0, 0, WALL},
        {WALL, DESK, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, WALL},
        {WALL, WALL, WALL, WALL, WALL, 0, 0, 0, WALL, WALL, WALL, WALL, WALL},
        {WALL, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, WALL},
        {WALL, 0, 0, 0, 0, 0, 0, 0, 0, PLAYER, 0, 0, WALL},
        {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL}
        };
        obstacleType state = obstacleType.FLOOR;
        LinkedList<Obstacle> desks = new LinkedList<Obstacle>();
        LinkedList<Obstacle> walls = new LinkedList<Obstacle>();
        LinkedList<Obstacle> fakecomputers = new LinkedList<Obstacle>();
        for(int i = 0; i<HEIGHT; i++) {
            for (int j = 0; j<WIDTH; j++) {
                switch(state){
                    case DESK: desks.add(new Obstacle());
                    case WALL: desks.add(new Obstacle());
                    case FAKECOMPUTER: desks.add(new Obstacle());
                }
            }

        }
        EntityManager = new EntityManager();
    }

}