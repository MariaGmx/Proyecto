package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class InstructionsPanel extends JFrame {

    public InstructionsPanel() {

        setTitle("Instructions");
        setSize(800, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        setContentPane(panel);

        // ===== TITULO =====

        JLabel title = new JLabel("GAME INSTRUCTIONS");
        title.setBounds(180, 40, 500, 50);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        Color miColorRGB = new Color(168, 92, 0); // Crea un color verde bosque
        title.setForeground(miColorRGB);

        panel.add(title);

        // ===== TEXTO INSTRUCCIONES =====

        JTextArea instructions = new JTextArea();

        instructions.setText(
                "OBJETIVO DEL JUEGO:\n" +
                        "Atrapa la mayor cantidad de alimentos posible\n" +
                        "antes de que el tiempo termine.\n\n" +

                        "TIEMPO LÍMITE:\n" +
                        "La partida dura aproximadamente 2 minutos.\n\n" +

                        "CONDICIÓN DE VICTORIA:\n" +
                        "- Llegar al final del tiempo con al menos\n" +
                        "  una vida restante.\n\n" +

                        "CONDICIÓN DE DERROTA:\n" +
                        "- Si tomas una poción lanzada por el chef malvado,\n" +
                        "  perderás todas tus vidas inmediatamente.\n\n" +

                        "SISTEMA DE PUNTAJES:\n" +
                        "- Comida común: +10 puntos.\n" +
                        "- Comida especial (plato completo): +20 puntos.\n\n" +

                        "ENEMIGO:\n" +
                        "- Un chef malvado lanza constantemente pociones peligrosas.\n" +
                        "- Debes evitarlas a toda costa.\n\n" +

                        "IMPORTANTE:\n" +
                        "- Los puntos obtenidos con la comida únicamente\n" +
                        "  sirven para definir el ranking final del nivel.\n\n" +

                        "¡BUENA SUERTE CHEF!"
        );

        instructions.setBounds(70, 110, 660, 590);

        instructions.setEditable(false);

        instructions.setFont(new Font("Arial", Font.PLAIN, 18));

        instructions.setForeground(Color.WHITE);

        instructions.setBackground(new Color(0, 0, 0, 150));

        instructions.setLineWrap(true);

        instructions.setWrapStyleWord(true);

        panel.add(instructions);


        // ===== BOTON CERRAR =====

        // RUTA DE LA IMAGEN
        URL urlClose = getClass().getClassLoader().getResource(
                "images/botonAtras.png"
        );

        // IMAGEN ORIGINAL
        ImageIcon iconCloseOriginal = new ImageIcon(urlClose);

        // ===== TAMAÑO PERSONALIZADO =====

        int anchoBoton = 180;
        int altoBoton = 90;

        // ESCALAR IMAGEN
        Image imagenCloseEscalada =
                iconCloseOriginal.getImage().getScaledInstance(
                        anchoBoton,
                        altoBoton,
                        Image.SCALE_SMOOTH
                );

        // NUEVO ICONO
        ImageIcon closeIcon = new ImageIcon(imagenCloseEscalada);

        // BOTON
        JButton btnClose = new JButton(closeIcon);

        // ===== POSICIÓN ABAJO DERECHA =====

        int x = 550;
        int y = 700;

        btnClose.setBounds(x, y, anchoBoton, altoBoton);

        // ===== ESTILO =====

        btnClose.setBorderPainted(false);

        btnClose.setContentAreaFilled(false);

        btnClose.setFocusPainted(false);

        btnClose.setOpaque(false);

        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ===== ACCIÓN =====

        btnClose.addActionListener(e -> dispose());

        // AGREGAR AL PANEL
        panel.add(btnClose);
    }

    // ===== PANEL FONDO =====

    class BackgroundPanel extends JPanel {

        private Image backgroundImage;

        public BackgroundPanel() {

            backgroundImage = new ImageIcon(
                    getClass().getClassLoader().getResource("images/background.png")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}