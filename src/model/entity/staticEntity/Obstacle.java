package model.entity.staticEntity;

import model.entity.Direction;
import model.entity.Position;

/**
 *
 */
public class Obstacle extends StaticEntity {

    public enum obstacleType {DESK, WALL};

    private int type;

    public Obstacle(Position position, Direction direction, obstacleType type) {
        super(position,direction);
        this.type = type.ordinal();
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

}
