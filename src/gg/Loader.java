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
 * 
 *  Additional permission under GNU GPL version 3 section 7:
 *  If you modify this Program, or any covered work, by linking or combining
 *  it with Clojure (or a modified version of that library), containing parts
 *  covered by the terms of EPL 1.0, the licensors of this Program grant you
 *  additional permission to convey the resulting work. {Corresponding Source
 *  for a non-source form of such a combination shall include the source code
 *  for the parts of Clojure used as well as that of the covered work.}
 */

package gg;

import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class Loader {
    private static Loader _instance;
    
    HashMap<String, Music> tracks = new HashMap();
    HashMap<String, TiledMap> maps = new HashMap();
    
    public static Loader instance () {
        if (_instance == null) {
            _instance = new Loader();
        }
        return _instance;
    }
    
    /**
     * load sound track if necessary and return it
     */
    public Music loadTrack (String fname) {
        Music music = tracks.get(fname);
        if (music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal(fname));
            tracks.put(fname, music);
        }
        return music;
    }
    
    
    /**
     * load map if necessary and return it
     */
    public TiledMap loadMap (String fname) {
        TiledMap map = maps.get(fname);
        if (map == null) {
            map = new TmxMapLoader().load(fname);
            maps.put(fname, map);
        }
        return map;
    }
}
