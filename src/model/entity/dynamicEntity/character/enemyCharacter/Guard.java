package model.entity.dynamicEntity.character.enemyCharacter;

import model.entity.Direction;
import model.entity.Position;
import model.gameWorld.Grid;

import java.util.List;

import static model.entity.Entity.IDLE;


/**
 * An Enemy that moves
 */
public class Guard extends EnemyCharacter {

    private List<Position> instructions;
    private int currentPosition;
    private static final int NORMAL_ORIENTATION = 1;        //recorre en el sentido en que se guardan las posiciones
    private static final int INVERSE_ORIENTATION = -1;
    private int orientation = NORMAL_ORIENTATION;
    private boolean playerDetected;

    public Guard(Position position, Direction direction, int velocity, int range) {
        super(position, direction, velocity, range);
        instructions = null;
        currentPosition = 0;
        playerDetected = false;
    }

    public Guard(Position position, Direction direction, int velocity, int range, List<Position> instructions) {
        super(position, direction, velocity, range);
        this. instructions = instructions;
        currentPosition = 0;
        playerDetected = false;
    }

    public void addInstruction(Position position) {
        instructions.add(position);
    }

    public void addInstruction(int index, Position position) {
        instructions.add(index, position);
    }

    public void tick() {
        if(instructions == null) {
            return;
        }
        if(getState() == IDLE) {
            Direction direction = nextDirection();
            tryToMove(direction);
            if(getMylight().collision(position, direction, grid)) {
                playerDetected = true;
            }
        }
        move();
        updateStatus();
    }

    public boolean hackerDetected() {
        return playerDetected;
    }

    public Direction nextDirection() {
        if(isCycle()) {
            updateCurrentPosition();
        }
        else {
            updateOrientation();
            updateCurrentPosition();
        }
        return Direction.directionBetween(getPosition(), instructions.get(currentPosition));

    }

    private void updateCurrentPosition() {
        if(getPosition().equals(instructions.get(currentPosition))) {       //si mi posicion es una de la lista
            currentPosition = (currentPosition + orientation) % instructions.size();
        }
    }

    private void updateOrientation() {
        if(getPosition().equals(instructions.get(0))) {               //si estoy en la primer direccion
            orientation = NORMAL_ORIENTATION;
        }
        else if(getPosition().equals(instructions.get(instructions.size()))) {          //si estoy en la ultima posision
            orientation = INVERSE_ORIENTATION;
        }
    }

    private boolean isCycle() {
        return instructions.get(0).equals(instructions.get(instructions.size()));
    }
}

