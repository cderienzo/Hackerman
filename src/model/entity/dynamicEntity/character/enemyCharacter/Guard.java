package model.entity.dynamicEntity.character.enemyCharacter;

/**
 * An Enemy that moves
 */
public class Guard extends EnemyCharacter {


   public Guard(Position position, Direction direction, int velocity) {
        super(position,direction,velocity);
    }

    @Override
    public void tick() {

    }
}

