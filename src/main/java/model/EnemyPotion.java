package main.java.model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class EnemyPotion implements Runnable {

    private int x;
    private int y;

    private Image image;

    private boolean visible;

    private int speed = 8;

    public EnemyPotion() {

        URL url = getClass().getClassLoader()
                .getResource("images/potion.png");

        if (url != null) {

            image = new ImageIcon(url).getImage();

        } else {

            System.out.println("No se encontró potion.png");
        }

        visible = false;
    }

    // sale desde el enemigo
    public void lanzar(int enemyX, int enemyY) {

        x = enemyX + 40;

        y = enemyY + 80;

        visible = true;
    }

    public void detener() {

        visible = false;

        y = 0;
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

    @Override
    public void run() {

        while (true) {

            if (visible) {

                y += speed;

                // sale de pantalla
                if (y > 1000) {

                    detener();
                }
            }

            try {

                Thread.sleep(30);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public void toDraw(Graphics g) {

        if (visible && image != null) {

            g.drawImage(
                    image,
                    x,
                    y,
                    50,
                    50,
                    null
            );
        }
    }
}