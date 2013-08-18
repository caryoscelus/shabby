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

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;

/**
 * Singleton for playback control
 */
public class Streamer {
    public static Streamer instance() {
        return Loader.instance().streamer;
    }
    
    Music music;
    boolean playing = false;
    boolean enabled = true;
    
    /**
     * Load track from file
     */
    public void load (String fname) {
        if (music != null) {
            music.dispose();
        }
        music = Gdx.audio.newMusic(Loader.instance().load(fname));
    }
    
    /**
     * Pause playing current track
     */
    public void pause () {
        if (enabled && music != null) {
            music.pause();
        }
    }
    
    /**
     * Resume/start playing current track
     */
    public void play () {
        if (enabled && music != null) {
            music.play();
        }
    }
    
    /**
     * Stop playing
     */
    public void stop () {
        if (music != null) {
            music.stop();
        }
    }
    
    /**
     * Returns true if playing
     */
    public boolean isPlaying () {
        return music != null && music.isPlaying();
    }
    
    /**
     * Enable streaming.
     * Will resume playing track if it was playing before disable() called
     */
    public void enable () {
        enabled = true;
        if (playing) {
            play();
        }
    }
    
    /**
     * Disable streaming.
     * Sound will be paused until it's enabled again
     */
    public void disable () {
        playing = isPlaying();
        pause();
        enabled = false;
    }
    
    /**
     * Returns true if enabled
     */
    public boolean isEnabled () {
        return enabled;
    }
}
