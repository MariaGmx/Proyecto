package main.java.controller;

import main.java.model.Enemy;
import main.java.model.EnemyPotion;
import main.java.model.FallingFood;
import main.java.model.GamerPot;
import main.java.view.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Clase GameController: Coordina toda la parte visual del juego con la logica.
 * las ventanas, las imagenes, los puntos, las colisiones e implementa KeyListener
 */
public class GameController implements KeyListener {

    /**
     * Atributos de GameController
     */
    private String playerName;
    private GamerPot gamer;
    private ArrayList<FallingFood> foods;
    private GamePanel panel;
    private main.java.model.Level1 currentLevel;
    private int points;
    private boolean isGameActive;
    private Enemy enemy;        // ✅ NUEVO
    private EnemyPotion potion; // ✅ NUEVO

    /**
     * Constructor sin parametros de GameController
     */
    public GameController() {
    }

    /**
     * Setters y Getters de playerName
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * Getters de enemy y potion
     */
    public Enemy getEnemy() {
        return enemy;
    }        // ✅ NUEVO

    public EnemyPotion getPotion() {
        return potion;
    } // ✅ NUEVO

    /**
     * Metodo startGame: Inicia el juego llamando a loadLevel()
     */
    public void startGame() {

        loadLevel();
        panel = new GamePanel(gamer, foods, currentLevel, playerName, enemy, potion);
        panel.getVentana().addKeyListener(this);
        panel.getVentana().requestFocus();
        panel.repaint();
        isGameActive = true;

        // ✅ NUEVO: hilo de animación del enemigo
        new Thread(enemy).start();

        // ✅ NUEVO: hilo que hace aparecer el enemigo cada 5 segundos
        Thread hiloEnemigo = new Thread(() -> {
            while (isGameActive) {
                try {
                    Thread.sleep(5000); // espera 5 segundos
                    enemy.aparecer();
                    potion.lanzar(enemy.getX(), enemy.getY());
                    Thread.sleep(3000); // visible 3 segundos
                    enemy.desaparecer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        hiloEnemigo.start();

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
     * Metodo checkCollision: Verifica si hubo colisión entre un objeto de tipo FallingFood y GamerPot
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
//colision con el enemigo
        if (potion.isVisible()) {

            int potionX = potion.getX();
            int potionY = potion.getY();

            int gamerX = gamer.getX();
            int gamerY = gamer.getY();

            Rectangle gamerBox =
                    new Rectangle(gamerX - 20, gamerY - 20, 140, 140);

            Rectangle potionBox =
                    new Rectangle(potionX, potionY, 100, 100);

            if (gamerBox.intersects(potionBox)) {

                gamer.perderTodasLasVidas();

                potion.detener();
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
     */
    public void loadLevel() {
        currentLevel = new main.java.model.Level1();
        gamer = new GamerPot();
        foods = new ArrayList<>();

        String[] imageNames = {"corn.png", "garlic.png"};
        String[] names = {"CORN", "GARLIC"};
        boolean[] isPositive = {true, false};

        for (int i = 0; i < imageNames.length; i++) {
            FallingFood food = new FallingFood(
                    currentLevel.getBoxes(),
                    imageNames[i],
                    names[i],
                    isPositive[i],
                    i * 2000
            );
            foods.add(food);
            new Thread(food).start();
        }

        // ✅ NUEVO: inicializar enemigo y poción
        enemy = new Enemy();
        potion = new EnemyPotion();
        new Thread(potion).start();
    }

    /**
     * Metodo randomBox: escoge una posición al azar del arreglo de casillas.
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
                isGameActive = false;
            } else if (gamer.getLives() <= 0) {
                isGameActive = false;
            }
        }
    }
}