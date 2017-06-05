package model.entity.dynamicEntity.character;

import model.entity.Direction;

import java.io.Serializable;

/**
 * Created by franciscosanguineti on 30/5/17.
 */
public class Timer implements Serializable {
    private static final long serialVersionUID = 1L;

    // Cooldown times for characters
    private static final long MOVE_COOLDOWN_BASE = 15; // in ms

    // Time attributes for characters
    private long lastMoveTime;
    private long moveCooldown;

    // State
    private int state;

    private static final int NODIAGONAL = 0;
    private static final int DIAGONAL = 1;

    public Timer(int velocity) {
        lastMoveTime = 0;
        moveCooldown = MOVE_COOLDOWN_BASE / velocity;
    }

    /**
     * Gets the time for the last movement
     * @return the time for the last movement
     */
    public long getLastMoveTime() {
        return lastMoveTime;
    }

    /**
     * Gets the time necessary to wait after a movement
     * @return the move cooldown time
     */
    public long getMoveCooldown() {
        return moveCooldown;
    }

    /**
     * Sets the time for last movement
     * @param nowTime time to update the last movemnt's time
     * @param direction update the state for diagonal or not diagonal direction
     */

    public void updateLastMoveTime(long nowTime, Direction direction) {
        this.lastMoveTime = nowTime;
        if(direction.isDiagonal()){
            this.state = DIAGONAL;
        }
        this.state = NODIAGONAL;
    }

    public void updateLastMoveTime(long nowTime) {
        this.lastMoveTime = nowTime;
    }

    /**
     * Compares movement time elapsed with the default cooldown or a cooldown for diagonal direcction
     * A cooldown for diagonal direction is 14 / 10 times bigger than default cooldown
     * @param nowTime time to compare
     * @return true if elapsed time overpass the cooldown. false otherwise.
     */
    public boolean moveTimePassed(long nowTime) {
        return (nowTime - this.getLastMoveTime() >= moveCooldown + (state * 4 / 10));
    }
}
