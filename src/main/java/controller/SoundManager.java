package controller;

import javax.sound.sampled.*;
import java.net.URL;

public class SoundManager {

    private Clip clip;

    /**
     * Método encargado de reproducir sonidos
     *
     * @param path ruta del sonido
     */
    public void playSound(String path) {

        try {

            URL url = getClass().getResource(path);

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();

            clip.open(audio);

            clip.start();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}