package view;


import com.badlogic.gdx.InputProcessor;
import

/**
 * Created by Bianca on 07/06/2017.
 */
public class UIManager {

    private ModelManager modelManager;
    private EntityManager entityManager;
    private HackerGame game;
    private InputProcessor myInput;

    public UIManager (ModelManager model) {
        game = new HackerGame(this, modelManager);

        initialize();
    }

    public void initialize() {
        game.setScreen(new MainMenuScreen(this.game));
        myInput = new
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public HackerGame getGame() {
        return this.game;
    }




}
