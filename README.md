shabby
======

Simple game using Chlorophytum engine (https://github.com/caryoscelus/chlorophytum)

Originally written for learning; maybe will grow into something bigger later.

Current status: development frozen; early development, but runnable

It is possible that development of game itself will be resumed, but most likely
using enother tech.

Dependencies
------------
First of all, you need Chlorophytum. Compile it and put jar into libs/ directory.
(or you can just put it alongside shabby source and run "ant runup" instead; see
below). You'll also need to put libgdx and clojure jars into libs/ directory.

Building & running
------------------
From root directory run:
* "ant runfast" - will build & run
* "ant justrun" - will only run (need to be build previously)
* "ant jar" - will build a jar
* "ant bundle" - build bundle jar containing content of dependency jars
* "ant runup" - if you have placed chlorophytum source alongside shabby, this will
    build chlorophytum jar first and then run runfast; note that you should either
    copy jar (or edit build.xml to do it for you) or (preferably) make a symlink
    to it.

License
-------
Java code - GNU GPL v3 or later [with clojure linking permission]

    Copyright (C) 2013 caryoscelus
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
     
    Additional permission under GNU GPL version 3 section 7:
    If you modify this Program, or any covered work, by linking or combining
    it with Clojure (or a modified version of that library), containing parts
    covered by the terms of EPL 1.0, the licensors of this Program grant you
    additional permission to convey the resulting work. {Corresponding Source
    for a non-source form of such a combination shall include the source code
    for the parts of Clojure used as well as that of the covered work.}

Content - CC-BY-SA
