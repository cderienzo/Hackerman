package controller;

import model.Managers.EntityManager;

/**
 * Created by franciscosanguineti on 31/5/17.
 */
public class Manager {

        // Managers to control
        private ModelManager modelManager;
        private View view;

        public InputManager() {
            if goup
            modelManager.getEntityManager().getPlayer().tryToMove();
            checkGameOver(); //a fran no le gusta NEFASTO
            if pause
            state(pausa)
            if space
            modelManager.getEntityManager.getPlayer.interact();

        }

        public void checkGameOver() {
             if (modelManager.getGameModel().gameOver()) {
                 state(gameOver);
             }
        }


        public Manager() {

            modelManager = new ModelManager();
            view = new View;
            FileManager.createFileManager(this);
            //view llama a initialize del model manager
        }

        public state(estado) {
            switch
            estado inicializar
                modelManager.initialize();
            estado exit
                exit
            estado cargarjuego
                fileManager.initialize();
            estado pausa
                modelManager.getGameModel().setPause();
                view.setState(view.pause);
            estado gameover
                view.setState(view.gameOver);
            estado win
                    if ()



        }

        /**
         * Displays win message and quits.
         */
        public void win() {
            masterRenderer.displayWin();
            System.exit(0);
        }


        /**
         * Sets the current entityManager to the one specified. This method should be
         * called if and only if the game is to be loaded.
         *
         * @param entityManager The loaded entityManager.
         */

        /**
         * Start the game flow
         * @param args default params
         */
        public static void main(String[] args) {
            new Manager();
        }

    }
}
