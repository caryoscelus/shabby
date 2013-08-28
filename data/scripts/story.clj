(ns story)

;; story starts here
(bind "intro" (t
"
<img:data/gfx/portrait-radi.png>
Two years ago research group went into the desert and never come back. Their families
were trying to make goverment send rescue team, but all in vain. Those who were
too active, had frequent accidents and eventually all the heat ceased.
^^
But a week ago, after mayor got retired and archives were looked at by new bureaucrats,
some interesting facts accidently went onto the surface. As i was secretly told,
radio operator who was receiving transmissions from research group, was killed in
car accident just before he was going to make an important report. So that report
was lost and nobody knew about last transmission until it was found in archives...
^^
They didn't say what exactly was there in report, but two grands of advance in my pocket 
unambiguously tell that it's very important."

;; next page
(t
"So i'm sent into the desert. Without knowing where to go and what exactly to find.
I still doubt about this deal, but price is good."
)))

(bind "oasis" (t
"It's good to run into oasis after long jorney in the desert.
I drinked some water and filled bottles. Ahh.. so refreshing!"
(list
 (ln "Go futher! I have no more time to spend here."
    (t "I'd want to stay here for longer.. But i need to go"))
 (ln "Stay a little bit more.."
    (t "I can relax a bit more.. I lean on palm. Huh.. Something crunched under me.
When i looked back, i discovered bunch of old bones. Human bones.
All my relaxation disappeared right away.."))
)))

(bind "broken" (t
"I found abandoned house. Walls are partly "))


(bind "bar" (t
"Want to go to the bar?"
(list
 (ln "Sure"
     (fn [] (change-map "data/maps/bar.tmx")))
 (ln "Not now" nil))))

(bind "bar-out" (fn []
                      (change-map "data/maps/map.tmx")))

(bind "barman" (tr
"Barman"
(list
 (ln "Hello"
     (t "Yeah.."))
 (ln "Gimme beer"
     (fn []
         (.setActive (get-event "barman") 2 true)
         (.trigger (t "Gimme cash"))
         true))
 (lnh "Ok, here's your monies"
      (t "Thanks, but i won't give you anything for this.."))
 (ln "..."
     nil)
)))


;; help
(bind "help" (t
"Welcome to Shabby!
Currently this is story-only game. You should move around, find places,
read story and use dialogs.
^^
Controls:^
[arrows] - moving^
[space]  - activate feature^
[f1]     - call this help^
[f2]     - switch sound^
^^
Good luck!"
(list
 (ln "Intro" (ev "intro"))
 (ln "Close" nil)
)))

(bind "_init" (ev "help"))

;; init actions

;; play music
(play-loop "data/music/01-obc.ogg")
