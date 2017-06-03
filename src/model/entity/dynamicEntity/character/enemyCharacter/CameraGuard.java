package model.entity.dynamicEntity.character.enemyCharacter;

/**
 * An Enemy that cant move
 */
public class CameraGuard extends EnemyCharacter {


    public CameraGuard(Position position, Direction direction, int velocity) {
        super(position,direction,velocity);
    }

    @Override
    public void tick() {

    }
}

