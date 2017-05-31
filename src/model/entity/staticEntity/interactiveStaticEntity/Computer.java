package model.entity.staticEntity.interactiveStaticEntity;

import model.entity.Direction;
import model.entity.Position;
import model.entity.dynamicEntity.Timer;

/**
 *
 */
public class Computer extends InteractiveStaticEntity {

    private ComputerTimer timer;
    private int consecutiveHacks;
    private int currentConsecutiveHacks;

    public Computer(Position position, Direction direction, int consecutiveHacks) {
        super(position, direction);
        timer = new ComputerTimer();
        this.consecutiveHacks = consecutiveHacks;
        this.currentConsecutiveHacks = 0;
    }

    public void interact() {
        long nowTime = System.currentTimeMillis();

        if(timer.hackTimePassed(nowTime)){
            currentConsecutiveHacks = 1;
            return;
        }

        timer.updateLastHackTime(nowTime);
        currentConsecutiveHacks++;
    }

    public boolean isPassable() {
        return !PASSABLE;
    }

}
