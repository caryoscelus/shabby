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

import java.util.Vector;

/**
 * StoryDialog: text + options.
 * Typically used as event. Actual event is working via Story singleton
 */
public class StoryDialog implements StoryEvent {
    public final String text;
    public final Vector <StoryDialogLine> options;
    
    public final boolean saveThis;
    
    /**
     * Create dialog displaying text with "close" button
     */
    public StoryDialog (String t, boolean save) {
        text = t;
        options = new Vector();
        add("Close", new StoryEvent() {
            public boolean trigger () {
                return false;
            }
        });
        saveThis = save;
    }
    
    /**
     * Create dialog displaying text and "next" button leading to some
     * event
     */
    public StoryDialog (String t, StoryEvent nexte, boolean save) {
        text = t;
        options = new Vector();
        add("Next", nexte);
        saveThis = save;
    }
    
    /**
     * Create dialog with text and options
     * NOTE: if opts is empty and dialog launched, something bad will happen
     */
    public StoryDialog (String t, Vector<StoryDialogLine> opts, boolean save) {
        text = t;
        options = opts;
        saveThis = save;
    }
    
    /**
     * Add dialog option, return line number
     */
    public int add (String t, StoryEvent event) {
        options.add(new StoryDialogLine(t, event));
        return options.size()-1;
    }
    
    /**
     * Add dialog option, return line number
     */
    public int add (StoryDialogLine line) {
        options.add(line);
        return options.size()-1;
    }
    
    /**
     * Enable/disable line
     */
    public void setActive (int lid, boolean v) {
        options.get(lid).visible = v;
    }
    
    
    @Override
    public boolean trigger () {
        Story.instance().ui(this);
        return true;
    }
}
