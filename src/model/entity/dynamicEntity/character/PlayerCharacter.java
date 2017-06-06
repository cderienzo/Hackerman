package model.entity.dynamicEntity.character;

import model.entity.Direction;
import model.entity.Position;
import model.entity.staticEntity.interactiveStaticEntity.Computer;
import model.entity.staticEntity.interactiveStaticEntity.Door;

/**
 *
 */
public class PlayerCharacter extends GameCharacter {

    private static final int HACKING = 2;

    // Health fields
    private int lives;
    private int currentLives;

    public PlayerCharacter(Position position, Direction direction, int maxLives, int velocity) {
        super(position, direction, velocity);
        this.lives = maxLives;
        this.currentLives = maxLives;
    }

    public boolean lives(){
        return currentLives > 0;
    }

    public void decreaseLife(){
        lives--;
    }

    public void tick() {
        move();
        updateStatus();
    }

    public void hack(Computer computer) {
        computer.interact();
    }

    public void openDoor(Door door) {
        door.interact();
    }
}
