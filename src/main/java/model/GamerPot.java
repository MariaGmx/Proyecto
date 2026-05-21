package model;

import java.awt.*;


public class GamerPot extends Entity {

    /**
     * // constantes y variables de  GamerPot
     */
    private static final int X_INITIAL = 250;
    private static final int Y_INITIAL = 550;
    private static final int SPEED = 1;
    private static final int INITIAL_LIVES = 3;
    private int currentBox = 0;


    /**
     * Variables de gamerPot
     */
    // variable de vidas
    private int lives;

    //variable de puntuacion
    private int score = 0;


    /**
     * Getters y Setters
     *
     * @return
     */
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    /**
     * Constructor de GamerPot
     */
    public GamerPot() {
        super(X_INITIAL, Y_INITIAL, uploadImage("pot.png"));
        this.lives = INITIAL_LIVES;
        setWidth(100);
        setHeight(100);
        setActive(true);

    }

    /**
     * Metodo abstracto de Entity para actualizar
     */
    @Override
    public void update() {
        setX(X_INITIAL);
        setY(Y_INITIAL);
        setSprite(uploadImage("pot.png"));
        setActive(true);
        this.lives = INITIAL_LIVES;

    }

    @Override
    public void toDraw(Graphics g) {
        g.drawImage(getSprite(), getX(), getY(), 300, 300, null);

    }

    /**
     * Metodo que reinicia por completo, posicion, vidas y puntaje
     */
    public void restartAll() {
        update();
        this.score = 0;
    }

    /**
     * Suma puntos a la puntuación acumulada.
     */
    public void sumarPuntos(int puntos) {
        this.score += puntos;
    }

    /**
     * Descuenta una vida.
     */
    public void loseLife() {
        if (lives > 0) lives--;
    }

    /**
     * Metodo para mover GamerPOt a la derecha
     */
    public void moveRight(int step) {
        if (x + step <= 600) {
            x += step;
        }
    }

    /**
     * Metodo para mover GamerPOt a la izquierda
     */
    public void moveLeft(int step) {
        if (x - step >= 0) {
            x -= step;
        }
    }

}