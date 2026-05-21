package main.java.view;

import main.java.model.Enemy;
import main.java.model.EnemyPotion;
import main.java.model.FallingFood;
import main.java.model.GamerPot;
import main.java.model.Level1;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Clase GamePanel:
 * representa el panel principal donde se dibujan
 * todos los elementos visuales del juego.
 * <p>
 * Se encarga de mostrar el fondo, el jugador,
 * los alimentos, el enemigo, la poción y la información
 * del jugador como puntaje y vidas.
 * </p>
 */
public class GamePanel extends JPanel {

    /**
     * Enemigo del juego.
     */
    private Enemy enemy;

    /**
     * Poción lanzada por el enemigo.
     */
    private EnemyPotion potion;

    /**
     * Objeto principal controlado por el jugador.
     */
    private GamerPot gamer;

    /**
     * Lista de alimentos que aparecen en el juego.
     */
    private ArrayList<FallingFood> foods;

    /**
     * Imagen de fondo del juego.
     */
    private Image background;

    /**
     * Nombre del jugador actual.
     */
    private String playerName;

    /**
     * Constructor de GamePanel.
     * <p>
     * Inicializa todos los elementos gráficos del juego
     * y configura la ventana principal.
     * </p>
     *
     * @param gamer        objeto del jugador.
     * @param foods        lista de alimentos.
     * @param currentLevel nivel actual del juego.
     * @param playerName   nombre del jugador.
     * @param enemy        enemigo del juego.
     * @param potion       poción lanzada por el enemigo.
     */
    public GamePanel(
            GamerPot gamer,
            ArrayList<FallingFood> foods,
            Level1 currentLevel,
            String playerName,
            Enemy enemy,
            EnemyPotion potion
    ) {

        this.gamer = gamer;

        this.foods = foods;

        this.playerName = playerName;

        this.enemy = enemy;

        this.potion = potion;

        setFocusable(true);

        requestFocusInWindow();

        // Carga la imagen de fondo
        URL url = getClass().getClassLoader()
                .getResource("images/background.png");

        System.out.println(url);

        if (url != null) {

            background = new ImageIcon(url).getImage();

        } else {

            System.out.println("No se encontró background.png");
        }

        // Configuración de la ventana principal
        JFrame ventana = new JFrame();

        ventana.setTitle("Burning Kitchen");

        ventana.setSize(800, 1000);

        ventana.add(this);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.setVisible(true);
    }

    /**
     * Obtiene la ventana principal asociada al panel.
     *
     * @return ventana JFrame del juego.
     */
    public JFrame getVentana() {

        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }

    /**
     * Método paint:
     * dibuja todos los elementos visuales del juego.
     *
     * @param g objeto Graphics utilizado para dibujar.
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);

        // Dibuja el fondo
        if (background != null) {

            g.drawImage(
                    background,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }

        // Filtro gris transparente
        Color greyFilter = new Color(124, 122, 122, 150);

        g.setColor(greyFilter);

        g.fillRect(0, 0, getWidth(), getHeight());

        // Configuración del texto
        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 20));

        // Información del jugador
        g.drawString("Puntaje: " + gamer.getScore(), 20, 30);

        g.drawString("Vidas: " + gamer.getLives(), 20, 60);

        g.drawString("Jugador: " + playerName, 20, 90);

        // Dibuja al jugador
        gamer.toDraw(g);

        // Dibuja los alimentos
        for (FallingFood food : foods) {

            food.toDraw(g);
        }

        // Dibuja el enemigo
        enemy.toDraw(g);

        // Dibuja la poción enemiga
        potion.toDraw(g);
    }
}