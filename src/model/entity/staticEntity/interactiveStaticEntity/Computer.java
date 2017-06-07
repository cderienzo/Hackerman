package model.entity.staticEntity.interactiveStaticEntity;

import model.entity.Direction;
import model.entity.Position;

/**
 *
 */
public class Computer extends InteractiveStaticEntity {

    private ComputerTimer timer;
    private int consecutiveHacks;
    private int currentConsecutiveHacks;
    private boolean isOn;
    private boolean isHacked;

    public Computer(Position position, Direction direction, int consecutiveHacks) {
        super(position, direction);
        timer = new ComputerTimer();
        this.consecutiveHacks = consecutiveHacks;
        this.currentConsecutiveHacks = 0;
        isOn = false;
        isHacked = false;
    }

    public void interact() {
        if(isOn) {
            long nowTime = System.currentTimeMillis();

            if (timer.hackTimePassed(nowTime)) {
                currentConsecutiveHacks = 1;
                return;
            }

            timer.updateLastHackTime(nowTime);
            currentConsecutiveHacks++;

            if(currentConsecutiveHacks == consecutiveHacks) {
                isHacked = true;
            }
        }
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

    public void setOn() {
        isOn = true;
    }

    public void setOff() {
        isOn = false;
    }

    public boolean isHacked() {
        return isHacked;
    }

    public boolean isOn() {
        return isOn;
    }
}
