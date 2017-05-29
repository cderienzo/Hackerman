package model.entity;

import java.io.Serializable;

/**
 * Created by franciscosanguineti on 29/5/17.
 */
public class Position implements Serializable {

    private static final long;
    //capaz en int
    private double x;
    private double y;


    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position otherPosition) {
        this.x = otherPosition.getX();
        this.y = otherPosition.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    public void move(double x , double y) {
        this.x += x;
        this.y += y;
    }

    public Direction directionTo(Position position) {
        return directionBetween(this,position);
    }

    public double distanceXOf(Position position) {
        return Math.abs(position.getX() - x);
    }

    public double distanceYOf(Position position) {
        return Math.abs(position.getY() - y);
    }

    public double distanceOf(Position position) {
        return Math.sqrt(Math.pow(distanceXOf(position),2) + Math.pow(distanceYOf(position),2));
    }

    public boolean isNearby(Position position, double delta) {
        return distanceOf(position) <= delta;
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
