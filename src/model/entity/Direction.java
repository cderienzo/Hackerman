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
public class Direction implements Serializable {

    private static final int UP = 0;
    private static final int UP_RIGHT = 1;
    private static final int RIGHT = 2;
    private static final int DOWN_RIGHT = 3;
    private static final int DOWN = 4;                  //esto es nefasto
    private static final int DOWN_LEFT = 5;
    private static final int LEFT = 6;
    private static final int UP_LEFT = 7;

    private static final long serialVersionUID = 1L;

    private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    private static final int[][] dir = {{0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}};

    private int code;

    public Direction(int code) {
        this.code = code % 8;
    }

    /**
     * Returns a random direction.
     *
     * @return A random direction.
     */
    public static Direction randomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public Direction getLeft() {
        return new Direction(code-1);
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
