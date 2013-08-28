;;  Copyright (C) 2013 caryoscelus
;;  
;;  This program is free software: you can redistribute it and/or modify
;;  it under the terms of the GNU General Public License as published by
;;  the Free Software Foundation, either version 3 of the License, or
;;  (at your option) any later version.
;;  
;;  This program is distributed in the hope that it will be useful,
;;  but WITHOUT ANY WARRANTY; without even the implied warranty of
;;  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;;  GNU General Public License for more details.
;;  
;;  You should have received a copy of the GNU General Public License
;;  along with this program.  If not, see <http://www.gnu.org/licenses/>.
;;  
;;  Additional permission under GNU GPL version 3 section 7:
;;  If you modify this Program, or any covered work, by linking or combining
;;  it with Clojure (or a modified version of that library), containing parts
;;  covered by the terms of EPL 1.0, the licensors of this Program grant you
;;  additional permission to convey the resulting work. {Corresponding Source
;;  for a non-source form of such a combination shall include the source code
;;  for the parts of Clojure used as well as that of the covered work.}


(ns story)

(import '(chlorophytum.story Story StoryEvent StoryDialog StoryDialogLine)
        '(chlorophytum Loader World Streamer)
        '(java.util Vector)
        '(com.badlogic.gdx Gdx))

(declare event)

(defn log [text]
      (.log (Gdx/app) "log" (str text)))

(defn make-dialog [text save & args]
      (if (= (class text) StoryDialog)
          text
          (let [opts (first args)]
               (cond
                   (nil? opts) (StoryDialog. text save)
                   (list? opts) (StoryDialog. text (Vector. opts) save)
                   (fn? opts) (StoryDialog. text (event opts) save)
                   :else (StoryDialog. text opts save)))))

(defn event [action]
      (if (fn? action)
          (proxy [StoryEvent] []
                 (trigger []
                          (let [a (action)]
                               (if (= a nil)
                                   false
                                   a))))
          action))

(defn add-event [ename new-event]
      (let [ne (cond
                   (fn? new-event) (event new-event)
                   :else new-event)]
           (.addEvent (Story/instance) ename ne)))

(defn get-event [ename]
      (.getEvent (Story/instance) ename))

(defn object-move [object new-map & xy]
      (cond
          (empty? xy) (.moveTo object new-map)
          :else (.moveTo object new-map (first xy) (second xy))))

(defn change-map [map-name & xy]
      (let [self (.getObject (.story (World/instance)) "self")]
           (apply object-move (concat (list self map-name) xy))))

(defn dialog-line [txt act & opts]
      (let [visible (if (empty? opts)
                        true
                        (first opts))]
           (StoryDialogLine. txt (event act) visible)))

;; shortcuts
(def bind add-event)
(defn t [text & args] (apply make-dialog (concat (list text false) args)))
(defn tr [text & args] (apply make-dialog (concat (list text true) args)))
(def ln dialog-line)
(defn lnh [text ev] (ln text ev false))
(def ev get-event)



;; music
(defn play-loop [fname]
      (.load (Streamer/instance) fname)
      (.play (Streamer/instance)))


;; input
(defn add-key-handler [tkey action]
      (.registerKeyAction (.keyboardHandler (.screen (Story/instance))) tkey action))
