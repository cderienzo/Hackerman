package model.entity.dynamicEntity.character.enemyCharacter;

/**
 * A character with a lattern
 */
public abstract class EnemyCharacter extends Character {

    public EnemyCharacter(Position position, Direction direction, int velocity) {
        super(position, direction, velocity);
    }
}

