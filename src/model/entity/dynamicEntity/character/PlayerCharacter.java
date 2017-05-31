package model.entity.dynamicEntity.character;

import model.entity.Direction;
import model.entity.Position;
import model.entity.staticEntity.interactiveStaticEntity.Computer;
import model.entity.staticEntity.interactiveStaticEntity.Door;
import model.entity.staticEntity.interactiveStaticEntity.InteractiveStaticEntity;

/**
 *
 */
public class PlayerCharacter extends Character {

    private static final int HACKING = 2;

    // Health fields
    private int lives;
    private int currentLives;

    protected PlayerCharacter(Position position, Direction direction, int maxHP, int velocity) {
        super(position, direction, velocity);
        this.lives = maxHP;
        this.currentLives = maxHP;
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
