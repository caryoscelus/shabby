package gg;

import java.util.HashMap;

public class Dialogue {
    public final String text;
    public final HashMap <String, StoryEvent> options;
    
    public Dialogue (String t) {
        text = t;
        options = new HashMap();
        options.put("Close", new StoryEvent() {
            public boolean trigger () {
                return false;
            }
        });
    }
    
    public Dialogue (String t, HashMap<String, StoryEvent> opts) {
        text = t;
        options = opts;
    }
    
    public void add (String t, StoryEvent event) {
        options.put(t, event);
    }
}
