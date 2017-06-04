package model.entity.dynamicEntity.character.enemyCharacter;

import model.entity.Direction;
import model.entity.Position;

import java.util.List;

import static model.entity.Entity.IDLE;

/**
 * An Enemy that cant move
 */
public class CameraGuard extends EnemyCharacter {

    private List<Direction> instructions;
    private int currentDirection;
    private static final int NORMAL_ORIENTATION = 1;        //recorre en el sentido en que se guardan las direccions
    private static final int INVERSE_ORIENTATION = -1;


    public CameraGuard(Position position, Direction direction, int range) {
        super(position, direction, 0, range);
        instructions = null;
        currentDirection = 0;
    }

    public CameraGuard(Position position, Direction direction, int range, List<Direction> instructions) {
        super(position, direction, 0, range);
        this. instructions = instructions;
        currentDirection = 0;
    }

    public void addInstruction(Direction direction) {
        instructions.add(direction);
    }

    public void addInstruction(Direction direction, int index) {
        instructions.add(index, direction);
    }

    public void tick() {
        if(instructions == null) {
            return;
        }
        if(getState() == IDLE) {
            rotate(nextDirection());
        }
        updateStatus();
    }

    public Direction nextDirection() {
        if(isCycle()) {
            updateCurrentDirection();
        }
        else {
            updateOrientation();
            updateCurrentDirection();
        }
        return instructions.get(currentDirection);
    }

    private void updateCurrentDirection() {
        if(getPosition().equals(instructions.get(currentPosition)) {
            currentPosition = (currentPosition + orientation) % instructions.size();
        }
    }

    private void updateOrientation() {
        if(getPosition.eguals(instructions.get(0))) {               //si estoy en la primer direccion
            orientation = NORMAL_ORIENTATION;
        }
        else if(getPosition.eguals(instructions.get(0))) {          //si estoy en la ultima posision
            orientation = INVERSE_ORIENTATION;
        }
    }

    private boolean isCycle() {
        return instructions.get(0).equals(instructions.get(instructions.size()));
    }
}
}
