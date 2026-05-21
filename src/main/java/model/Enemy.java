package main.java.model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Enemy implements Runnable {

    private int x;
    private int y;
    private boolean visible;

    // array con los sprites
    private Image[] sprites = new Image[3];

    // sprite actual
    private int spriteActual = 0;

    public Enemy() {

        String[] nombres = {
                "enemy1.png",
                "enemy2.png",
                "enemy3.png"
        };

        for (int i = 0; i < nombres.length; i++) {

            URL url = getClass().getClassLoader()
                    .getResource("images/" + nombres[i]);

            if (url != null) {
                sprites[i] = new ImageIcon(url).getImage();
            } else {
                System.out.println("No se encontró: " + nombres[i]);
            }
        }

        visible = false;
    }

    @Override
    public void run() {

        while (true) {

            if (visible) {

                // cambia sprite
                spriteActual = (spriteActual + 1) % 3;
            }

            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // aparece en posición aleatoria
    public void aparecer() {

        x = 50 + (int) (Math.random() * 650);

        y = 15;

        visible = true;
    }

    public void desaparecer() {

        visible = false;

        spriteActual = 0;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void toDraw(Graphics g) {

        if (visible && sprites[spriteActual] != null) {

            g.drawImage(
                    sprites[spriteActual],
                    x,
                    y,
                    100,
                    100,
                    null
            );
        }
    }
}