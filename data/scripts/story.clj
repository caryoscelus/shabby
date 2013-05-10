(ns story)

;; bind events (bind id name action)
;; id is for map bindings, otherwise unrequired (pass -1 for it)
;; name should be unique and can be used to call this event manually
;; THIS IS EXAMPLE
(comment
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
Some very long string to check wrapping.. Hopefully it won't be borked")))



(bind 0 "Oasis-1" (dia
"It's good to run into oasis after long jorney in the desert.
I drinked some water and filled bottles. Ahh.. so refreshing!"
{
 "Go futher! I have no more time to spend here."
    (txt "I'd want to stay here for longer.. But i need to go"),
 "Stay a little bit more.."
    (txt "I can relax a bit more.. I lean on palm. Huh.. Something crunched under me.
When i looked back, i discovered bunch of old bones. Human bones.
All my relaxation disappeared right away..")
}))

(bind 1 "Broken house" (txt
"I found abandoned house. "))


;; help
(bind -1 "help" (txt
"Welcome to Shabby!
Currently this is story-only game. You should move around, find places,
read story and use dialogs.

Controls:
[arrows] - moving
[space]  - activate feature
[f1]     - call this help
[f2]     - switch sound

Good luck!"))


;; load music
(load-music "data/music/01-obc.ogg" "obc")


;; init actions

;; play music
(play-loop "obc")
