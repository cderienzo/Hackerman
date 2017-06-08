package model.entity.dynamicEntity.character.enemyCharacter;

import model.entity.Direction;
import model.entity.Position;
import model.gameWorld.Grid;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.LinkedList;
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
        instructions = new ArrayList<Position>();
        instructions.add(new Position(position.getX(), position.getY()));
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
        if(getMylight().collision(position, direction, grid)) {
            playerDetected = true;
        }
        if(instructions == null) {
            return;
        }
        if(getState() == IDLE) {
            updateCurrentPosition();
            Direction direction = nextDirection();
            tryToMove(direction);
        }
        move();
        updateStatus();
    }

    public boolean hackerDetected() {
        return playerDetected;
    }

    private Direction nextDirection() {
        if(!isCycle()) {
            updateOrientation();
        }
        return Direction.directionBetween(getPosition(), instructions.get(currentPosition));

    }

    private void updateCurrentPosition() {
        if(getPosition().sameGridIndex(instructions.get(currentPosition))) {       //si mi posicion es una de la lista
            currentPosition = Math.floorMod(currentPosition + orientation, instructions.size());
        }
    }

    private void updateOrientation() {
        if(getPosition().sameGridIndex(instructions.get(0))) {               //si estoy en la primer direccion
            orientation = NORMAL_ORIENTATION;
        }
        else if(getPosition().sameGridIndex(instructions.get(instructions.size() - 1))) {          //si estoy en la ultima posision
            orientation = INVERSE_ORIENTATION;
        }
    }

    private boolean isCycle() {
        return instructions.get(0).sameGridIndex(instructions.get(instructions.size() - 1));
    }
}

