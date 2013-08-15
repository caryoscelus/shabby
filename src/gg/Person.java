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
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

import java.lang.Math;
import java.util.Map;
import java.util.HashMap;

/**
 * Player's person.
 * TODO: separate rendering, input and everything else
 */
public class Person extends MapObject {
    // gfx
    Texture texture;
    Animation defaultSprite;
    Animation[] spriteRun = new Animation[8];
    Animation[] spriteStand = new Animation[8];
    
    // gfx state
    int direction = 0;
    enum State {
        Stand, Run
    }
    State state = State.Stand;
    float tc;
    
    public Person () {
        texture = new Texture("data/maps/char-1.png");
        TextureRegion[][] regions = TextureRegion.split(texture, 32, 32);
        
        defaultSprite = new Animation(0, regions[0][0]);
        
        for (int i = 0; i < 8; ++i) {
            spriteStand[i] = new Animation(0, regions[i+1][0]);
            
            TextureRegion[] tmp = new TextureRegion[8];
            System.arraycopy(regions[i+1], 1, tmp, 0, 8);
            spriteRun[i] = new Animation(0.11f, tmp);
            spriteRun[i].setPlayMode(Animation.LOOP);
        }
    }
    
    public void render (SpriteBatch batch) {
        final Animation[] spriteT;
        if (state == State.Run) {
            spriteT = spriteRun;
        } else {
            spriteT = spriteStand;
        }
        // fix this when all animations are done
        if (direction % 4 == 3) {
            direction = (direction+1)%8;
        }
        direction = direction/2*2;
        final Animation sprite = spriteT[direction];
        
        batch.begin();
        batch.draw(sprite.getKeyFrame(tc), position.x, position.y, 2, 2);
        batch.end();
    }
    
    public void update (float dt) {
        // fur animations
        tc += dt;
        
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
            
            position.x += dx;
            position.y += dy;
        } else {
            state = State.Stand;
        }
    }
    
    public void clicked () {
        clicked(0, 0);
    }
    
    public void clicked (float dx, float dy) {
        TiledMapTile tile = getTile("quests", dx, dy);
        if (tile != null) {
            String tname = tile.getProperties().get("name", String.class);
            if (tname != null) {
                story(tname);
            } else {
                Gdx.app.log("story", "no story name here; ids not supported anymore");
            }
        }
    }
    
    public void story (String tname) {
        Story.instance().trigger(tname);
    }
}
