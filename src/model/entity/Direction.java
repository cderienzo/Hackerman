package model.entity;

import javafx.geometry.Pos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by franciscosanguineti on 30/5/17.
 */
public enum Direction implements Serializable {

    UP (0),
    UP_RIGHT (1),
    RIGHT (2),
    DOWN_RIGHT (3),
    DOWN (4),
    DOWN_LEFT (5),
    LEFT (6),
    UP_LEFT (7);

    private static final long serialVersionUID = 1L;

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private static final int[][] dir = {{0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}};

    private int code;

    Direction(int code) {
        this.code = code;
    }

    /**
     * Returns a random direction.
     *
     * @return A random direction.
     */
    public static Direction randomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public int getCode(){
        return code;
    }

    public int[] getDir() {
        return dir[code];
    }

    public boolean isDiagonal(){
        return (code % 2) == 1;
    }

    public static Direction directionBetween(Position p1, Position p2) {

    }

}
