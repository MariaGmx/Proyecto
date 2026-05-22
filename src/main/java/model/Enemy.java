package model;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase que representa al enemigo del juego.
 * <p>
 * El enemigo puede aparecer en pantalla, cambiar sus sprites
 * para generar una animación y dibujarse en el panel del juego.
 * Además, implementa {@link Runnable} para ejecutar la animación
 * en un hilo independiente.
 * </p>
 */
public class Enemy implements Runnable {

    /**
     * Posición horizontal del enemigo.
     */
    private int x;

    /**
     * Posición vertical del enemigo.
     */
    private int y;

    /**
     * Indica si el enemigo está visible en pantalla.
     */
    private boolean visible;

    /**
     * Arreglo que almacena los sprites del enemigo.
     */
    private Image[] sprites = new Image[3];

    /**
     * Índice del sprite que se está mostrando actualmente.
     */
    private int spriteActual = 0;

    /**
     * Constructor de la clase Enemy.
     * <p>
     * Carga las imágenes de los sprites desde la carpeta
     * {@code resources/images}.
     * </p>
     */
    public Enemy() {

        String[] nombres = {
                "enemy1.png",
                "enemy2.png",
                "enemy3.png"
        };

        // Carga cada sprite en el arreglo
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

    /**
     * Método ejecutado por el hilo del enemigo.
     * <p>
     * Mientras el juego esté activo, el enemigo cambia
     * continuamente de sprite para crear una animación.
     * </p>
     */
    @Override
    public void run() {

        while (true) {

            if (visible) {

                // Cambia el sprite actual para animar el enemigo
                spriteActual = (spriteActual + 1) % 3;
            }

            try {

                // Pausa la animación durante 200 ms
                Thread.sleep(200);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Hace aparecer al enemigo en una posición aleatoria.
     * <p>
     * La posición horizontal cambia aleatoriamente
     * dentro del ancho permitido del panel.
     * </p>
     */
    public void aparecer() {

        x = 50 + (int) (Math.random() * 650);

        y = 15;

        visible = true;
    }

    /**
     * Hace desaparecer al enemigo de la pantalla.
     * <p>
     * También reinicia el sprite actual al primero.
     * </p>
     */
    public void desaparecer() {

        visible = false;

        spriteActual = 0;
    }

    /**
     * Verifica si el enemigo está visible.
     *
     * @return {@code true} si el enemigo está visible,
     * {@code false} en caso contrario.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Obtiene la posición horizontal del enemigo.
     *
     * @return coordenada X del enemigo.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la posición vertical del enemigo.
     *
     * @return coordenada Y del enemigo.
     */
    public int getY() {
        return y;
    }

    /**
     * Dibuja el enemigo en pantalla.
     *
     * @param g objeto Graphics utilizado para dibujar.
     */
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