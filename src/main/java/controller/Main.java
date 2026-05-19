package controller;

import view.WelcomePanel;




/**
 * Clase main: Inicializa a Gamecontroller para iniciar el juego.
 */
public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.startGame();
        new WelcomePanel();


    }


}
