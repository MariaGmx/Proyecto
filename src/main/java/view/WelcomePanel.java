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
}