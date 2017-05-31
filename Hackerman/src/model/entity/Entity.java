package model.entity;

import java.io.Serializable;

/**
 * Represent an object in the game that occupies a space.
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int IDLE = 0;

    protected Position position;

}
