package model.test;


import model.Managers.EntityManager;
import model.entity.Direction;
import model.entity.Position;
import model.entity.dynamicEntity.character.PlayerCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.Guard;
import model.entity.staticEntity.interactiveStaticEntity.Computer;
import model.entity.staticEntity.interactiveStaticEntity.Door;
import model.gameWorld.GameMap;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.Assert.*;
//import static org.junit.Assert.assertTrue;

/**
 * Created by brothers on 07/06/2017.
 */
public class LightTest {
    PlayerCharacter player;
    Guard guard;
    List<EnemyCharacter> enemies;
    Door door;
    Computer computer;
    List<Computer> computers;
    EntityManager entityManager;
    GameMap gameMap;

    @Before
    public void createEntities() {
        player = new PlayerCharacter(new Position(112,80), new Direction(Direction.UP), 10);
        guard = new Guard(new Position(16,48), new Direction(Direction.DOWN_RIGHT), 10, 4);

        enemies = new ArrayList<EnemyCharacter>();
        enemies.add(guard);

        door = new Door(new Position(100,200), new Direction(Direction.UP));

        computer = new Computer(new Position(300,200), new Direction(Direction.UP),10);
        computers = new ArrayList<Computer>();
        computers.add(computer);

        entityManager = new EntityManager(player, door, enemies, computers, null);
        gameMap = new GameMap(entityManager);
    }

    @Test
    public void shouldCollision() throws Exception {
        assertTrue(guard.getMylight().collision(guard.getPosition(), guard.getDirection(), gameMap.getGrid()), "There was a collision");
    }

}