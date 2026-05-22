package main.java.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Clase SoundManager:
 * se encarga de administrar y reproducir
 * los sonidos y la música del juego.
 * <p>
 * Permite:
 * <ul>
 *     <li>Reproducir efectos de sonido.</li>
 *     <li>Reproducir música de fondo en bucle.</li>
 *     <li>Detener la música de fondo.</li>
 * </ul>
 *
 * @author Maria
 * @version 1.0
 */
public class SoundManager {

    /**
     * Clip utilizado para efectos de sonido.
     */
    private Clip clip;

    /**
     * Clip utilizado para la música de fondo.
     */
    private Clip backgroundClip;

    /**
     * Reproduce un efecto de sonido corto.
     *
     * @param path ruta del archivo de sonido.
     */
    public void playSound(String path) {

        try {

            URL url = getClass().getResource(path);

            // Verifica si el archivo existe
            if (url == null) {

                System.out.println("No se encontró el sonido: " + path);

                return;
            }

            // Carga el audio
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(url);

            // Crea el clip
            clip = AudioSystem.getClip();

            // Abre el audio
            clip.open(audio);

            // Reproduce el sonido
            clip.start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     * Reproduce música de fondo de manera continua.
     * <p>
     * La música se repetirá infinitamente
     * hasta que se llame el método
     * stopBackgroundMusic().
     *
     * @param path ruta del archivo de música.
     */
    public void playBackgroundMusic(String path) {

        try {

            URL url = getClass().getResource(path);

            // Verifica si el archivo existe
            if (url == null) {

                System.out.println("No se encontró la música: " + path);

                return;
            }

            // Carga el audio
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(url);

            // Crea el clip de fondo
            backgroundClip = AudioSystem.getClip();

            // Abre el audio
            backgroundClip.open(audio);

            // Repite infinitamente
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);

            // Inicia la reproducción
            backgroundClip.start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     * Detiene la música de fondo si está sonando.
     */
    public void stopBackgroundMusic() {

        // Verifica que exista y esté reproduciéndose
        if (backgroundClip != null &&
                backgroundClip.isRunning()) {

            backgroundClip.stop();
        }
    }
}