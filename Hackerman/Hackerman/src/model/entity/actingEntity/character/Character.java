package model.entity.actingEntity.character;

/**
 *  An actingEntity that can move
 */
public abstract class Character implements Movable {

    // Movement fields
    private Timer timer;
    protected int moveRemaining;
    private int velocity;

}
