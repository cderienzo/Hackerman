package controller;

import model.Managers.EntityManager;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Manager {
    class Manager {

        public static final Logger LOGGER = initializeLogger();

        private MasterRenderer masterRenderer;

        // Managers to control
        private RenderManager renderManager;
        private EventManager eventManager;
        private ModelManager modelManager;

        private Notifier notifier;

        private EntityManager entityManager;

        /**
         * Initialized error's file logger.
         * @return logger.
         */
        private static Logger initializeLogger() {
            Logger logger = Logger.getAnonymousLogger();
            String logPath = "./Log/log";
            logger.setUseParentHandlers(false);
            try {
                FileHandler fh = new FileHandler(logPath);
                logger.addHandler(fh);
                logger.setLevel(Level.FINEST);
                fh.setFormatter(new SimpleFormatter());
            } catch (SecurityException | IOException e) {
                // It's okay for the program to blow up at this point,
                // as if the logger could not be loaded, no exceptions will be
                // registered properly during the execution.
                e.printStackTrace();
            }
            return logger;
        }

        public Manager() {
            notifier = new Notifier(this);
            FileManager.createFileManager(this);
            masterRenderer = new MasterRenderer(new Delegate(this));
            renderManager = new RenderManager(this);
            eventManager = new EventManager(this);

            renderManager.initialize();
        }

        /**
         * Initializes the game. Must be called when a new game is requested or a
         * game is to be loaded. Calling this method before having a entityManager set is
         * unsafe and will throw an exception.
         * @throws TickerMissingException if entityManager was not loaded correctly.
         */
        public void initialize() throws TickerMissingException {
            if (entityManager == null)
                throw new TickerMissingException("EntityManager has not been set.");
            entityManager.setNotifier(notifier);
            modelManager = new ModelManager(this);
            modelManager.initialize();
            masterRenderer.initialize();
        }

        /**
         * Updates the game's stage.
         *
         * @param stage stage to update to
         */
        public void updateStage(Stage stage) {
            renderManager.setStage(stage);
            if (!(stage.equals(Stage.MENU) || stage.equals(Stage.MAPSELECTION) || stage.equals(Stage.MODE)))
                toggleMovement();
        }

        /**
         * Change status of the game when showing a game-stopping stage
         */
        public void toggleMovement() {
            Stage stage = getStage();
            if (stage.equals(Stage.MAP))
                entityManager.changeModifier(true);
            else
                entityManager.changeModifier(false);
        }

        /**
         * Gets stage from the renderManager
         * @return stage
         */
        public Stage getStage() {
            return renderManager.getStage();
        }

        /**
         * Gets the entityManager
         * @return entityManager
         */
        public EntityManager getEntityManager() {
            return entityManager;
        }

        /**
         * Updates the stage according to the player death.
         * Ends the game
         */
        public void gameOver() {
            updateStage(eventManager.handlePlayerDeath());
        }

        /**
         * Displays win message and quits.
         */
        public void win() {
            masterRenderer.displayWin();
            System.exit(0);
        }

        /**
         * Handles the keys
         * @param key key to handle
         */
        public void keyChange(int key) {
            updateStage(eventManager.handleEvent(key, getStage()));
        }

        /**
         * Gets the master renderer
         * @return master renderer
         */
        public MasterRenderer getMasterRenderer() {
            return masterRenderer;
        }

        /**
         * Sets the current entityManager to the one specified. This method should be
         * called if and only if the game is to be loaded.
         *
         * @param entityManager The loaded entityManager.
         */
        public void loadTicker(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        /**
         * Gets notifier
         * @return notifier
         */
        public Notifier getNotifier() {
            return notifier;
        }

        /**
         * Shows file missing error
         */
        public void loadFileMissing() {
            masterRenderer.displayFileMissing();
        }

        /**
         * Start the game flow
         * @param args default params
         */
        public static void main(String[] args) {
            new Manager();
        }

    }
}
