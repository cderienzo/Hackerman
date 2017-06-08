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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by brothers on 08/06/2017.
 */
public class PositionTest {
    Direction dir;


    @Test
    public void testDirectionBetween() throws Exception {
        assertEquals("Direction: [0,-1]", Direction.directionBetween(new Position(3,4), new Position(3,2)).toString());
        assertEquals("Direction: [0,1]", Direction.directionBetween(new Position(3,4), new Position(3,8)).toString());
    }
}
