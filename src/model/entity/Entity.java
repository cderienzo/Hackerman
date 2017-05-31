package model.entity;

import java.io.Serializable;

/**
 * Represent an object in the game that occupies a space.
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int IDLE = 0;

    public static final boolean PASSABLE = true;

    protected Position position;
    protected Direction direction;

    //podriamos tener una forma, por ej circulo o rectangulo
    //protected Shape shape;

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

    protected void setPosition(Position position) {
        this.position = position;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    //public boolean checkCollision(Entity entity, double delta)

    public abstract boolean isPassable();

    public boolean equals(Object object){
        if(object==null || getClass().equals(object.getClass())) {
            return false;
        }
        Entity aux = (Entity) object;
        if(this.getPosition().equals(aux.getPosition())) {
            return true;
        }
        return false;
    }

}
