package model.entity.actingEntity;

import model.entity.Entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * It´s an entity that has a direction, and it can rotate
 */
public abstract class ActingEntity extends Entity implements Rotable {

    private static final long serialVersionUID = 1L;

    /**
     * Directions to which an actingEntity can be facing.
     */
    public enum Direction {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT;

        private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        /**
         * Returns a random direction.
         *
         * @return A random direction.
         */
        public static Direction randomDirection() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    // State fields
    public static final int MOVING = 1;
    public static final int ROTATING = 2;
    protected int state = IDLE;

}
