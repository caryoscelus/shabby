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

import com.badlogic.gdx.Gdx;

import java.util.Map;
import java.util.HashMap;

public class Story {
    Map<String, StoryObject> objects = new HashMap();
    Map<String, StoryEvent> events = new HashMap();
    
    StoryDialog saved = null;
    
    public StoryScreen screen;
    
    
    // Maybe use World.instance().story instead?
    public static Story instance () {
        return World.instance().story;
    }
    
    
    public void addEvent (String name, StoryEvent event) {
        events.put(name, event);
    }
    
    public StoryEvent getEvent (String name) {
        return events.get(name);
    }
    
    
    public void addObject (String name, StoryObject object) {
        objects.put(name, object);
    }
    
    public StoryObject getObject (String name) {
        return objects.get(name);
    }
    
    
    /**
     * Trigger event by name
     */
    public void trigger (String name) {
        trigger(getEvent(name));
    }
    
    /**
     * Trigger specific event
     */
    public void trigger (StoryEvent event) {
        if (event != null) {
            event.trigger();
        }
    }
    
    
    /**
     * Show dialogue through screen
     */
    public void ui (StoryDialog dialogue) {
        if (screen != null) {
            screen.showStory(dialogue);
        }
    }
    
    /**
     * Check whether we should exit or just return to some previous dialogue
     */
    public boolean checkExit () {
        if (saved != null) {
            ui(saved);
            return false;
        }
        return true;
    }
}
