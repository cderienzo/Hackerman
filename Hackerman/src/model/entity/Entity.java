package model.entity;

import java.io.Serializable;

/**
 * Represent an object in the game that occupies a space.
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int IDLE = 0;

    //private static final boolean PASSABLE;

    protected Position position;
    protected Direction direction;

    protected Entity(Position position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    boolean closeEnough(Entity entity, double delta) {
        return this.position.isNearby(entity.getPosition(), delta);
    }

    abstract boolean isPassable();


}
