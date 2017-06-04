package model.entity.dynamicEntity.character.enemyCharacter;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Position;
import model.entity.dynamicEntity.character.PlayerCharacter;
import model.gameWorld.Grid;
import model.gameWorld.Map;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Light {

    private int range;

    public Light(int range) {
        this.range = range;
    }

    public boolean collision(Position guardPosition, Direction guardDirection, Grid grid) {
        int[] guardDir = guardDirection.getDir();

        Position p1 = new Position(guardPosition.getX() + guardDir[0], guardPosition.getY() + guardDir[1]);

        Direction dirRight = new Direction(guardDirection.getCode() + 1);  //al sumarle uno me da la proxima direccion a la derecha
        Direction dirLeft = new Direction(guardDirection.getCode() - 1);

        boolean detected = false;

        for(int i = 0; i < range; i++) {
            detected = checkDirection(p1, dirRight, range-i, grid) || checkDirection(p1, dirLeft, range-i, grid);
            if(detected) {
                return true;
            }
            p1.incrementPosition(guardDir[0] * Map.CELL_SIZE, guardDir[1] * Map.CELL_SIZE);
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
            currentPosition.incrementPosition(dir[0] * Map.CELL_SIZE, dir[1] * Map.CELL_SIZE);
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
