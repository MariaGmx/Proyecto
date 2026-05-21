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
 * Clase GameController: Coordina toda la parte visual del juego con la lógica.
 * Maneja las ventanas, imágenes, puntajes, colisiones y eventos del teclado.
 * Además, controla el flujo principal del juego.
 */
public class GameController implements KeyListener {

    /**
     * Nombre del jugador actual.
     */
    private String playerName;

    /**
     * Objeto principal controlado por el jugador.
     */
    private GamerPot gamer;

    /**
     * Lista de alimentos que caen en el juego.
     */
    private ArrayList<FallingFood> foods;

    /**
     * Panel gráfico donde se dibuja el juego.
     */
    private GamePanel panel;

    /**
     * Nivel actual del juego.
     */
    private main.java.model.Level1 currentLevel;

    /**
     * Puntaje acumulado del jugador.
     */
    private int points;

    /**
     * Indica si la partida sigue activa.
     */
    private boolean isGameActive;

    /**
     * Enemigo del juego.
     */
    private Enemy enemy;

    /**
     * Poción lanzada por el enemigo.
     */
    private EnemyPotion potion;

    /**
     * Constructor vacío de GameController.
     */
    public GameController() {
    }

    /**
     * Asigna el nombre del jugador.
     *
     * @param name nombre del jugador.
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return nombre del jugador.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Obtiene el enemigo actual.
     *
     * @return objeto Enemy.
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * Obtiene la poción del enemigo.
     *
     * @return objeto EnemyPotion.
     */
    public EnemyPotion getPotion() {
        return potion;
    }

    /**
     * Método startGame: inicia toda la lógica principal del juego.
     * <p>
     * Carga el nivel, crea el panel gráfico, inicia los hilos
     * de animación y ejecuta el loop principal del juego.
     * </p>
     */
    public void startGame() {

        loadLevel();

        panel = new GamePanel(
                gamer,
                foods,
                currentLevel,
                playerName,
                enemy,
                potion
        );

        panel.getVentana().addKeyListener(this);
        panel.getVentana().requestFocus();
        panel.repaint();

        isGameActive = true;

        // Inicia la animación del enemigo
        new Thread(enemy).start();

        /**
         * Hilo encargado de hacer aparecer periódicamente
         * al enemigo y lanzar la poción.
         */
        Thread hiloEnemigo = new Thread(() -> {

            while (isGameActive) {

                try {

                    // Espera antes de aparecer
                    Thread.sleep(5000);

                    enemy.aparecer();

                    // Lanza la poción desde el enemigo
                    potion.lanzar(enemy.getX(), enemy.getY());

                    // El enemigo permanece visible
                    Thread.sleep(3000);

                    enemy.desaparecer();

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        });

        hiloEnemigo.start();

        /**
         * Loop principal del juego.
         * Verifica colisiones, estados y actualiza la pantalla.
         */
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

    /**
     * Método obligatorio de KeyListener.
     *
     * @param e evento del teclado.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Detecta cuando el usuario presiona una tecla.
     * <p>
     * Permite mover la olla a la izquierda o derecha.
     * </p>
     *
     * @param e evento del teclado.
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

    /**
     * Método obligatorio de KeyListener.
     *
     * @param e evento del teclado.
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Método checkCollision:
     * verifica las colisiones entre la olla,
     * los alimentos y la poción enemiga.
     */
    public void checkCollision() {

        int[] boxes = currentLevel.getBoxes();

        // Colisiones con alimentos
        for (FallingFood food : foods) {

            for (int i = 0; i < boxes.length; i++) {

                if (gamer.getX() == boxes[i]
                        && food.getX() == boxes[i]) {

                    if (gamer.getY() == food.getY()) {

                        updatePoints(food);
                    }
                }
            }
        }

        // Colisión con la poción enemiga
        if (potion.isVisible()) {

            int potionX = potion.getX();
            int potionY = potion.getY();

            int gamerX = gamer.getX();
            int gamerY = gamer.getY();

            Rectangle gamerBox =
                    new Rectangle(gamerX - 20, gamerY - 20, 140, 140);

            Rectangle potionBox =
                    new Rectangle(potionX, potionY, 100, 100);

            // Verifica si las áreas chocan
            if (gamerBox.intersects(potionBox)) {

                gamer.perderTodasLasVidas();

                potion.detener();
            }
        }
    }

    /**
     * Método updatePoints:
     * suma o resta puntos según el tipo de alimento atrapado.
     *
     * @param food alimento atrapado.
     */
    public void updatePoints(FallingFood food) {

        if (food.isPositive()) {

            gamer.sumarPuntos(10);

        } else {

            gamer.sumarPuntos(-10);
        }
    }

    /**
     * Método restart:
     * reinicia completamente la partida.
     */
    public void restart() {

        if (!isGameActive) {

            gamer.restartAll();

            loadLevel();

            startGame();
        }
    }

    /**
     * Método loadLevel:
     * carga todos los elementos necesarios para iniciar el nivel.
     * <p>
     * Inicializa jugador, alimentos, enemigo y poción.
     * </p>
     */
    public void loadLevel() {

        currentLevel = new main.java.model.Level1();

        gamer = new GamerPot();

        foods = new ArrayList<>();

        String[] imageNames = {"corn.png", "garlic.png"};

        String[] names = {"CORN", "GARLIC"};

        boolean[] isPositive = {true, false};

        // Crea los alimentos del juego
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

        // Inicializa enemigo y poción
        enemy = new Enemy();

        potion = new EnemyPotion();

        new Thread(potion).start();
    }

    /**
     * Método randomBox:
     * selecciona una posición aleatoria del arreglo de casillas.
     *
     * @return posición aleatoria.
     */
    private int randomBox() {

        int[] boxes = currentLevel.getBoxes();

        int index = (int) (Math.random() * boxes.length);

        return boxes[index];
    }

    /**
     * Método verifyState:
     * revisa si el jugador ganó o perdió la partida.
     */
    public void verifyState() {

        if (gamer.isActive()) {

            // El jugador gana
            if (gamer.getScore() >= currentLevel.getMinScore()) {

                isGameActive = false;

            }
            // El jugador pierde
            else if (gamer.getLives() <= 0) {

                isGameActive = false;
            }
        }
    }
}