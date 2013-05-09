package gg;

import com.badlogic.gdx.Gdx;

import java.util.Map;
import java.util.HashMap;

public class Story {
    private static Story _instance;
    
    StoryObject[] objects;
    
    Map<Integer, String> eventNames = new HashMap();
    Map<String, StoryEvent> events = new HashMap();
    
    public StoryScreen screen;
    
//     private Story () {
//         eventNames = new HashMap();
//         events = new HashMap();
//     }
    
    public static Story instance () {
        if (_instance == null) {
            _instance = new Story();
        }
        return _instance;
    }
    
    
    public void addEvent (int id, String name, StoryEvent event) {
        Gdx.app.log("story", "addevent "+name+" "+id);
        
        eventNames.put(id, name);
        events.put(name, event);
    }
    
    public StoryEvent getEvent (int id) {
        return getEvent(eventNames.get(id));
    }
    
    public StoryEvent getEvent (String name) {
        return events.get(name);
    }
    
    public void trigger (int id) {
        trigger(getEvent(id));
    }
    
    public void trigger (String name) {
        trigger(getEvent(name));
    }
    
    public void trigger (StoryEvent event) {
        if (event != null) {
            event.trigger();
        }
    }
    
    
    public void ui (StoryDialog dialogue) {
        if (screen != null) {
            screen.showStory(dialogue);
        }
    }
}
