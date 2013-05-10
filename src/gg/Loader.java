/*
 *  Copyright (C) 2013 caryoscelus
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
