package view;

import controller.GameController;
import controller.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WelcomePanel extends JFrame {

    /**
     * Objeto encargado de reproducir sonidos del menú.
     */
    private SoundManager sound;


    /**
     * Constructor principal de la ventana de bienvenida.
     */
    public WelcomePanel() {

        sound = new SoundManager();

        setTitle("Burning Kitchen");
        setSize(800, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel();
        setContentPane(panel);

        panel.setLayout(null);

        // LOGO DE LA UAM
        URL urlLogo = getClass().getClassLoader().getResource("images/logoU.png");

        System.out.println(urlLogo);

        ImageIcon logoIcon = new ImageIcon(urlLogo);

        Image imgEscalada = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        ImageIcon logoFinal = new ImageIcon(imgEscalada);

        JLabel logo = new JLabel(logoFinal);
        logo.setBounds(590, 610, 210, 300);
        panel.add(logo);

        // CONTENEDOR EN EL QUE ESTAMOS TRABAJANDO
        JPanel estudiantesPanel = new JPanel(null);
        estudiantesPanel.setBounds(150, 150, 500, 500);
        estudiantesPanel.setOpaque(false);
        estudiantesPanel.setVisible(false);

        // LA IMAGEN QUE OCUPA TODO EL
        JLabel estudiantesLabel = new JLabel();
        estudiantesLabel.setBounds(0, 0, 500, 500);
        estudiantesPanel.add(estudiantesLabel);

        panel.add(estudiantesPanel);

        // BOTON CERRAR DENTRO DEL PANEL
        URL urlCerrar = getClass().getClassLoader().getResource("images/cerrar.png");

        ImageIcon iconoCerrar = new ImageIcon(urlCerrar);
        JButton btnCerrar = new JButton(iconoCerrar);

        btnCerrar.setBounds(400, 1, iconoCerrar.getIconWidth(), iconoCerrar.getIconHeight());
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);

        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButtonSound(
                btnCerrar,
                "/sounds/SonidoBonton.wav"
        );

        /**
         * Evento encargado de ocultar el panel
         * de estudiantes.
         */
        btnCerrar.addActionListener(e -> {
            estudiantesPanel.setVisible(false);
            panel.repaint();
        });

        estudiantesPanel.add(btnCerrar);
        // FORZAMOS AL BOTON CERRAR A QUEDAR ENCIMA DE LA IMAGEN
        estudiantesPanel.setComponentZOrder(btnCerrar, 0);
        estudiantesPanel.setComponentZOrder(estudiantesLabel, 1);

        // BOTON DE MOSTRAR ESTUDIANTES
        URL urlMostrarEstudiantes = getClass().getClassLoader().getResource("images/mostrarestu.png");

        ImageIcon btn = new ImageIcon(urlMostrarEstudiantes);

        JButton btnMostrarEstudiantes = new JButton(btn);
        // NOMBRE DE LA MATERIA
        GlowingLabel glowLabel = new GlowingLabel("PROGRAMACIÓN ORIENTADA A OBJETOS");


        glowLabel.setBounds(310, 610, 400, 100);

        panel.add(glowLabel);
        btnMostrarEstudiantes.setBounds(370, 420, btn.getIconWidth(), btn.getIconHeight());
        btnMostrarEstudiantes.setBorderPainted(false);
        btnMostrarEstudiantes.setContentAreaFilled(false);
        btnMostrarEstudiantes.setFocusPainted(false);

        btnMostrarEstudiantes.addActionListener(e -> {
            URL urlImg = getClass().getClassLoader().getResource("images/Estudiantes.png");

            System.out.println(urlImg);

            ImageIcon icon = new ImageIcon(urlImg);
            Image imgEscalada2 = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);

            estudiantesLabel.setIcon(new ImageIcon(imgEscalada2));
            estudiantesPanel.setVisible(true);
            panel.repaint();
        });

        panel.add(btnMostrarEstudiantes);

        // BOTÓN INICIAR
        URL urlBtn = getClass().getClassLoader().getResource("images/boton1.png");
        panel.add(estudiantesPanel);

        btnCerrar.setBounds(400, 1, iconoCerrar.getIconWidth(), iconoCerrar.getIconHeight());
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);

        btnCerrar.addActionListener(e -> {
            estudiantesPanel.setVisible(false);
            panel.repaint();
        });

        estudiantesPanel.add(btnCerrar);
        // FORZAMOS AL BOTON CERRAR A QUEDAR ENCIMA DE LA IMAGEN
        estudiantesPanel.setComponentZOrder(btnCerrar, 0);
        estudiantesPanel.setComponentZOrder(estudiantesLabel, 1);




        glowLabel.setBounds(310, 610, 400, 100);

        panel.add(glowLabel);
        btnMostrarEstudiantes.setBounds(370, 420, btn.getIconWidth(), btn.getIconHeight());
        btnMostrarEstudiantes.setBorderPainted(false);
        btnMostrarEstudiantes.setContentAreaFilled(false);
        btnMostrarEstudiantes.setFocusPainted(false);

        btnMostrarEstudiantes.addActionListener(e -> {
            URL urlImg = getClass().getClassLoader().getResource("images/Estudiantes.png");

            System.out.println(urlImg);

            ImageIcon icon = new ImageIcon(urlImg);
            Image imgEscalada2 = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);

            estudiantesLabel.setIcon(new ImageIcon(imgEscalada2));
            estudiantesPanel.setVisible(true);
            panel.repaint();
        });

        panel.add(btnMostrarEstudiantes);

        // BOTÓN INICIAR

        ImageIcon icono = new ImageIcon(urlBtn);

        JButton btnIniciar = new JButton(icono);
        btnIniciar.setBounds(300, 250, icono.getIconWidth(), icono.getIconHeight());
        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusPainted(false);

        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButtonSound(
                btnIniciar,
                "/sounds/SonidoSubirNivel.wav"
        );


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

        addButtonSound(
                btnInstructions,
                "/sounds/SonidoBonton.wav"
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



        ImageIcon iconEstudiantesOriginal =
                new ImageIcon(urlMostrarEstudiantes);

        Image imagenEstudiantesEscalada =
                iconEstudiantesOriginal.getImage().getScaledInstance(
                        250,
                        90,
                        Image.SCALE_SMOOTH
                );





        btnMostrarEstudiantes.setBounds(275, 460, 250, 90);

        btnMostrarEstudiantes.setBorderPainted(false);

        btnMostrarEstudiantes.setContentAreaFilled(false);

        btnMostrarEstudiantes.setFocusPainted(false);

        btnMostrarEstudiantes.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        addButtonSound(
                btnMostrarEstudiantes,
                "/sounds/SonidoBonton.wav"
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
     * Método encargado de agregar sonido a un botón.
     *
     * @param button    botón al que se le agregará sonido
     * @param soundPath ruta del sonido
     */
    private void addButtonSound(JButton button, String soundPath) {

        button.addActionListener(e -> {

            sound.playSound(soundPath);

        });

    }

    /**
     * Panel personalizado encargado de dibujar
     * el fondo de la ventana principal.
     */
    class BackgroundPanel extends JPanel {
        private Image background;

        public BackgroundPanel() {
            URL url = getClass().getClassLoader().getResource("images/PanelBienvenida.png");

            background = new ImageIcon(url).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    class GlowingLabel extends JLabel {
        private float alpha = 0f;
        private boolean increasing = true;

        public GlowingLabel(String text) {
            super(text);
            setFont(new Font("Arial", Font.BOLD, 20));
            setForeground(new Color(248, 159, 72
            ));
            setHorizontalAlignment(SwingConstants.CENTER);

            // TIMER QUE CAMBIA EL BRILLO CADA 50MS
            Timer timer = new Timer(50, e -> {
                if (increasing) {
                    alpha += 0.05f;
                    if (alpha >= 1f) increasing = false;
                } else {
                    alpha -= 0.05f;
                    if (alpha <= 0.2f) increasing = true;
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();

            // ORGANIZA EL TEXTO
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // BRILLO DEL TEXTO
            g2d.setFont(getFont());
            FontMetrics fm = g2d.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;

            // Dibuja el  azul alrededor
            g2d.setColor(new Color(255, 140, 0, (int) (alpha * 120)));
            for (int i = -3; i <= 3; i++) {
                for (int j = -3; j <= 3; j++) {
                    g2d.drawString(getText(), x + i, y + j);
                }
            }

            // Dibuja el texto principal encima
            g2d.setColor(new Color((int) (100 + alpha * 155),
                    (int) (180 + alpha * 75),
                    255
            ));
            g2d.drawString(getText(), x, y);

            g2d.dispose();
        }
    }
}