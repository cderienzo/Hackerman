package com.poo.hackerman.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.poo.hackerman.model.entity.Direction;
import com.poo.hackerman.model.entity.Position;
import com.poo.hackerman.model.entity.dynamicEntity.character.PlayerCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import com.poo.hackerman.model.entity.dynamicEntity.character.enemyCharacter.Guard;
import com.poo.hackerman.model.entity.staticEntity.Obstacle;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Computer;
import com.poo.hackerman.model.entity.staticEntity.interactiveStaticEntity.Door;

import java.util.LinkedList;
import java.util.List;

public class prueba extends ScreenAdapter {

    private static final float WORLD_WIDTH = 640;
    private static final float WORLD_HEIGHT = 480;
    private Viewport viewport;
    private Camera camera;
   // private BitmapFont bitmapFont;
   // private EntityManager entityManager;
    private SpriteBatch batch;

    private Entity hacker;
    private Sprite door;
    private Entity[] enemies;
    private Sprite[] computers, obstacles;


    private Texture doorT, computersT, obstaclesT;
    private Texture hackerT, guardT;
    private Texture background;


    private final Application application;

    public prueba(Application application) {
        this.application = application;
    }
    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        PlayerCharacter player = new PlayerCharacter(new Position(40,40), new Direction(Direction.RIGHT), 20);
        Door doorO = new Door(new Position(100,600),new Direction(Direction.DOWN));

        List<EnemyCharacter> enemiesO = new LinkedList<EnemyCharacter>();
        enemiesO.add(new Guard(new Position(30, 100), new Direction(Direction.DOWN), 40, 40));
        enemiesO.add(new Guard(new Position(200, 200), new Direction(Direction.RIGHT), 40, 40));
        enemiesO.add(new Guard(new Position(380, 250), new Direction(Direction.UP_RIGHT), 40, 40));
        enemiesO.add(new Guard(new Position(500, 250), new Direction(Direction.UP_RIGHT), 40, 40));

        List<Computer> computersO = new LinkedList<Computer>();
        computersO.add(new Computer(new Position(600,230), new Direction(Direction.DOWN), 10));

        List<Obstacle> obstaclesO = new LinkedList<Obstacle>();
        obstaclesO.add(new Obstacle(new Position(150,60), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(300,60), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(450,60), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(150,230), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(300,230), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(450,230), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(150,380), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(300,380), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));
        obstaclesO.add(new Obstacle(new Position(450,380), new Direction(Direction.RIGHT), Obstacle.obstacleType.DESK));

        batch = new SpriteBatch();
        hackerT = new Texture("core/assets/hacker.png");
        guardT = new Texture("core/assets/guard.png");
        doorT = new Texture("core/assets/door.png");
        computersT = new Texture("core/assets/computer.png");
        obstaclesT = new Texture("core/assets/obstacles.png");
        background = new Texture("core/assets/bg.png");

        hacker = new Entity(hackerT, player);
        hacker.setPosition(player.getPosition().getX(),player.getPosition().getY());

        enemies = new Entity[enemiesO.size()];
        for(int i = 0; i < enemiesO.size() ; i++) {
            enemies[i] = new Entity(guardT, enemiesO.get(i));
            (enemies[i]).setPosition(enemiesO.get(i).getPosition().getX(), enemiesO.get(i).getPosition().getY());
        }

        door = new Sprite(doorT);
        computers = new Sprite[computersO.size()];
        obstacles = new Sprite[obstaclesO.size()];

        door.setX(doorO.getPosition().getX());
        door.setY(doorO.getPosition().getY());


        for(int i = 0; i < computersO.size() ; i++) {
            computers[i] = new Sprite(computersT);
            (computers[i]).setX(computersO.get(i).getPosition().getX());
            (computers[i]).setY(computersO.get(i).getPosition().getY());
        }


        for(int i = 0; i < obstaclesO.size() ; i++) {
            obstacles[i] = new Sprite(obstaclesT);
            (obstacles[i]).setX(obstaclesO.get(i).getPosition().getX());
            (obstacles[i]).setY(obstaclesO.get(i).getPosition().getY());
        }


    }



    public void resume() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        clearScreen();
        draw();
//        drawDebug();
    }
    private void update(float delta) {
        hacker.update(delta);
    }
    private void draw() {
//        batch.totalRenderCalls = 0;
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        batch.begin();
       // batch.draw(background, 50, 0);
        door.draw(batch);
        hacker.draw(batch);
        computers[0].draw(batch);
        guardDraw();
        obstaclesDraw();
        batch.end();

        //drawScore();
        // drawLives();
//        System.out.println(batch.totalRenderCalls);
    }

    private void guardDraw() {
        for(Entity guard: enemies) {
            guard.draw(batch);
        }
    }

    private void obstaclesDraw() {
        for(Sprite o : obstacles) {
            o.draw(batch);
        }
    }
    //drawScore?

    private void drawObstacles() {
        for(Sprite s : obstacles) {
            s.draw(batch);
        }
    }

    private void drawEnemies() {
        for(Entity enemy : enemies) {
            enemy.draw(batch);
        }
    }


    private void drawLives() {

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}