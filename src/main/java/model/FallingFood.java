package model;

import java.awt.*;

/**
 * Clase FallingFood extiende de Runnable, son los elemenos que descienden de la parte superior de la pantalla,
 * dan y quitan puntos al jugador
 */
public class FallingFood extends Entity implements Runnable {

    /**
     * Variables de la clase FallingFood
     */
    private String name;
    private int code;
    private int speed = 17;
    private int[] boxes;
    private int initialDelay;

    /**
     * Constantes para el puntaje
     */
    public static final int POINTS_10 = 1;
    public static final int POINTS_20 = 2;
    public static final int KILLER = 0;

    /**
     * Constructor de la clase FallingFood
     * @param boxes
     * @param imagePath
     * @param name
     * @param code
     * @param initialDelay
     */
    public FallingFood(int[] boxes, String imagePath, String name, int code, int initialDelay) {
        super(boxes[(int) (Math.random() * boxes.length)], -150, Entity.uploadImage(imagePath)); // convierte el String a BufferedImage
        this.boxes = boxes;
        this.name = name;
        this.code = code;
        this.initialDelay = initialDelay;

    }

    /**
     * Metodo run() de Runnable()
     */

    @Override
    public void run() {
        {
            try {
                Thread.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                y += speed;
                if (y >= 600) update();
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Metodo heredado de Entity; define como se actualiza la imagen del objeto tipo FallingFood
     */
    @Override
    public void update() {
        y = -17; // desde donde cae la comida
        x = boxes[(int) (Math.random() * boxes.length)];
        setActive(true);
    }

    /**
     * Metodo abstracto toDraw heredado de Entity; define como se dibuja el objeto de tipo FallingFood
     * @param g
     */
    @Override
    public void toDraw(Graphics g) {
        g.drawImage(getSprite(), x - 30, y, 110, 110, null);
    }

    /**
     * Getter
     */
    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}

