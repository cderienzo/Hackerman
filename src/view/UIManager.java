package view;

import controller.Manager;
import controller.ModelManager;
import model.Managers.EntityManager;

/**
 * Created by Bianca on 07/06/2017.
 */
public class UIManager {

    private ModelManager modelManager;
    private EntityManager entityManager;
    private HackerGame game;

    public UIManager (ModelManager model) {
        modelManager = model;
        entityManager = modelManager.getEntityManager();
        game = new HackerGame(this, modelManager);
        initialize();
    }

    public void initialize() {
        game.setScreen(new MainMenuScreen(this.game));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public HackerGame getGame() {
        return this.game;
    }

    public void setState (Manager.STATE state) {
        modelManager.getManager().stateManager(state);
    }
}

