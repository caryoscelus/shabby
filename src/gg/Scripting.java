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

import clojure.lang.RT;
import clojure.lang.Var;
import clojure.lang.Compiler;

import java.io.IOException;

/**
 * Script manager.
 * Now static class.. Maybe make singleton? 
 */
public class Scripting {
    /**
     * init
     */
    public static void init () {
        // if this removed, crash occurs..; could be replaced by access to
        // any static member of RT though
        RT.init();
        
        // libs
        run("data/scripts/base.clj");
    }
    
    /**
     * Run script with fname
     * don't run this before init()
     */
    public static void run (String fname) {
        try {
            Compiler.loadFile(fname);
        } catch (IOException e) {
            Gdx.app.error("clojure", "can't find file", e);
        }
    }
    
    /**
     * Call clojure function "var" from namespace "ns"
     * No argument passing available atm
     */
    public static Object call (String ns, String var) {
        return RT.var(ns, var).invoke();
    }
    
    public static Object call (Object var) {
        return ((clojure.lang.AFn)var).invoke();
    }
}
