(ns story)

(import '(gg Story StoryEvent StoryDialog Loader)
        '(java.util HashMap))

(defn show-text [text]
      (let [dialogue (StoryDialog. text)]
           (fn []
               (.ui (Story/instance) dialogue))))

(defn show-dialogue [text options]
      (let [dialogue (StoryDialog. text (HashMap. options))]
           (fn []
               (.ui (Story/instance) dialogue))))

(defn event [action]
      (proxy [StoryEvent] []
             (trigger []
                      (let [a (action)]
                           (if (= a nil)
                               true
                               false)))))

(defn add-event [id ename new-event]
      (.addEvent (Story/instance) id ename new-event))

(defn get-event [ename]
      (.getEvent (Story/instance) ename))

(defn text-event [text]
      (event (show-text text)))

(defn dialogue-event [text options]
      (event (show-dialogue text options)))


;; shortcuts
(def bind add-event)
(def txt text-event)
(def dia dialogue-event)
(def ev get-event)



;; music
(defn load-music [fname ename]
      (.loadTrack (Loader/instance) ename fname))

(defn play-loop [ename]
      (let [track (.getTrack (Loader/instance) ename)]
           (.setLooping track true)
           (.play track)))
