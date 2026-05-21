package main.java.view;

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
        // =========================================================

        URL urlLogo = getClass().getClassLoader()
                .getResource("images/logoU.png");

        ImageIcon logoIcon = new ImageIcon(urlLogo);

        Image imgEscalada = logoIcon.getImage().getScaledInstance(
                120, 120, Image.SCALE_SMOOTH);

        ImageIcon logoFinal = new ImageIcon(imgEscalada);

        JLabel logo = new JLabel(logoFinal);
        logo.setBounds(590, 610, 210, 300);
        panel.add(logo);

        // PANEL ESTUDIANTES
        // =========================================================

        JPanel estudiantesPanel = new JPanel(null);
        estudiantesPanel.setBounds(150, 150, 500, 500);
        estudiantesPanel.setOpaque(false);
        estudiantesPanel.setVisible(false);

        JLabel estudiantesLabel = new JLabel();
        estudiantesLabel.setBounds(0, 0, 500, 500);
        estudiantesPanel.add(estudiantesLabel);

        panel.add(estudiantesPanel);

        // BOTON CERRAR PANEL ESTUDIANTES
        // =========================================================

        URL urlCerrar = getClass().getClassLoader()
                .getResource("images/cerrar.png");

        ImageIcon iconCerrarOriginal = new ImageIcon(urlCerrar);

        Image imagenCerrarEscalada = iconCerrarOriginal.getImage()
                .getScaledInstance(60, 60, Image.SCALE_SMOOTH);

        ImageIcon iconoCerrar = new ImageIcon(imagenCerrarEscalada);

        JButton btnCerrar = new JButton(iconoCerrar);
        btnCerrar.setBounds(420, 0, 60, 60);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCerrar.addActionListener(e -> {
            estudiantesPanel.setVisible(false);
            panel.repaint();
        });

        estudiantesPanel.add(btnCerrar);
        estudiantesPanel.setComponentZOrder(btnCerrar, 0);
        estudiantesPanel.setComponentZOrder(estudiantesLabel, 1);

        //Titulo que brilla de POO

        GlowingLabel glowLabel = new GlowingLabel(
                "PROGRAMACIÓN ORIENTADA A OBJETOS");
        glowLabel.setBounds(170, 620, 460, 60);
        panel.add(glowLabel);

        //BOTON INICIAR

        URL urlBtn = getClass().getClassLoader()
                .getResource("images/boton1.png");

        ImageIcon iconoOriginal = new ImageIcon(urlBtn);

        Image imagenEscaladaBtn = iconoOriginal.getImage()
                .getScaledInstance(250, 90, Image.SCALE_SMOOTH);

        ImageIcon icono = new ImageIcon(imagenEscaladaBtn);

        JButton btnIniciar = new JButton(icono);
        btnIniciar.setBounds(275, 220, 250, 90);
        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusPainted(false);
        btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //AQUI ABRIMOS PRIMERO EL NAMEPANEL ANTES DE EMPEZAWR EL JUEGO
        btnIniciar.addActionListener(e -> {

            dispose(); //CIERRA EL DE WELCOME

            String[] nameHolder = new String[1]; //EL INPUT DEL NOMBRE

            new NamePanel(() -> {

                main.java.controller.GameController controller =
                        new main.java.controller.GameController();
                controller.setPlayerName(nameHolder[0]);
                controller.startGame();
            }, nameHolder);

        });

        panel.add(btnIniciar);

        //PARTE DEL BOTON DE INSTRUCCIONES

        URL urlInstructions = getClass().getClassLoader()
                .getResource("images/botonInstrucciones.png");

        ImageIcon iconOriginalInstructions =
                new ImageIcon(urlInstructions);

        Image imagenEscaladaInstructions =
                iconOriginalInstructions.getImage().getScaledInstance(
                        300, 170, Image.SCALE_SMOOTH);

        ImageIcon iconFinalInstructions =
                new ImageIcon(imagenEscaladaInstructions);

        JButton btnInstructions = new JButton(iconFinalInstructions);
        btnInstructions.setBounds(275, 340, 250, 90);
        btnInstructions.setBorderPainted(false);
        btnInstructions.setContentAreaFilled(false);
        btnInstructions.setFocusPainted(false);
        btnInstructions.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnInstructions.addActionListener(e -> {
            InstructionsPanel instructionsPanel = new InstructionsPanel();
            instructionsPanel.setVisible(true);
        });

        panel.add(btnInstructions);

        //BOTON DE MOSTRAR ESTUDIANTES

        URL urlMostrarEstudiantes = getClass().getClassLoader()
                .getResource("images/mostrarestu.png");

        ImageIcon iconEstudiantesOriginal =
                new ImageIcon(urlMostrarEstudiantes);

        Image imagenEstudiantesEscalada =
                iconEstudiantesOriginal.getImage().getScaledInstance(
                        250, 90, Image.SCALE_SMOOTH);

        ImageIcon btn = new ImageIcon(imagenEstudiantesEscalada);

        JButton btnMostrarEstudiantes = new JButton(btn);
        btnMostrarEstudiantes.setBounds(275, 460, 250, 90);
        btnMostrarEstudiantes.setBorderPainted(false);
        btnMostrarEstudiantes.setContentAreaFilled(false);
        btnMostrarEstudiantes.setFocusPainted(false);
        btnMostrarEstudiantes.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnMostrarEstudiantes.addActionListener(e -> {

            URL urlImg = getClass().getClassLoader()
                    .getResource("images/Estudiantes.png");

            ImageIcon icon = new ImageIcon(urlImg);

            Image imgEscalada2 = icon.getImage().getScaledInstance(
                    500, 500, Image.SCALE_SMOOTH);

            estudiantesLabel.setIcon(new ImageIcon(imgEscalada2));
            estudiantesPanel.setVisible(true);
            panel.repaint();
        });

        panel.add(btnMostrarEstudiantes);

        //HACE QUE SE VEA LA VENTANA

        setVisible(true);
    }
}

class BackgroundPanel extends JPanel {
    private Image background;

    public BackgroundPanel() {
        URL url = getClass().getClassLoader()
                .getResource("images/PanelBienvenida.png");
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
        setForeground(new Color(248, 159, 72));
        setHorizontalAlignment(SwingConstants.CENTER);

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

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 2;

        // naranja
        g2d.setColor(new Color(255, 140, 0, (int) (alpha * 120)));
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                g2d.drawString(getText(), x + i, y + j);
            }
        }

        // Texto naranja
        g2d.setColor(new Color(
                255,
                (int) (100 + alpha * 80),
                0
        ));
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}