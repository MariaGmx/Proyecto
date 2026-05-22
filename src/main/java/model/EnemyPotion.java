package model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase que representa la poción lanzada por el enemigo.
 * <p>
 * La poción se mueve verticalmente hacia abajo y puede
 * aparecer o desaparecer durante la partida.
 * Además, implementa {@link Runnable} para actualizar
 * su movimiento en un hilo independiente.
 * </p>
 */
public class EnemyPotion implements Runnable {

    /**
     * Posición horizontal de la poción.
     */
    private int x;

    /**
     * Posición vertical de la poción.
     */
    private int y;

    /**
     * Imagen de la poción.
     */
    private Image image;

    /**
     * Indica si la poción está visible en pantalla.
     */
    private boolean visible;

    /**
     * Velocidad de movimiento de la poción.
     */
    private int speed = 8;

    /**
     * Constructor de la clase EnemyPotion.
     * <p>
     * Carga la imagen de la poción desde la carpeta
     * {@code resources/images}.
     * </p>
     */
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

    /**
     * Lanza la poción desde la posición del enemigo.
     *
     * @param enemyX posición horizontal del enemigo.
     * @param enemyY posición vertical del enemigo.
     */
    public void lanzar(int enemyX, int enemyY) {

        x = enemyX + 40;

        y = enemyY + 80;

        visible = true;
    }

    /**
     * Detiene la poción y la hace desaparecer.
     */
    public void detener() {

        visible = false;

        y = 0;
    }

    /**
     * Verifica si la poción está visible.
     *
     * @return {@code true} si la poción está visible,
     * {@code false} en caso contrario.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Obtiene la posición horizontal de la poción.
     *
     * @return coordenada X de la poción.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la posición vertical de la poción.
     *
     * @return coordenada Y de la poción.
     */
    public int getY() {
        return y;
    }

    /**
     * Método ejecutado por el hilo de la poción.
     * <p>
     * Mientras la poción sea visible, se moverá
     * continuamente hacia abajo.
     * </p>
     */
    @Override
    public void run() {

        while (true) {

            if (visible) {

                // Movimiento vertical de la poción
                y += speed;

                // Verifica si salió de la pantalla
                if (y > 1000) {

                    detener();
                }
            }

            try {

                // Pausa el movimiento durante 30 ms
                Thread.sleep(30);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    /**
     * Dibuja la poción en pantalla.
     *
     * @param g objeto Graphics utilizado para dibujar.
     */
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