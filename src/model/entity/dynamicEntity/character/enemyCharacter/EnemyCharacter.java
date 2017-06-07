package model.entity.dynamicEntity.character.enemyCharacter;

import model.entity.Direction;
import model.entity.Position;
import model.entity.dynamicEntity.character.GameCharacter;
import model.gameWorld.Grid;

/**
 * A character with a lantern
 */
public abstract class EnemyCharacter extends GameCharacter {

    private Light mylight;

    protected EnemyCharacter(Position position, Direction direction, int velocity, int range) {
        super(position, direction, velocity);
        mylight = new Light(range);
    }

    public abstract boolean hackerDetected();

    public Light getMylight() {
            return mylight;
        }

    public void setMylight(Light mylight) {
            this.mylight = mylight;
        }

}

