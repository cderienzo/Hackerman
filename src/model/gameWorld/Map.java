package model.gameWorld;

import model.entity.dynamicEntity.DynamicEntity;
import model.entity.dynamicEntity.character.PlayerCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import model.entity.staticEntity.StaticEntity;

import java.util.List;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Map {


    public static final int HEIGHT = 640;
    public static final int WIDTH = (HEIGHT * 3) / 4;
    public static int CELL_SIZE = 32;

    private Grid grid;

    private PlayerCharacter player;
    private List<EnemyCharacter> enemyes;

    public Map(PlayerCharacter player, List<StaticEntity> staticEntities, List<DynamicEntity>) {
        this.player = player;
        this.enemyes = enemyes;
        grid = new Grid();
    }



}
