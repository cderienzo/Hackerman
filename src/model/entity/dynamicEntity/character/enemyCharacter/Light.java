package model.entity.dynamicEntity.character.enemyCharacter;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Position;
import model.entity.dynamicEntity.character.PlayerCharacter;
import model.gameWorld.Grid;
import model.gameWorld.GameMap;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Light {

    private int range;

    public Light(int range) {
        this.range = range;
    }

    //Devuelve true si hay colision de la luz con el hacker
    public boolean collision(Position guardPosition, Direction guardDirection, Grid grid) {
        int[] guardDir = guardDirection.getDir(); //Me devuelve vector [-1 0 1, -1 0 1] dependiendo de a donde se dirija el guardia

        Position p1 = new Position(guardPosition.getX() + guardDir[0] * GameMap.CELL_SIZE, guardPosition.getY() + guardDir[1] * GameMap.CELL_SIZE);

        Direction dirRight = new Direction(guardDirection.getCode() + 1);  //al sumarle uno me da la proxima direccion a la derecha
        Direction dirLeft = new Direction(guardDirection.getCode() - 1);

        boolean detected = false;

        for(int i = 0; i < range; i++) {
            detected = checkDirection(p1, dirRight, range-i, grid) || checkDirection(p1, dirLeft, range-i, grid);
            if(detected) {
                return true;
            }
            p1.incrementPosition(guardDir[0] * GameMap.CELL_SIZE, guardDir[1] * GameMap.CELL_SIZE);
        }
        return false;
    }

    private boolean checkDirection(Position currentPosition, Direction currentDirection, int currentRange, Grid grid) {
        int[] dir = currentDirection.getDir();

        for (int i = 0; i < currentRange; i++) {
            //devuelve true si encuentra al hacker
            if(!checkPassable(currentPosition, grid)) {
                return false;
            }
            if(checkPosition(currentPosition, grid)) {
                return true;
            }
            currentPosition.incrementPosition(dir[0] * GameMap.CELL_SIZE, dir[1] * GameMap.CELL_SIZE);
        }
        return false;
    }

    private boolean checkPassable(Position position, Grid grid) {
        if(!position.withinBoundaries() || !grid.isPossibleAdd(position)) {
            return false;
        }
        return true;
    }

    private boolean checkPosition(Position position, Grid grid) {
        Entity entity = grid.getCell(position).getEntity();

        if(entity == null){
            return false;
        }
        if(entity.getClass().equals(PlayerCharacter.class)) {
            return true;
        }
        return false;
    }


}
