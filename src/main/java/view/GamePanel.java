package main.java.view;

import main.java.model.FallingFood;
import src.main.java.model.GamerPot;
import src.main.java.model.Level1;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Clase GamePanel: Extiende de JPanel, se encarga de coordinar la ventana de juego.
 * tamaño, titulo, imagenes, ventana.
 */
public class GamePanel extends JPanel {

    private GamerPot gamer;
    private ArrayList<FallingFood> foods;
    private Image background;
    private String playerName; // ✅ NUEVO atributo

    /**
     * Constructor por parametros de GamePanel
     *
     * @param gamer
     * @param foods
     * @param currentLevel
     * @param playerName
     */
    public GamePanel(GamerPot gamer, ArrayList<FallingFood> foods, Level1 currentLevel, String playerName) {
        this.gamer = gamer;
        this.foods = foods;
        this.playerName = playerName; // ✅ NUEVO: guarda el nombre
        setFocusable(true);
        requestFocusInWindow();

        URL url = getClass().getClassLoader()
                .getResource("src/main/resources/images/background.png");

        System.out.println(url);

        background = new ImageIcon(url).getImage();

        JFrame ventana = new JFrame();
        ventana.setTitle("Burning Kitchen");
        ventana.setSize(800, 1000);
        ventana.add(this);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    /**
     * Getter de Ventana
     *
     * @return
     */
    public JFrame getVentana() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }

    /**
     * Metodo heredado de JPanel: Escribe en la pantalla, establece el fondo de pantalla con un efecto de opacidad
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

        Color greyFilter = new Color(124, 122, 122, 150);
        g.setColor(greyFilter);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString("Puntaje: " + gamer.getScore(), 20, 30);
        g.drawString("Vidas: " + gamer.getLives(), 20, 60);
        g.drawString("Jugador: " + playerName, 20, 90);

        gamer.toDraw(g);
        for (FallingFood food : foods) {
            food.toDraw(g);
        }
        gamer.toDraw(g);
    }
}