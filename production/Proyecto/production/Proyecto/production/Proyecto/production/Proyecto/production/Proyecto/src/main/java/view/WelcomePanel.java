package main.java.view;

import main.java.controller.GameController;

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

        // LOGO
        URL urlLogo = getClass().getClassLoader().getResource("main/resources/images/logoU.png");

        System.out.println(urlLogo);

        ImageIcon logoIcon = new ImageIcon(urlLogo);

        Image imgEscalada = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

        ImageIcon logoFinal = new ImageIcon(imgEscalada);

        JLabel logo = new JLabel(logoFinal);
        logo.setBounds(590, 610, 210, 300);
        panel.add(logo);

        // ✅ PASO 1: Panel contenedor
        JPanel estudiantesPanel = new JPanel(null);
        estudiantesPanel.setBounds(150, 150, 500, 500);
        estudiantesPanel.setOpaque(false);
        estudiantesPanel.setVisible(false);

        // ✅ La imagen ocupa todo el panel
        JLabel estudiantesLabel = new JLabel();
        estudiantesLabel.setBounds(0, 0, 500, 500);
        estudiantesPanel.add(estudiantesLabel);

        panel.add(estudiantesPanel);

        // ✅ PASO 2: btnCerrar dentro del panel
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
        // ✅ CLAVE: fuerza al btnCerrar a quedar encima de la imagen
        estudiantesPanel.setComponentZOrder(btnCerrar, 0);
        estudiantesPanel.setComponentZOrder(estudiantesLabel, 1);

        // ✅ PASO 3: Botón MOSTRAR ESTUDIANTES
        URL urlMostrarEstudiantes = getClass().getClassLoader().getResource("main/resources/images/mostrarestu.png");

        ImageIcon btn = new ImageIcon(urlMostrarEstudiantes);

        JButton btnMostrarEstudiantes = new JButton(btn);
        // ✅ Texto animado debajo del botón mostrar estudiantes
        GlowingLabel glowLabel = new GlowingLabel("PROGRAMACIÓN ORIENTADA A OBJETOS");

// btnMostrarEstudiantes está en y=420, le sumamos su alto aprox
        glowLabel.setBounds(290, 510, 400, 100);

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
        btnIniciar.setBounds(300, 250, icono.getIconWidth(), icono.getIconHeight());
        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusPainted(false);

        btnIniciar.addActionListener(e -> {
            GameController controller = new GameController();
            controller.startGame();
            dispose();
        });

        panel.add(btnIniciar);

        setVisible(true);
    }

    class BackgroundPanel extends JPanel {
        private Image background;

        public BackgroundPanel() {
            URL url = getClass().getClassLoader().getResource("main/resources/images/PanelBienvenida.png");

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

            // ✅ El Timer es el "respirador" que cambia el brillo cada 50ms
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

            // ✅ Suaviza el texto
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // ✅ Capa de brillo azul difuminado detrás del texto
            g2d.setFont(getFont());
            FontMetrics fm = g2d.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;

            // Dibuja el halo azul alrededor
            g2d.setColor(new Color(255, 140, 0, (int) (alpha * 120)));
            for (int i = -3; i <= 3; i++) {
                for (int j = -3; j <= 3; j++) {
                    g2d.drawString(getText(), x + i, y + j);
                }
            }

            // Dibuja el texto principal encima
            g2d.setColor(new Color((int) (100 + alpha * 155),  // R
                    (int) (180 + alpha * 75),   // G
                    255                         // B siempre máximo
            ));
            g2d.drawString(getText(), x, y);

            g2d.dispose();
        }
    }
}