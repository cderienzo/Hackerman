package model.gameWorld;

import model.Managers.EntityManager;
import model.entity.Direction;
import model.entity.Entity;
import model.entity.Position;
import model.entity.dynamicEntity.character.PlayerCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.CameraGuard;
import model.entity.dynamicEntity.character.enemyCharacter.EnemyCharacter;
import model.entity.dynamicEntity.character.enemyCharacter.Guard;
import model.entity.staticEntity.Obstacle;
import model.entity.staticEntity.interactiveStaticEntity.Computer;
import model.entity.staticEntity.interactiveStaticEntity.Door;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;

/**
 * Created by Mac on 7/6/17.
 */
public class Level {

    private LinkedList<LinkedList<Entity>> grid;
    private EntityManager entityManager;

    public Level(String filename){
        this.grid = setGrid(filename);
        this.entityManager = setEntityManager(grid);
    }

    private String readFile(String filename) {
        File f = new File(filename);
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return new String(bytes,"UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private LinkedList<LinkedList<Entity>> setGrid(String filename) { //el ultimo elemento de cada array es basura para ese elemento
        //ej: guards[guards.lenght-1] no es un guard
        String s = readFile(filename);
        String[] guards = s.split(" GUARDS\n");
        String[] cameraguards = guards[guards.length-1].split(" CAMERAGUARDS\n");
        String[] computers = cameraguards[cameraguards.length-1].split(" COMPUTERS\n");
        String[] doors = computers[computers.length-1].split(" DOORS\n");
        String[] desks = doors[doors.length-1].split(" DESKS\n");
        String[] hackers = desks[desks.length-1].split(" HACKER\n");
        String[] maps = hackers[hackers.length-1].split(" MAP");
        String map = maps[0];
        String[] mapRows = map.split("/");
        LinkedList<LinkedList<Entity>> grid = new LinkedList<>();
        int guardIndex = 0,cameraIndex = 0,computerIndex = 0,doorIndex = 0,deskIndex = 0;
        int rowNumber = 0;
        for(String row:mapRows){
            LinkedList<Entity> aRow = new LinkedList<>();
            grid.add(rowNumber,aRow);
            System.out.println(rowNumber+": "+row);
            String[] cells = row.split(",");
            int cellNumber = 0;
            for(String cell:cells){
                Position position = new Position(rowNumber,cellNumber);
                if(cell.equals("WALL")){
                    Direction direction = new Direction(0);
                    grid.get(rowNumber).add(cellNumber,new Obstacle(position,direction,Obstacle.obstacleType.WALL));
                }
                else if(cell.equals("DOOR")){
                    String[] properties = doors[doorIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    grid.get(rowNumber).add(cellNumber,new Door(position,direction));
                }
                else if(cell.equals("GUARD")){
                    String[] properties = guards[guardIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int velocity=Integer.valueOf(properties[1]);
                    int range=Integer.valueOf(properties[2]);
                    grid.get(rowNumber).add(cellNumber,new Guard(position,direction,velocity,range));
                }
                else if(cell.equals("CAMERAGUARD")){
                    String[] properties = cameraguards[cameraIndex++].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int range=Integer.valueOf(properties[1]);
                    grid.get(rowNumber).add(cellNumber,new CameraGuard(position,direction,range));
                }
                else if(cell.equals("HACKER")){
                    String[] properties = hackers[0].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int velocity=Integer.valueOf(properties[1]);
                    grid.get(rowNumber).add(cellNumber,new PlayerCharacter(position,direction,velocity));
                }
                else if(cell.equals("COMPUTER")){
                    String[] properties = computers[computerIndex].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    int consecutiveHacks=Integer.valueOf(properties[1]);
                    grid.get(rowNumber).add(cellNumber,new Computer(position,direction,consecutiveHacks));
                }
                else if(cell.equals("DESK")){
                    String[] properties = desks[deskIndex].split(",");
                    Direction direction = new Direction(Integer.valueOf(properties[0]));
                    grid.get(rowNumber).add(cellNumber,new Obstacle(position,direction,Obstacle.obstacleType.DESK));
                }
                cellNumber++;
            }
            rowNumber++;
        }
        return grid;
    }

    private EntityManager setEntityManager(LinkedList<LinkedList<Entity>> level) {
        LinkedList<Obstacle> obstacles = new LinkedList<>();
        LinkedList<Computer> computers = new LinkedList<>();
        LinkedList<EnemyCharacter> enemies = new LinkedList<>();
        PlayerCharacter hacker = null;
        Door door = null;
        for(LinkedList<Entity> row:level){
            for(Entity entity:row){
                if(entity instanceof Computer)
                    computers.add((Computer)entity);
                else if(entity instanceof Obstacle)
                    obstacles.add((Obstacle)entity);
                else if(entity instanceof EnemyCharacter)
                    enemies.add((EnemyCharacter)entity);
                else if(entity instanceof Door)
                    door=(Door) entity;
                else if(entity instanceof PlayerCharacter)
                    hacker=(PlayerCharacter)entity;
            }
        }
        return new EntityManager(hacker,door,enemies,computers,obstacles);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public LinkedList<LinkedList<Entity>> getGrid() {
        return grid;
    }
}