package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase principal encargada de mostrar el menú de bienvenida
 * del videojuego Burning Kitchen.
 *
 * <p>
 * Esta ventana permite:
 * </p>
 *
 * <ul>
 *     <li>Iniciar el juego.</li>
 *     <li>Visualizar las instrucciones.</li>
 *     <li>Mostrar los integrantes del proyecto.</li>
 * </ul>
 * <p>
 * Además, contiene efectos visuales personalizados
 * y un fondo gráfico dinámico.
 *
 * @author Juan David Marin Hernandez
 * @version 1.0
 * @since 2026
 */
public class WelcomePanel extends JFrame {

    /**
     * Constructor principal de la ventana de bienvenida.
     */
    public WelcomePanel() {

        setTitle("Burning Kitchen");

        setSize(800, 1000);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel();

        setContentPane(panel);

        panel.setLayout(null);

        /**
         * Logo institucional de la UAM
         * */

        URL urlLogo = getClass().getClassLoader().getResource(
                "images/logoU.png"
        );

        ImageIcon logoIcon = new ImageIcon(urlLogo);

        Image imgEscalada = logoIcon.getImage().getScaledInstance(
                120,
                120,
                Image.SCALE_SMOOTH
        );

        ImageIcon logoFinal = new ImageIcon(imgEscalada);

        JLabel logo = new JLabel(logoFinal);

        logo.setBounds(590, 610, 210, 300);

        panel.add(logo);


        /**
         * Panel utilizado para mostrar los integrantes del proyecto
         * */

        JPanel estudiantesPanel = new JPanel(null);

        estudiantesPanel.setBounds(150, 150, 500, 500);

        estudiantesPanel.setOpaque(false);

        estudiantesPanel.setVisible(false);

        /**
         * Etiquetas que contiene la imagen de los estudiantes del proyecto
         * */

        JLabel estudiantesLabel = new JLabel();

        estudiantesLabel.setBounds(0, 0, 500, 500);

        estudiantesPanel.add(estudiantesLabel);

        panel.add(estudiantesPanel);

        /**
         * Botón encargado de cerrar el panel de estudiantes
         * */

        URL urlCerrar = getClass().getClassLoader().getResource(
                "images/cerrar.png"
        );

        ImageIcon iconCerrarOriginal = new ImageIcon(urlCerrar);

        Image imagenCerrarEscalada =
                iconCerrarOriginal.getImage().getScaledInstance(
                        60,
                        60,
                        Image.SCALE_SMOOTH
                );

        ImageIcon iconoCerrar = new ImageIcon(imagenCerrarEscalada);

        JButton btnCerrar = new JButton(iconoCerrar);

        btnCerrar.setBounds(420, 0, 60, 60);

        btnCerrar.setBorderPainted(false);

        btnCerrar.setContentAreaFilled(false);

        btnCerrar.setFocusPainted(false);

        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        /**
         * Evento encargado de ocultar el panel
         * de estudiantes.
         */
        btnCerrar.addActionListener(e -> {

            estudiantesPanel.setVisible(false);

            panel.repaint();

        });

        estudiantesPanel.add(btnCerrar);

        /**
         * Se asegura de que el botón de cerrar permanesca encima de la imagen
         * */

        estudiantesPanel.setComponentZOrder(btnCerrar, 0);

        estudiantesPanel.setComponentZOrder(estudiantesLabel, 1);

        /**
         * Título principal de la materia
         * */

        GlowingLabel glowLabel = new GlowingLabel(
                "PROGRAMACIÓN ORIENTADA A OBJETOS"
        );

        glowLabel.setBounds(170, 550, 460, 60);

        panel.add(glowLabel);

        /**
         * Botón encargado de iniciar el videojuego
         * */

        URL urlBtn = getClass().getClassLoader().getResource(
                "images/botonIniciar.png"
        );

        ImageIcon iconoOriginal = new ImageIcon(urlBtn);

        Image imagenEscaladaBtn =
                iconoOriginal.getImage().getScaledInstance(
                        270,
                        180,
                        Image.SCALE_SMOOTH
                );

        ImageIcon icono = new ImageIcon(imagenEscaladaBtn);

        JButton btnIniciar = new JButton(icono);

        btnIniciar.setBounds(270, 220, 250, 90);

        btnIniciar.setBorderPainted(false);

        btnIniciar.setContentAreaFilled(false);

        btnIniciar.setFocusPainted(false);

        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        /**
         * Evento encargado de iniciar el videojuego.
         */
        btnIniciar.addActionListener(e -> {

            GameController controller = new GameController();

            controller.startGame();

            dispose();

        });

        panel.add(btnIniciar);

        /**
         * Botón para mostrar las instrucciones del juego
         * */

        URL urlInstructions = getClass().getClassLoader().getResource(
                "images/botonInstrucciones.png"
        );

        ImageIcon iconOriginalInstructions =
                new ImageIcon(urlInstructions);

        Image imagenEscaladaInstructions =
                iconOriginalInstructions.getImage().getScaledInstance(
                        260,
                        250,
                        Image.SCALE_SMOOTH
                );

        ImageIcon iconFinalInstructions =
                new ImageIcon(imagenEscaladaInstructions);

        JButton btnInstructions =
                new JButton(iconFinalInstructions);

        btnInstructions.setBounds(275, 340, 250, 90);

        btnInstructions.setBorderPainted(false);

        btnInstructions.setContentAreaFilled(false);

        btnInstructions.setFocusPainted(false);

        btnInstructions.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );


        /**
         * Eventos que abre la ventana de instrucciones
         * */

        btnInstructions.addActionListener(e -> {

            InstructionsPanel instructionsPanel =
                    new InstructionsPanel();

            instructionsPanel.setVisible(true);

        });

        panel.add(btnInstructions);

        /**
         * Botón para mostrar los ingredientes del proyecto
         * */

        URL urlMostrarEstudiantes =
                getClass().getClassLoader().getResource(
                        "images/mostrarestu.png"
                );

        ImageIcon iconEstudiantesOriginal =
                new ImageIcon(urlMostrarEstudiantes);

        Image imagenEstudiantesEscalada =
                iconEstudiantesOriginal.getImage().getScaledInstance(
                        250,
                        90,
                        Image.SCALE_SMOOTH
                );

        ImageIcon btn =
                new ImageIcon(imagenEstudiantesEscalada);

        JButton btnMostrarEstudiantes =
                new JButton(btn);

        btnMostrarEstudiantes.setBounds(275, 460, 250, 90);

        btnMostrarEstudiantes.setBorderPainted(false);

        btnMostrarEstudiantes.setContentAreaFilled(false);

        btnMostrarEstudiantes.setFocusPainted(false);

        btnMostrarEstudiantes.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        /**
         * Evento encargado de mostrar la imagen
         * de los integrantes del proyecto.
         */
        btnMostrarEstudiantes.addActionListener(e -> {

            URL urlImg = getClass().getClassLoader().getResource(
                    "images/Estudiantes.png"
            );

            ImageIcon icon = new ImageIcon(urlImg);

            Image imgEscalada2 = icon.getImage().getScaledInstance(
                    500,
                    500,
                    Image.SCALE_SMOOTH
            );

            estudiantesLabel.setIcon(
                    new ImageIcon(imgEscalada2)
            );

            estudiantesPanel.setVisible(true);

            panel.repaint();

        });

        panel.add(btnMostrarEstudiantes);

        /**
         * Hacer visible la ventana principal
         * */

        setVisible(true);
    }

    /**
     * Panel personalizado encargado de dibujar
     * el fondo de la ventana principal.
     */
    class BackgroundPanel extends JPanel {

        /**
         * Imagen utilizada como fondo.
         */
        private Image background;

        /**
         * Constructor del panel de fondo.
         */
        public BackgroundPanel() {

            URL url = getClass().getClassLoader().getResource(
                    "images/PanelBienvenida.png"
            );

            background = new ImageIcon(url).getImage();
        }

        /**
         * Método encargado de dibujar el fondo.
         *
         * @param g objeto gráfico utilizado para renderizar
         */
        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.drawImage(
                    background,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }
    }

    /**
     * Etiqueta personalizada con efecto
     * de brillo animado.
     */
    class GlowingLabel extends JLabel {

        /**
         * Nivel de transparencia del brillo.
         */
        private float alpha = 0f;

        /**
         * Controla la dirección del brillo.
         */
        private boolean increasing = true;

        /**
         * Constructor de la etiqueta animada.
         *
         * @param text texto mostrado en pantalla
         */
        public GlowingLabel(String text) {

            super(text);

            setFont(new Font("Arial", Font.BOLD, 20));

            setForeground(new Color(248, 159, 72));

            setHorizontalAlignment(SwingConstants.CENTER);

            /**
             * Tempodizador que controla la animación del efecto glow
             * */

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
         * Método encargado de dibujar el texto
         * con efecto glow.
         *
         * @param g objeto gráfico utilizado para renderizar
         */
        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2d = (Graphics2D) g.create();

            /**
             * Configuracion del renderizado para mejorar la calidad visual del tecto
             * */

            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            /**
             * Calculo de la posicion del texto dentro de la etiqueta
             * */

            g2d.setFont(getFont());

            FontMetrics fm = g2d.getFontMetrics();

            int x = (getWidth() - fm.stringWidth(getText())) / 2;

            int y = (getHeight() + fm.getAscent()) / 2 - 2;

            /**
             * Dibuja del efecto de brillo alrededor del texto principal
             * */

            g2d.setColor(
                    new Color(255, 140, 0, (int) (alpha * 120))
            );

            for (int i = -3; i <= 3; i++) {

                for (int j = -3; j <= 3; j++) {

                    g2d.drawString(
                            getText(),
                            x + i,
                            y + j
                    );
                }
            }

            /**
             * Dibujo del texto principal encima del efecto del brillo
             * */

            g2d.setColor(
                    new Color(
                            (int) (100 + alpha * 155),
                            (int) (180 + alpha * 75),
                            255
                    )
            );

            g2d.drawString(getText(), x, y);

            g2d.dispose();
        }
    }
}