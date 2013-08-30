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

package shabby.objects;

import chlorophytum.mapobject.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.*;

/**
 * Contains moving processing, States and direction..
 */
public class ShabbyObject extends MapObject {
    protected static final float DEFAULT_SPEED = 4;
    protected static final float ROAD_BOOST = 1.5f;
    
    public int direction = 0;
    public enum State {
        Stand, Run
    }
    public State state = State.Stand;
    
    @Override
    public void update(float dt) {
        super.update(dt);
        
        if (move.x != 0 || move.y != 0) {
            int angle = ((int) move.angle()/45) * 45;
            float dx = (float) Math.cos(angle*Math.PI/180) * dt * DEFAULT_SPEED;
            float dy = (float) Math.sin(angle*Math.PI/180) * dt * DEFAULT_SPEED;
            
            direction = angle / 45;
            state = State.Run;
            
            // check ground
            TiledMapTile tile;
            tile = getTile("gameplay");
            if (tile != null) {
                final String tid = tile.getProperties().get("status", String.class);
                if (tid != null) {
                    switch (tid) {
                        case "road":
                            dx *= ROAD_BOOST;
                            dy *= ROAD_BOOST;
                            break;
                    }
                }
            } else {
                Gdx.app.error("Person.update", "not on map or no feature");
            }
            
            tile = getTile("gameplay", dx, dy);
            if (tile != null) {
                final String tid = tile.getProperties().get("status", String.class);
                if (tid != null) {
                    switch (tid) {
                        case "unpassable":
                        case "oasis":
                        case "mapborder":
                            dx = 0;
                            dy = 0;
                            break;
                    }
                }
            }
            
            if (dx != 0 || dy != 0) {
                move(dx, dy);
            }
        }
    }
}
