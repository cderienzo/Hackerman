package model.entity.dynamicEntity.character.enemyCharacter;

import model.entity.Direction;
import model.entity.Position;
import model.gameWorld.Grid;

import java.util.List;

import static model.entity.Entity.IDLE;

/**
 * An Enemy that cant move
 */
public class CameraGuard extends EnemyCharacter {

    private List<Direction> instructions;

    private int currentDirection;

    private int orientation;
    private static final int NORMAL_ORIENTATION = 1;        //recorre en el sentido en que se guardan las direccions
    private static final int INVERSE_ORIENTATION = -1;

    private boolean playerDetected;

    private static final int ROTATING = 2;
    private static final int TIME_ROTATING = 5000;
    private int timeRotateRemaining;


    public CameraGuard(Position position, Direction direction, int range) {
        super(position, direction, 0, range);
        instructions = null;
        currentDirection = 0;
        playerDetected = false;
    }

    public CameraGuard(Position position, Direction direction, int range, List<Direction> instructions) {
        super(position, direction, 0, range);
        this. instructions = instructions;
        currentDirection = 0;
        playerDetected = false;
    }

    public void addInstruction(Direction direction) {
        instructions.add(direction);
    }

    public void addInstruction(Direction direction, int index) {
        instructions.add(index, direction);
    }

    public boolean hackerDetected() {
        return playerDetected;
    }

    public void tick() {
        if(getMylight().collision(position, direction, grid)) {
            playerDetected = true;
        }
        if(instructions == null) {
            return;
        }
        if(getState() == IDLE) {
            rotate(nextDirection());
            state = ROTATING;
            timeRotateRemaining = TIME_ROTATING;
        }
        timeRotateRemaining--;
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
        if(getPosition().equals(instructions.get(currentDirection))) {
            currentDirection = (currentDirection + orientation) % instructions.size();
        }
    }

    private void updateOrientation() {
        if(getDirection().equals(instructions.get(0))) {               //si estoy en la primer direccion
            orientation = NORMAL_ORIENTATION;
        }
        else if(getDirection().equals(instructions.get(0))) {          //si estoy en la ultima posision
            orientation = INVERSE_ORIENTATION;
        }
    }

    @Override
    protected void updateStatus() {
        if (state == ROTATING && timeRotateRemaining <= 0)
            state = IDLE;
    }

    private boolean isCycle() {
        return instructions.get(0).equals(instructions.get(instructions.size()));
    }
}

