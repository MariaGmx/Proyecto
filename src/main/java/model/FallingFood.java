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
    private boolean isPositive;
    private int speed = 22;
    private int[] boxes;
    private int initialDelay;

    /**
     * Constructor de la clase FallingFood
     * @param boxes
     * @param imagePath
     * @param name
     * @param isPositive
     * @param initialDelay
     */
    public FallingFood(int[] boxes, String imagePath, String name, boolean isPositive, int initialDelay) {
        super(boxes[(int) (Math.random() * boxes.length)], -20, Entity.uploadImage(imagePath)); // convierte el String a BufferedImage
        this.boxes = boxes;
        this.name = name;
        this.isPositive = isPositive;
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
                if (y >= 560) update();
                try {
                    Thread.sleep(200);
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
        y = -20; // desde donde cae la comida
        x = boxes[(int) (Math.random() * boxes.length)];
        setActive(true);


    }

    /**
     * Metodo abstracto toDraw heredado de Entity; define como se dibuja el objeto de tipo FallingFood
     * @param g
     */
    @Override
    public void toDraw(Graphics g) {
        g.drawImage(getSprite(), x, y, 150, 150, null);
    }

    /**
     * Getter
     */
    public String getName() {
        return name;
    }

    /**
     * Metodo isPositive(): Metodo que verifica si un objeto de tipo FallingFood es positivo,
     * es decir, si le suma puntos al jugador. Entonces será True.
     * @return
     */
    public boolean isPositive() {
        return isPositive;
    }
}

