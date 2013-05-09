(ns story)

;; bind events (bind id name action)
;; id is for map bindings, otherwise unrequired (pass -1 for it)
;; name should be unique and can be used to call this event manually
(bind 0 "Hello" (txt "Hello, world"))
(bind 1 "Clojure" (txt "Hello from Clojure"))
(bind 2 "StoryDialog"
      (dia
          ;; initial text
          "Do you like coffee?"
          {
           ;; dialogue answers & reactions
           "Yes" (txt "Here is a cup for ya!"),
           "No" (txt "Well, you can drink tea then.."),
           ;; This reaction is `trigger event named "Hello"`
           "Say hello to me!" (ev "Hello")
           }))
(bind 3 "Chunk" (txt
"Big chunk of text
Really big
And even bigger!!!
Some very long string to check wrapping.. Hopefully it won't be borked"))
