(ns story)

(import '(gg Story StoryEvent StoryDialog Loader World)
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
      (let [ne (cond
                   (fn? new-event) (event new-event)
                   :else new-event)]
           (.addEvent (Story/instance) id ename ne)))

(defn get-event [ename]
      (.getEvent (Story/instance) ename))

(defn text-event [text & args]
      (event (apply show-text (cons text args))))

(defn object-move [object new-map & xy]
      (cond
          (empty? xy) (.moveTo object new-map)
          :else (.moveTo object new-map (first xy) (second xy))))

(defn change-map [map-name & xy]
      (let [self (.getObject (.story (World/instance)) "self")]
           (apply object-move (concat (list self map-name) xy))))


;; shortcuts
(def bind add-event)
(def txt text-event)
(def ev get-event)



;; music
(defn load-music [fname]
      (.loadTrack (Loader/instance) fname))

(defn play-loop [fname]
      (let [track (.loadTrack (Loader/instance) fname)]
           (.setLooping track true)
           (.play track)))
