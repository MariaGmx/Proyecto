package main.java.view;

import main.java.controller.GameController;
import main.java.controller.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase WelcomePanel:
 * representa la ventana principal de bienvenida
 * del juego Burning Kitchen.
 * <p>
 * En esta ventana se muestran:
 * <ul>
 *     <li>El fondo principal del juego.</li>
 *     <li>El logo institucional.</li>
 *     <li>Botones de inicio, instrucciones y estudiantes.</li>
 *     <li>Efectos de sonido y música.</li>
 * </ul>
 * <p>
 * Además, permite iniciar la partida y visualizar
 * información adicional del proyecto.
 *
 * @author Maria
 * @version 1.0
 */
public class WelcomePanel extends JFrame {

    /**
     * Administrador de sonidos del juego.
     */
    private SoundManager sound;

    /**
     * Constructor de WelcomePanel.
     * <p>
     * Inicializa todos los componentes gráficos
     * de la ventana principal:
     * <ul>
     *     <li>Panel de fondo.</li>
     *     <li>Botones interactivos.</li>
     *     <li>Animaciones.</li>
     *     <li>Eventos y sonidos.</li>
     * </ul>
     */
    public WelcomePanel() {

        sound = new SoundManager();

        setTitle("Burning Kitchen");
        setSize(800, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con imagen de fondo
        BackgroundPanel panel = new BackgroundPanel();
        setContentPane(panel);
        panel.setLayout(null);

        // LOGO DE LA UAM
        URL urlLogo = getClass().getClassLoader().getResource("main/resources/images/logoU.png");

        ImageIcon logoIcon = new ImageIcon(urlLogo);

        Image imgEscalada = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        ImageIcon logoFinal = new ImageIcon(imgEscalada);

        JLabel logo = new JLabel(logoFinal);

        logo.setBounds(590, 610, 210, 300);

        panel.add(logo);

        // PANEL ESTUDIANTES
        JPanel estudiantesPanel = new JPanel(null);

        estudiantesPanel.setBounds(150, 150, 500, 500);

        estudiantesPanel.setOpaque(false);

        estudiantesPanel.setVisible(false);

        JLabel estudiantesLabel = new JLabel();

        estudiantesLabel.setBounds(0, 0, 500, 500);

        estudiantesPanel.add(estudiantesLabel);

        panel.add(estudiantesPanel);

        // BOTÓN CERRAR PANEL ESTUDIANTES
        URL urlCerrar = getClass().getClassLoader().getResource("main/resources/images/cerrar.png");

        ImageIcon iconCerrarOriginal = new ImageIcon(urlCerrar);

        Image imagenCerrarEscalada = iconCerrarOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        ImageIcon iconoCerrar = new ImageIcon(imagenCerrarEscalada);

        JButton btnCerrar = new JButton(iconoCerrar);

        btnCerrar.setBounds(420, 0, 60, 60);

        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);

        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButtonSound(btnCerrar, "/sounds/SonidoBonton.wav");

        btnCerrar.addActionListener(e -> {

            estudiantesPanel.setVisible(false);

            panel.repaint();
        });

        estudiantesPanel.add(btnCerrar);

        estudiantesPanel.setComponentZOrder(btnCerrar, 0);

        estudiantesPanel.setComponentZOrder(estudiantesLabel, 1);

        // TÍTULO ANIMADO
        GlowingLabel glowLabel = new GlowingLabel("PROGRAMACIÓN ORIENTADA A OBJETOS");

        glowLabel.setBounds(170, 620, 460, 60);

        panel.add(glowLabel);

        // BOTÓN INICIAR
        URL urlBtn = getClass().getClassLoader().getResource("main/resources/images/boton1.png");

        ImageIcon iconoOriginal = new ImageIcon(urlBtn);

        Image imagenEscaladaBtn = iconoOriginal.getImage().getScaledInstance(250, 90, Image.SCALE_SMOOTH);

        ImageIcon icono = new ImageIcon(imagenEscaladaBtn);

        JButton btnIniciar = new JButton(icono);

        btnIniciar.setBounds(275, 220, 250, 90);

        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusPainted(false);

        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButtonSound(btnIniciar, "/sounds/SonidoSubirNivel.wav");

        btnIniciar.addActionListener(e -> {

            // REPRODUCIR MÚSICA DE FONDO
            sound.playBackgroundMusic("/sounds/MusicaFondo.wav");

            dispose();

            String[] nameHolder = new String[1];

            new NamePanel(() -> {

                GameController controller = new GameController();

                controller.setPlayerName(nameHolder[0]);

                controller.startGame();

            }, nameHolder);
        });

        panel.add(btnIniciar);

        // BOTÓN INSTRUCCIONES
        URL urlInstructions = getClass().getClassLoader().getResource("main/resources/images/botonInstrucciones.png");

        ImageIcon iconOriginalInstructions = new ImageIcon(urlInstructions);

        Image imagenEscaladaInstructions = iconOriginalInstructions.getImage().getScaledInstance(300, 170, Image.SCALE_SMOOTH);

        ImageIcon iconFinalInstructions = new ImageIcon(imagenEscaladaInstructions);

        JButton btnInstructions = new JButton(iconFinalInstructions);

        btnInstructions.setBounds(275, 340, 250, 90);

        btnInstructions.setBorderPainted(false);
        btnInstructions.setContentAreaFilled(false);
        btnInstructions.setFocusPainted(false);

        btnInstructions.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButtonSound(btnInstructions, "/sounds/SonidoBonton.wav");

        btnInstructions.addActionListener(e -> {

            InstructionsPanel instructionsPanel = new InstructionsPanel();

            instructionsPanel.setVisible(true);
        });

        panel.add(btnInstructions);

        // BOTÓN MOSTRAR ESTUDIANTES
        URL urlMostrarEstudiantes = getClass().getClassLoader().getResource("main/resources/images/mostrarestu.png");

        ImageIcon iconEstudiantesOriginal = new ImageIcon(urlMostrarEstudiantes);

        Image imagenEstudiantesEscalada = iconEstudiantesOriginal.getImage().getScaledInstance(250, 90, Image.SCALE_SMOOTH);

        ImageIcon btn = new ImageIcon(imagenEstudiantesEscalada);

        JButton btnMostrarEstudiantes = new JButton(btn);

        btnMostrarEstudiantes.setBounds(275, 460, 250, 90);

        btnMostrarEstudiantes.setBorderPainted(false);
        btnMostrarEstudiantes.setContentAreaFilled(false);
        btnMostrarEstudiantes.setFocusPainted(false);

        btnMostrarEstudiantes.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButtonSound(btnMostrarEstudiantes, "/sounds/SonidoBonton.wav");

        btnMostrarEstudiantes.addActionListener(e -> {

            URL urlImg = getClass().getClassLoader().getResource("main/resources/images/Estudiantes.png");

            ImageIcon icon = new ImageIcon(urlImg);

            Image imgEscalada2 = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);

            estudiantesLabel.setIcon(new ImageIcon(imgEscalada2));

            estudiantesPanel.setVisible(true);

            panel.repaint();
        });

        panel.add(btnMostrarEstudiantes);

        // HACER VISIBLE LA VENTANA
        setVisible(true);
    }

    /**
     * Agrega un efecto de sonido a un botón.
     * <p>
     * Cada vez que el usuario presione el botón,
     * se reproducirá el sonido indicado.
     *
     * @param button    botón al que se le agregará el sonido.
     * @param soundPath ruta del archivo de sonido.
     */
    private void addButtonSound(JButton button, String soundPath) {

        button.addActionListener(e -> {

            if (sound != null) {

                sound.playSound(soundPath);
            }
        });
    }
}

/**
 * Clase BackgroundPanel:
 * panel personalizado encargado de mostrar
 * la imagen de fondo de la ventana principal.
 */
class BackgroundPanel extends JPanel {

    /**
     * Imagen de fondo del panel.
     */
    private Image background;

    /**
     * Constructor de BackgroundPanel.
     * <p>
     * Carga la imagen de fondo desde
     * los recursos del proyecto.
     */
    public BackgroundPanel() {

        URL url = getClass().getClassLoader().getResource("main/resources/images/PanelBienvenida.png");

        if (url != null) {

            background = new ImageIcon(url).getImage();
        }
    }

    /**
     * Dibuja la imagen de fondo del panel.
     *
     * @param g objeto Graphics utilizado
     *          para renderizar la imagen.
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (background != null) {

            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

/**
 * Clase GlowingLabel:
 * representa una etiqueta animada
 * con efecto brillante.
 * <p>
 * El texto cambia progresivamente
 * su intensidad luminosa para crear
 * una animación visual.
 */
class GlowingLabel extends JLabel {

    /**
     * Nivel de transparencia del brillo.
     */
    private float alpha = 0f;

    /**
     * Indica si el brillo está aumentando
     * o disminuyendo.
     */
    private boolean increasing = true;

    /**
     * Constructor de GlowingLabel.
     * <p>
     * Configura la fuente, color y animación
     * del texto brillante.
     *
     * @param text texto que mostrará la etiqueta.
     */
    public GlowingLabel(String text) {

        super(text);

        setFont(new Font("Arial", Font.BOLD, 20));

        setForeground(new Color(248, 159, 72));

        setHorizontalAlignment(SwingConstants.CENTER);

        Timer timer = new Timer(50, e -> {

            if (increasing) {

                alpha += 0.05f;

                if (alpha >= 1f) {
                    increasing = false;
                }

            } else {

                alpha -= 0.05f;

                if (alpha <= 0.2f) {
                    increasing = true;
                }
            }

            repaint();
        });

        timer.start();
    }

    /**
     * Dibuja el texto con efecto brillante.
     *
     * @param g objeto Graphics utilizado
     *          para renderizar el texto.
     */
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(getFont());

        FontMetrics fm = g2d.getFontMetrics();

        int x = (getWidth() - fm.stringWidth(getText())) / 2;

        int y = (getHeight() + fm.getAscent()) / 2 - 2;

        g2d.setColor(new Color(255, 140, 0, (int) (alpha * 120)));

        for (int i = -3; i <= 3; i++) {

            for (int j = -3; j <= 3; j++) {

                g2d.drawString(getText(), x + i, y + j);
            }
        }

        g2d.setColor(new Color(
                (int) (100 + alpha * 155),
                (int) (180 + alpha * 75),
                255
        ));

        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}