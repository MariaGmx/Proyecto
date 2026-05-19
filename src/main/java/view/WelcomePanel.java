package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class WelcomePanel extends JFrame {

    public WelcomePanel() {

        setTitle("Burning Kitchen");
        setSize(800, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel();
        setContentPane(panel);

        panel.setLayout(null);

        // LOGO DE LA UAM
        URL urlLogo = getClass().getClassLoader().getResource("main/resources/images/logoU.png");

        URL urlLogo = getClass().getClassLoader()
                .getResource("main/resources/images/logoU.png");

        System.out.println(urlLogo); // verifica que NO sea null

        ImageIcon logoIcon = new ImageIcon(urlLogo);

// 🔥 REDIMENSIONAR IMAGEN
        Image imgEscalada = logoIcon.getImage()
                .getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        ImageIcon logoFinal = new ImageIcon(imgEscalada);

// JLabel con imagen ya ajustada
        JLabel logo = new JLabel(logoFinal);

// posición abajo a la derecha (ajusta si quieres)
        logo.setBounds(590, 610, 210, 300);

        panel.add(logo);
        // BOTÓN IMAGEN
        URL urlBtn = getClass().getClassLoader()
                .getResource("main/resources/images/boton1.png");

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
        URL urlCerrar = getClass().getClassLoader().getResource("main/resources/images/cerrar.png");

        ImageIcon iconoCerrar = new ImageIcon(urlCerrar);
        JButton btnCerrar = new JButton(iconoCerrar);

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

        // BOTON DE MOSTRAR ESTUDIANTES
        URL urlMostrarEstudiantes = getClass().getClassLoader().getResource("main/resources/images/mostrarestu.png");

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
            URL urlImg = getClass().getClassLoader().getResource("main/resources/images/Estudiantes.png");

            System.out.println(urlImg);

            ImageIcon icon = new ImageIcon(urlImg);
            Image imgEscalada2 = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);

            estudiantesLabel.setIcon(new ImageIcon(imgEscalada2));
            estudiantesPanel.setVisible(true);
            panel.repaint();
        });

        panel.add(btnMostrarEstudiantes);

        // BOTÓN INICIAR
        URL urlBtn = getClass().getClassLoader().getResource("main/resources/images/boton1.png");

        ImageIcon icono = new ImageIcon(urlBtn);

        JButton btnIniciar = new JButton(icono);

        btnIniciar.setBounds(300, 250,
                icono.getIconWidth(),
                icono.getIconHeight());

        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusPainted(false);

        // 🔥 INICIAR JUEGO
        btnIniciar.addActionListener(e -> {

            GameController controller = new GameController();
            controller.startGame();

            dispose(); // cierra el menú
        });

        panel.add(btnIniciar);

        setVisible(true);
    }

    class BackgroundPanel extends JPanel {

        private Image background;

        public BackgroundPanel() {
            URL url = getClass().getClassLoader()
                    .getResource("main/resources/images/PanelBienvenida.png");

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

        //MODIFICACION
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