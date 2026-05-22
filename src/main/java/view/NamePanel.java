package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NamePanel extends JFrame {

    public NamePanel(Runnable onNameEntered, String[] nameHolder) {

        setTitle("Burning Kitchen - Jugador");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //aqui definimos el fondo
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(40, 20, 10));
        setContentPane(mainPanel);

        // Titulo
        JLabel titulo = new JLabel("BURNING KITCHEN", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(255, 140, 0));
        titulo.setBounds(0, 30, 500, 40);
        mainPanel.add(titulo);

        // Subtítulo
        JLabel subtitulo = new JLabel("¿Cómo te llamas, chefsito?", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.ITALIC, 16));
        subtitulo.setForeground(new Color(255, 210, 150));
        subtitulo.setBounds(0, 80, 500, 30);
        mainPanel.add(subtitulo);

        //  Campo de texto para el nombre
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(100, 130, 300, 45);
        txtNombre.setFont(new Font("Arial", Font.BOLD, 18));
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
        txtNombre.setBackground(new Color(60, 30, 10));
        txtNombre.setForeground(new Color(255, 200, 100));
        txtNombre.setCaretColor(new Color(255, 140, 0));
        txtNombre.setBorder(BorderFactory.createLineBorder(
                new Color(255, 140, 0), 2));
        mainPanel.add(txtNombre);

        //
        JLabel instruccion = new JLabel(
                "Presiona Enter para jugar el juego mas emocionante del mundo", SwingConstants.CENTER);
        instruccion.setFont(new Font("Arial", Font.PLAIN, 13));
        instruccion.setForeground(new Color(180, 120, 60));
        instruccion.setBounds(0, 185, 500, 25);
        mainPanel.add(instruccion);

        // Al presionar Enter: guarda el nombre y lanza el juego
        txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String nombre = txtNombre.getText().trim();
                    if (!nombre.isEmpty()) {
                        nameHolder[0] = nombre;
                        dispose();
                        onNameEntered.run();
                    } else {
                        // por si esta vacio
                        txtNombre.setBorder(BorderFactory.createLineBorder(
                                Color.RED, 2));
                    }
                }
            }
        });

        setVisible(true);
        txtNombre.requestFocus();//para que el cursor aparezca en el cuadro
    }
}