package gg;

import java.util.HashMap;

public class StoryDialog {
    public final String text;
    public final HashMap <String, StoryEvent> options;
    
    public StoryDialog (String t) {
        text = t;
        options = new HashMap();
        options.put("Close", new StoryEvent() {
            public boolean trigger () {
                return false;
            }
        });
    }
    
    public StoryDialog (String t, HashMap<String, StoryEvent> opts) {
        text = t;
        options = opts;
    }
    
    public void add (String t, StoryEvent event) {
        options.put(t, event);
    }
}
