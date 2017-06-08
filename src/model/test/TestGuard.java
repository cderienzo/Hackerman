package model.test;

import controller.ModelManager;
import model.Managers.EntityManager;
import model.entity.Direction;
import model.entity.Position;
import model.entity.dynamicEntity.character.PlayerCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.Guard;
import model.entity.staticEntity.interactiveStaticEntity.Computer;
import model.entity.staticEntity.interactiveStaticEntity.Door;
import model.gameWorld.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franciscosanguineti on 7/6/17.
 */
public class TestGuard {

    public static void main(String[] args) {
        PlayerCharacter player = new PlayerCharacter(new Position(80,16), new Direction(Direction.UP), 10);
        Guard guard = new Guard(new Position(80,300), new Direction(Direction.DOWN_RIGHT), 10, 4);

        guard.addInstruction(new Position(80,16));

        List<EnemyCharacter> enemies = new ArrayList<EnemyCharacter>();
        enemies.add(guard);

        Door door = new Door(new Position(200,200), new Direction(Direction.UP));

        Computer computer = new Computer(new Position(300,200), new Direction(Direction.UP),10);
        List<Computer> computers = new ArrayList<Computer>();
        computers.add(computer);


        EntityManager entityManager = new EntityManager(player, door, enemies, computers, null);
        ModelManager modelManager = new ModelManager();

        modelManager.getGameModel().nextLevel(entityManager);

        modelManager.initialize();
    }
}

