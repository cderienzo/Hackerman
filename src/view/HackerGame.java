package view;

import Controller.ModelManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Bianca on 07/06/2017.
 */
public class HackerGame extends Game {

    SpriteBatch batch;
    private UIManager manager;
    private ModelManager model;
    private EntityManager entityManager;

    private MainMenuScreen mainMenuScreen;
    private ExitScreen exitScreen;
    private PausedScreen pausedScreen;
    private GameOverScreen gameOverScreen;
    private GameScreen gameScreen;


    public HackerGame (UIManager manager, ModelManager model) {
        this.manager = manager;
        this.model = model;
        this.entityManager = manager.getEntityManager();
        mainMenuScreen = new MainMenuScreen(this);
        exitScreen = new ExitScreen(this);
        pausedScreen = new PausedScreen(this);
        gameOverScreen = new GameOverScreen(this);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    public void createGameScreen(HackerGame game) {
        gameScreen = new GameScreen(this);
    }

    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    public GameOverScreen getGameOverScreen() {
        return this.gameOverScreen;
    }

    public ExitScreen getExitScreen() {
        return this.exitScreen;
    }

    public PausedScreen getPausedScreen() {
        return this.pausedScreen;
    }




}
