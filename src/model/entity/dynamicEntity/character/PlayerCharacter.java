package model.entity.dynamicEntity.character;

import model.entity.Direction;
import model.entity.Entity;
import model.entity.Position;
import model.entity.staticEntity.interactiveStaticEntity.Computer;
import model.entity.staticEntity.interactiveStaticEntity.Door;
import model.entity.staticEntity.interactiveStaticEntity.InteractiveStaticEntity;
import model.gameWorld.GameMap;
import model.gameWorld.Grid;

/**
 *
 */
public class PlayerCharacter extends GameCharacter {

    private boolean interacting;

    public PlayerCharacter(Position position, Direction direction, int velocity) {
        super(position, direction, velocity);
        interacting = false;
    }

    public void tick() {
        if(interacting) {
            interact();
        }
        move();
        updateStatus();
        interacting = false;
    }

    public void setInteracting() {
        interacting = true;
    }

    public void interact() {
        int[] dir = direction.getDir();

        Position objective = new Position(getPosition().getX() + dir[0] * GameMap.CELL_SIZE, getPosition().getY() + dir[1] * GameMap.CELL_SIZE);

        if(!objective.withinBoundaries())
            return;

        if(InteractiveStaticEntity.class.equals(grid.getCell(objective).getEntity().getClass())) {
            InteractiveStaticEntity entity = (InteractiveStaticEntity) grid.getCell(objective).getEntity();
            entity.interact();
        }
    }
}
