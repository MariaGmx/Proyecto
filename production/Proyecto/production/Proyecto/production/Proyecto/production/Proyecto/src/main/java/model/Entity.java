package main.java.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 * Clase abstracta para todas las entidades del juego (Player, FallingFood, AngryChef, HappyChef).
 * define Posición en x, y, ancho, alto, nombre y sprite
 */
public abstract class Entity {

    /**
     * Atibutos de la entidad
     */

    // Posición de la entidad
    protected int x;
    protected int y;

    // Dimensiones de la entidad
    private int width;
    private int height;

    // Imagen que representa la entidad
    private BufferedImage sprite;

    // Indica si la entidad está activa en el juego, para poder "reutilizar" los hilos
    private boolean active;
    protected Image image;


    public Entity(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;

        this.sprite = sprite;

    }

    /*
     * Cada subclase debe definir su propia lógica de reinicio
     */
    public abstract void update();

    /*
     * Carga una imagen desde la carpeta de recursos.
     * Uso: Entidad.cargarImagen("pacman_left.png")
     */
    public static BufferedImage uploadImage(String nombre) {
        try {
            InputStream is = Entity.class.getResourceAsStream("/src/main/resources/images/" + nombre);
            if (is == null) {
                System.err.println("Imagen no encontrada: " + nombre);
                return null;
            }
            return ImageIO.read(is);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + nombre);
            return null;
        }
    }

    public abstract void toDraw(Graphics g);


    /**
     * Métodos getters y setters
     *
     * @return
     */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
