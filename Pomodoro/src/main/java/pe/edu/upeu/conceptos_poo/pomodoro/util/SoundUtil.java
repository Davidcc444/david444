package pe.edu.upeu.conceptos_poo.pomodoro.util;

import javafx.scene.media.AudioClip;

public class SoundUtil {
    private static final String PREFIX = "/sounds/";

    /** Reproduce un MP3 dentro de resources/sounds */
    public static void play(String fileName) {
        String url = SoundUtil.class
                .getResource(PREFIX + fileName)
                .toExternalForm();
        AudioClip clip = new AudioClip(url);
        clip.play();
    }
}

