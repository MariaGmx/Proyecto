package controller;

import model.FallingFood;
import model.GamerPot;
import model.Level1;
import view.GamePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase GameController: Coordina toda la parte visual del jugeo con la logica.
 * las ventanas, las imagenes, los puntos, las colisiones e implementa KeyListener
 */
public class GameController implements KeyListener {

    /**
     * Atributos de GameContoller
     */
    private GamerPot gamer;
    private ArrayList<FallingFood> foods;
    private GamePanel panel;
    private Level1 currentLevel;
    private int points;
    private boolean isGameActive;
    private int timeLeft;
    private Timer timer;

    /**
     * Constructor sin parametros de GameController
     */
    public GameController() {
    }

    /**
     * Metodo stratGame: Inicia el juego llamndo a loadLevel()
     */
    public void startGame() {

        loadLevel();
        isGameActive = true;
        panel = new GamePanel(gamer, foods, currentLevel);
        panel.getVentana().addKeyListener(this);
        panel.getVentana().requestFocus();
        startTimer();

        //  Integrante 2 - conectar pantallas de inicio aquí !!!!!!!!!!!!!!!!!!!!!

        Thread loopJuego = new Thread(new Runnable() {
            public void run() {
                while (isGameActive) {
                    checkCollision();
                    verifyState();
                    gamer.checkPower();
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
        for (FallingFood food : foods) {
            boolean mismaColumna = Math.abs(gamer.getX() - food.getX()) < 80;
            boolean mismaFila = food.getY() >= gamer.getY() && food.getY() <= gamer.getY() + (gamer.getHeight() / 2);
            if (mismaColumna && mismaFila) {
                updatePoints(food);
                food.update();
            }
        }
    }
    /**
     * Metodo updatePoints: Suma o resta puntos según el tipo de alimento que el jugador atrape.
     * @param food
     */
    public void updatePoints(FallingFood food) {
        if (food.getCode() == FallingFood.KILLER) {
            if (!gamer.isPowered()) {
                gamer.setLives(0);
            }
        } else if (food.getCode() == FallingFood.POINTS_10) {
            gamer.sumarPuntos(10);
        } else if (food.getCode() == FallingFood.POINTS_20) {
            gamer.sumarPuntos(20);
            gamer.activatePower();
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
     * <p>
     * Crea el currentLevel
     * Crea el gamer con las vidas del nivel
     * Crear las  foods con las casillas y velocidad del nivel
     * Arranca los hilos de cada food
     */
    public void loadLevel() {
        currentLevel = new Level1();
        gamer = new GamerPot();
        foods = new ArrayList<>();

        String[] imageNames = {"corn.png", "garlic.png", "beef.png","egg.png","tapioca.png","soup.png","bandejaPaisa.png","venom.png"};
        String[] names = {"CORN", "GARLIC", "BEEF", "EGG","TAPIOCA","SOUP","BANDEJA_PAISA","VENOM"};
        int[] codes = {1,1,1,1,1,2,1,0};

        for (int i = 0; i < imageNames.length; i++) {
            FallingFood food = new FallingFood(currentLevel.getBoxes(), imageNames[i], names[i], codes[i], i * 3000);
            foods.add(food);
            new Thread(food).start();
        }


    }


    /**
     * Metodo randomBox: Toma el arreglo de casillas del nivel activo escoge una posición al azar.
     *
     * @return posición x aleatoria de una casilla válida
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
            if (gamer.getLives() <=0 ) {
                timer.stop();
                isGameActive = false;
                // aqui se llama a "GameOverPanel" o "FinalPanel" con mensaje de derrota
            }

    }


    /**
     * Metodo startTimer(): creamos el timer
     */
    private void startTimer(){
        timeLeft = currentLevel.getTimeLimitSeconds();
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                timeLeft--;
                panel.setTimeLeft(timeLeft);
                panel.repaint();
                if(timeLeft <= 0) {
                    timer.stop();
                    isGameActive = false;
                    if (gamer.getLives() > 0) {
                        // GameOverPanel o FinalPanel con mensaje de victoria conectar aqui
                    } else {// GameOverPanel o FinalPanel derrota}
                    }
                }
            }
        });
        timer.start();
    }


    public int getTimeLeft() {
        return timeLeft;
    }
}


