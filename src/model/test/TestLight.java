package model.test;


import model.Managers.EntityManager;
import model.entity.Direction;
import model.entity.Position;
import model.entity.dynamicEntity.character.PlayerCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.Guard;
import model.gameWorld.GameMap;
import model.gameWorld.OccupiedCellException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franciscosanguineti on 5/6/17.
 */
@BeforeEach


public class TestLight {
    @Test

    public void testLight() {
        PlayerCharacter player = new PlayerCharacter(new Position(48,48), new Direction(0), 3, 10);
        Guard guard = new Guard(new Position(48,80), new Direction(0), 10, 4);

        List<EnemyCharacter> enemies = new ArrayList<EnemyCharacter>();
        enemies.add(guard);

        EntityManager entityManager = new EntityManager(player, null, enemies, null, null);
        GameMap gameMap = new GameMap(entityManager);

        System.out.println(guard.hackerDetected(gameMap.getGrid()));
    }


}