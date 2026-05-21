package model;

/**
 * Clase Level1: contiene la configuración del nivel.
 * tiempo limite, vidas, velocidad de lan comida al caer, puntaje minimo, movimiento de GamerPot en x, y numero de enemigos
 */
public class Level1  {

    /**
     * Atributos de la clase Level1
     */
    private int[] boxes = {80,230,3900,550,6900};
    private int lifes = 3;
    private int timeLine = 150; // duracion del nivel en segundos
    private int minScore = 10;
    private int numbEnemies = 5;
    private int foodSpeed = 25;
    private int steps = 152;


    /**
     * getters  y Setters
     *
     * @return
     */
    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int[] getBoxes() {
        return boxes;
    }

    public void setBoxes(int[] boxes) {
        this.boxes = boxes;
    }

    public int getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(int timeLine) {
        timeLine = timeLine;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getNumbEnemies() {
        return numbEnemies;
    }

    public void setNumbEnemies(int numbEnemies) {
        this.numbEnemies = numbEnemies;
    }

    public int getFoodSpeed() {
        return foodSpeed;
    }

    public void setFoodSpeed(int foodSpeed) {
        this.foodSpeed = foodSpeed;
    }

    public int getSteps() {
        return steps;
    }

    public int getLives() {
        return lifes;
    }

    public int getTimeLimitSeconds() {
        return timeLine;
    }

    public int getNumEnemies() {
        return numbEnemies;
    }


}

