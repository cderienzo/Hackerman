package model.entity.staticEntity.interactiveStaticEntity;

import model.entity.Direction;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class ComputerTimer {

    private static final long serialVersionUID = 1L;

    // Cooldown times for characters
    private static final long HACKING_BASE = 10; // in ms


    // Time attributes for characters
    private long lastHackTime;
    private long hackBase;


    public ComputerTimer() {
        lastHackTime = 0;
    }


    public long getLastHackTime() {
        return lastHackTime;
    }

    public long getHackBase() {
        return HACKING_BASE;
    }

    public void updateLastHackTime(long nowTime) {
        this.lastHackTime = nowTime;
    }

    public boolean hackTimePassed(long nowTime) {
        return (nowTime - this.getLastHackTime() >= HACKING_BASE);
    }
}
