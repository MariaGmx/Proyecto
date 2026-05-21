package main.java.controller;

import main.java.model.FallingFood;
import src.main.java.model.GamerPot;
import src.main.java.view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Clase GameController: Coordina toda la parte visual del jugeo con la logica.
 * las ventanas, las imagenes, los puntos, las colisiones e implementa KeyListener
 */
public class GameController implements KeyListener {

    /**
     * Atributos de GameContoller
     */

    private String playerName;

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    private GamerPot gamer;
    private ArrayList<FallingFood> foods;
    private GamePanel panel;
    private src.main.java.model.Level1 currentLevel;
    private int points;
    private boolean isGameActive;

    /**
     * Constructor sin parametros de GameController
     */
    public GameController() {
    }

    /**
     * Metodo startGame: Inicia el juego llamndo a loadLevel()
     */
    public void startGame() {

        loadLevel();
        panel = new GamePanel(gamer, foods, currentLevel);
        panel.getVentana().addKeyListener(this);
        panel.getVentana().requestFocus();
        panel.repaint();
        isGameActive = true;
        //  Integrante 2 - conectar GamePanel aquí !!!!!!!!!!!!!!!!!!!!!
        //  Integrante 2 - arrancar Timer aquí!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        Thread loopJuego = new Thread(new Runnable() {
            public void run() {
                while (isGameActive) {
                    checkCollision();
                    verifyState();
                    panel.repaint();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        loopJuego.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo de KeyListener permite vincular un botón con una acción en el codigo.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamer.moveLeft(currentLevel.getSteps());

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gamer.moveRight(currentLevel.getSteps());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    /**
     * Metodo checkCollision: Vferifica si hubo colisión entre un objeto de tipo FallingFood y GamerPot
     */
    public void checkCollision() {

        int[] boxes = currentLevel.getBoxes();
        for (FallingFood food : foods) {
            for (int i = 0; i < boxes.length; i++) {
                if (gamer.getX() == boxes[i] && food.getX() == boxes[i]) {
                    if (gamer.getY() == food.getY()) {
                        updatePoints(food);
                    }
                }
            }
        }
    }


    /**
     * Metodo updatePoints: Suma o resta puntos según el tipo de alimento que el jugador atrape.
     *
     * @param food
     */
    public void updatePoints(FallingFood food) {
        if (food.isPositive()) {
            gamer.sumarPuntos(10);
        } else {
            gamer.sumarPuntos(-10);
        }
    }

    /**
     * Metodo restart: reinicia todo desde cero, nuevo jugador, se llama desde Game Over
     */
    public void restart() {
        if (!isGameActive) {
            gamer.restartAll();
            loadLevel();
            startGame();
        }
    }


    /**
     * Método loadLevel: toma los valores del nivel activo y configura el juego
     * Crea el currentLevel
     * Crea el gamer con las vidas del nivel
     * Crear las  foods con las casillas y velocidad del nivel
     * Arranca los hilos de cada food
     */
    public void loadLevel() { //-------------------FALTA AGREGAR MAS COMIDA------------------------------!!!!!!!!!!!!!!!
        currentLevel = new src.main.java.model.Level1();
        gamer = new GamerPot();
        foods = new ArrayList<>();

        String[] imageNames = {"corn.png", "garlic.png"};
        String[] names = {"CORN", "GARLIC"};
        boolean[] isPositive = {true, false};

        int[] boxes = currentLevel.getBoxes();


        for (int i = 0; i < imageNames.length; i++) {
            FallingFood food = new FallingFood(currentLevel.getBoxes(), imageNames[i], names[i], isPositive[i], i * 2000);
            foods.add(food);
            new Thread(food).start();
        }


    }


    /**
     * Metodo randomBox: Toma el arreglo de casillas del nivel activo escoge una posición al azar.
     *
     * @return
     */
    private int randomBox() {
        int[] boxes = currentLevel.getBoxes();
        int index = (int) (Math.random() * boxes.length);
        return boxes[index];
    }

    /**
     * Metodo verifyState: revisar si el jugador ganó, perdió o se quedó sin vidas.
     */
    public void verifyState() {

        if (gamer.isActive()) {

            if (gamer.getScore() >= currentLevel.getMinScore()) {
                // pasa de nivel sin importar las vidas
                isGameActive = false;
                // cargar siguiente nivel aquí !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            } else if (gamer.getLives() <= 0) {
                isGameActive = false;
            }
        }
    }
}

