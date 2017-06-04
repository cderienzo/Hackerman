package model.entity.dynamicEntity.character.enemyCharacter;

/**
 * A character with a lantern
 */
public abstract class EnemyCharacter extends Character {

    private Light mylight;

    protected EnemyCharacter(Position position, Direction direction, int velocity, int range) {
        super(position, direction, velocity);
        mylight = new Light(range, position, direction);
    }

    public boolean hackerDetected(Grid grid) {
       return mylight.collision(grid)
    }
    

    public Light getMylight() {
        return mylight;
    }

    public void setMylight(Light mylight) {
        this.mylight = mylight;
    }
}
