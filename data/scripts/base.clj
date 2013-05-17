(ns story)

(import '(gg Story StoryEvent StoryDialog Loader)
        '(java.util HashMap))

(defn make-dialog [text & args]
      (cond
          (empty? args) (StoryDialog. text)
          (map? (first args)) (StoryDialog. text (HashMap. (first args)))
          :else (StoryDialog. text (first args))))

(defn show-text [text & args]
      (let [dialogue (apply make-dialog (cons text args))]
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

(defn text-event [text & args]
      (event (apply show-text (cons text args))))


;; shortcuts
(def bind add-event)
(def txt text-event)
(def ev get-event)



;; music
(defn load-music [fname ename]
      (.loadTrack (Loader/instance) ename fname))

(defn play-loop [ename]
      (let [track (.getTrack (Loader/instance) ename)]
           (.setLooping track true)
           (.play track)))
