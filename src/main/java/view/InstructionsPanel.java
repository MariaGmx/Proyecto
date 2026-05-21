package view;

import controller.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase encargada de mostrar la ventana de instrucciones
 * del videojuego Burning Kitchen.
 *
 * <p>
 * Esta ventana contiene:
 * </p>
 *
 * <ul>
 *     <li>Las reglas principales del juego.</li>
 *     <li>El sistema de puntajes.</li>
 *     <li>Las condiciones de victoria y derrota.</li>
 *     <li>Un botón para regresar al menú principal.</li>
 * </ul>
 * <p>
 * Además, utiliza un fondo personalizado y componentes
 * gráficos diseñados con Java Swing.
 *
 * @author Juan David Marin Hernandez
 * @version 1.0
 * @since 2026
 */
public class InstructionsPanel extends JFrame {

    /**
     * Objeto encargado de reproducir sonidos del menú.
     */
    private SoundManager sound;

    /**
     * Constructor principal de la ventana de instrucciones.
     */
    public InstructionsPanel() {

        sound = new SoundManager();

        setTitle("Instructions");

        setSize(800, 1000);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BackgroundPanel panel = new BackgroundPanel();

        panel.setLayout(null);

        setContentPane(panel);

        /**
         * Titulo principal de la ventana
         * */

        JLabel title = new JLabel("GAME INSTRUCTIONS");

        title.setBounds(180, 40, 500, 50);

        title.setFont(new Font("Arial", Font.BOLD, 32));

        Color miColorRGB = new Color(168, 92, 0);

        title.setForeground(miColorRGB);

        panel.add(title);

        /**
         * Area de texto que contiene las instrucciones del videojuego
         * */

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

        /**
         * Boton encargado de cerrar la ventana de instrucciones
         * Ruta de la imagen del boton
         * */

        URL urlClose = getClass().getClassLoader().getResource(
                "images/botonAtras.png"
        );

        /**
         * Carga de la imagen iriginal
         * */

        ImageIcon iconCloseOriginal = new ImageIcon(urlClose);

        /**
         * Tamaño personalizado del boton
         * */
        int anchoBoton = 180;

        int altoBoton = 90;

        /**
         * Escalado de la imagen
         * */
        Image imagenCloseEscalada =
                iconCloseOriginal.getImage().getScaledInstance(
                        anchoBoton,
                        altoBoton,
                        Image.SCALE_SMOOTH
                );

        /**
         * Creacion del nuevo icono escalado
         * */

        ImageIcon closeIcon = new ImageIcon(imagenCloseEscalada);

        /**
         * Creacion del boton
         * */

        JButton btnClose = new JButton(closeIcon);

        /**
         * Posicion del boton en la ventana
         * */

        int x = 550;

        int y = 700;

        btnClose.setBounds(x, y, anchoBoton, altoBoton);

        /**
         * Configuracion visual del boton
         * */

        btnClose.setBorderPainted(false);

        btnClose.setContentAreaFilled(false);

        btnClose.setFocusPainted(false);

        btnClose.setOpaque(false);

        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButtonSound(
                btnClose,
                "/sounds/SonidoBonton.wav"
        );

        /***
         *Evento encargado de cerrar la ventana
         */

        btnClose.addActionListener(e -> dispose());

        /**
         * Adregar el boton al panel
         * */

        panel.add(btnClose);
    }

    /**
     * Método encargado de agregar sonido a un botón.
     *
     * @param button botón al que se le agregará sonido
     * @param soundPath ruta del sonido
     */
    private void addButtonSound(JButton button, String soundPath) {

        button.addActionListener(e -> {

            sound.playSound(soundPath);

        });

    }

    /**
     * Panel personalizado encargado de dibujar
     * el fondo de la ventana de instrucciones.
     */
    class BackgroundPanel extends JPanel {

        /**
         * Imagen utilizada como fondo.
         */
        private Image backgroundImage;

        /**
         * Constructor del panel de fondo.
         */
        public BackgroundPanel() {

            backgroundImage = new ImageIcon(
                    getClass().getClassLoader().getResource(
                            "images/background.png"
                    )
            ).getImage();
        }

        /**
         * Método encargado de dibujar el fondo
         * de la ventana.
         *
         * @param g objeto gráfico utilizado para renderizar
         */
        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            g.drawImage(
                    backgroundImage,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }
    }
}