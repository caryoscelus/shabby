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

package shabby.person;

import shabby.*;
import shabby.objects.*;

import chlorophytum.story.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.math.*;

import java.lang.Math;
import java.util.Map;
import java.util.HashMap;

/**
 * Player's person.
 * Needs some cleaning..
 */
public class Person extends ShabbyObject {
    // gfx state
    public Person () {
        viewData = new PersonViewData(this);
        view = MapObjectViewFactory.instance().personView;
    }
    
    /**
     * update.
     * Needs cleaning
     * @param dt float time delta in seconds
     */
    @Override
    public void update (float dt) {
        // state will be fixed to Run after super.update() call if needed
        state = State.Stand;
        super.update(dt);
    }
    
    @Override
    public void moved () {
        state = State.Run;
        
        TiledMapTile tile = getTile("quests");
        if (tile != null) {
            if (Boolean.parseBoolean(tile.getProperties().get("auto", "", String.class))) {
                processStory(tile);
            }
        }
    }
    
    /**
     * React on 'click' on centre
     */
    public void clicked () {
        clicked(0, 0);
    }
    
    /**
     * React on 'click' offseted to centre
     * @param dx float x offset
     * @param dy float y offset
     */
    public void clicked (float dx, float dy) {
        TiledMapTile tile = getTile("quests", dx, dy);
        if (tile != null) {
            if (Boolean.parseBoolean(tile.getProperties().get("clickable", "", String.class))) {
                processStory(tile);
            }
        }
    }
    
    /**
     * Process story event assigned to tile.
     * Note that it probably would be deprecated eventually if using
     * proper tiled objects will be implemented.
     * @param tile TiledMapTile containing properties with info for triggering story
     */
    protected void processStory (TiledMapTile tile) {
        String tname = tile.getProperties().get("name", String.class);
        if (tname != null) {
            story(tname);
        } else {
            Gdx.app.log("story", "no story name here; ids not supported anymore");
        }
    }
    
    /**
     * Trigger story; this is just helper, maybe remove or make private?
     * @param tname story name
     */
    public void story (String tname) {
        Story.instance().trigger(tname);
    }
}
