package model.entity;

import model.gameWorld.GameMap;

import java.io.Serializable;

import static model.entity.Direction.directionBetween;


/**
 * Created by franciscosanguineti on 29/5/17.
 */
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;
    //capaz en int
    private int x;
    private int y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementPosition(int x , int y) {
        this.x += x;
        this.y += y;
    }

    public Direction directionTo(Position position) {
        return directionBetween(this,position);
    }

    public int distanceXOf(Position position) {
        return Math.abs(position.getX() - x);
    }

    public int distanceYOf(Position position) {
        return Math.abs(position.getY() - y);
    }

    public int distanceOf(Position position) {
        return (int) Math.sqrt(Math.pow(distanceXOf(position),2) + Math.pow(distanceYOf(position),2));
    }

    public boolean withinBoundaries() {
        return !(getX() < 0 || getX() >= GameMap.WIDTH * GameMap.CELL_SIZE || getY() < 0 || getY() >= GameMap.HEIGHT * GameMap.CELL_SIZE);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !getClass().equals(obj.getClass())) {
            return false;
        }
        Position aux = (Position) obj;
        if(aux.getX()==this.x && aux.getY()==this.y) {
            return true;
        }
        return false;
    }


}
