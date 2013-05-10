package gg;

import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class Loader {
    private static Loader _instance;
    
    HashMap<String, Music> tracks = new HashMap();
    
    public static Loader instance () {
        if (_instance == null) {
            _instance = new Loader();
        }
        return _instance;
    }
    
    public void loadTrack(String name, String fname) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(fname));
        tracks.put(name, music);
    }
    
    public Music getTrack(String name) {
        return tracks.get(name);
    }
}
