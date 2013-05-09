(ns story)

(import '(gg Story StoryEvent StoryDialog)
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

(defn add-event [id e-name new-event]
      (.addEvent (Story/instance) id e-name new-event))

(defn get-event [e-name]
      (.getEvent (Story/instance) e-name))

(defn text-event [text]
      (event (show-text text)))

(defn dialogue-event [text options]
      (event (show-dialogue text options)))


;; shortcuts
(def bind add-event)
(def txt text-event)
(def dia dialogue-event)
(def ev get-event)
