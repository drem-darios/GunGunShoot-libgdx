package com.drem.games.ggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by drem on 3/26/15.
 */
public class Jukebox {
    private static Map<String, Sound> sounds;
    private static Map<String, Music> music;

    static {
        sounds = new HashMap<String, Sound>();
        music = new HashMap<String, Music>();
    }

    public static void loadSound(String path, String name) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
        sounds.put(name, sound);
    }

    public static void loadMusic(String path, String name) {
        Music newMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.put(name, newMusic);
    }

    public static void playSound(String name) {
        Sound s = sounds.get(name);
        if (s == null) {
            // Display an error or just log
        } else {
            s.play();
        }
    }

    public static void playMusic(String name) {
        Music m = music.get(name);
        if (m == null) {
            // Display an error or just log
        } else {
            m.play();
        }
    }

    public static void loopSound(String name) {
        Sound s = sounds.get(name);
        if (s == null) {
            // Display an error or just log
        } else {
            s.loop();
        }
    }

    public static void loopMusic(String name) {
        Music m = music.get(name);
        if (m == null) {
            // Display an error or just log
        } else {
            m.setLooping(true);
        }
    }

    public static void stopSound(String name) {
        Sound s = sounds.get(name);
        if (s == null) {
            // Display an error or just log
        } else {
            s.stop();
        }
    }

    public static void stopMusic(String name) {
        Music m = music.get(name);
        if (m == null) {
            // Display an error or just log
        } else {
            m.stop();
        }
    }

    public static void stopAll() {
        for (Sound s : sounds.values()) {
            s.stop();
        }

        for (Music m : music.values()) {
            m.stop();
        }
    }
}
