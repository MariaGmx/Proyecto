package main.java.model;

public interface Level {
    int[] getBoxes();

    int getLives();

    int getTimeLimitSeconds();

    int getMinScore();

    int getNumEnemies();

    int getFoodSpeed();

    int getSteps();
}
