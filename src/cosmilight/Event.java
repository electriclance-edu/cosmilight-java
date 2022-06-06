package cosmilight;

import java.util.ArrayList;

//Creation: 3/31/2022
/**
 * Represents a single Event that may happen during the course of the game.
* @author Dementiabeans
*/

public class Event {
  public static Event currentlyDisplayed;
  private static ArrayList<Event> events = new ArrayList<Event>();
  
  private String id;
  private String title;
  private String imageUrl;
  private String[] content;
  private EventOption[] options;
  private Consequence[] consequences;
  private String[] spawnableBiomeNames;
  
  public Event(String i, String t, String img, String[] cont, EventOption[] op, Consequence[] cons, String[] biomes) {
    id = i;
    title = t;
    imageUrl = img;
    content = cont;
    options = op;
    consequences = cons;
    spawnableBiomeNames = biomes;
    events.add(this);
  }
//  **********************
//  GETTERS
//  **********************
  public String getId() {
    return id;
  }
  public String getImageUrl() {
    return imageUrl;
  }
  public String getTitle() {
    return title;
  }
  public String[] getContent() {
    return content;
  }
  public EventOption[] getOptions() {
    return options;
  }
  static public Event getEvent(String id) throws InvalidIdException {
    for (Event event : events) {
      if (event.getId().equals(id)) {
        return event;
      }
    }
    throw new InvalidIdException("getEvent(): no event with id '" + id + "' found");
  }
//  **********************
//  SETTERS
//  **********************
  static public void setDisplayed(Event event) {
    currentlyDisplayed = event;
  }
//  **********************
//  OTHER
//  **********************
  public void trigger() {
    
  }
//  **********************
//  TEMPLATE
//  **********************
  static public void generateTemplates() {
    ResourceList cost = new ResourceList();
    cost.add("water",2);
    cost.add("metal",3);
    cost.add("seeds",1);
    cost.add("energy",1);
    
    EventOption[] options = new EventOption[1];
    options[0] = new EventOption(
            "End the event.",
            "You have no need to engage with the event.",
            cost,
            new Condition[0],
            new Consequence[0]
    );
    
    new Event(
            "template",
            "TEMPLATEHEADER.",
            "areas/IMAGEFILENAME.png",
            new String[]{"LINEONE","LINETWO"},
            options,
            new Consequence[0],
            new String[0]
    );
    
    new Event(
            "tempGrass",
            "The Howling Winds",
            "areas/forest.png",
            new String[]{"Strong, howling winds had led you towards here, a vast expanse of grass extending beyond the horizon.","Peaceful, yes, but hollow might be a better word, for nothing other than the grass moved as far as you could see."},
            options,
            new Consequence[0],
            new String[0]
    );
    
    new Event(
            "tempGrove",
            "Fragility",
            "areas/grove.png",
            new String[]{"You find yourself in a grove, the vibrance of the foliage around you a stark contrast to the dark world outside.","Despite the colors, it seems almost everything here is dead. Who knew corpses could lie so beautifully?"},
            options,
            new Consequence[0],
            new String[0]
    );
    
    new Event(
            "tempDesert",
            "The Screaming Winds",
            "areas/void.png",
            new String[]{"If the winds howled in the grasslands, here, they moaned. Roared. Growled. Screamed.","While you guess the sands here tampered with the sound, a primal, instinctual part of you tells you to run, in the presence of the roar of the wind","Though, the longer you stayed, the more you felt like running wouldn't be the worst option."},
            options,
            new Consequence[0],
            new String[0]
    );
    
    new Event(
            "tempSea",
            "The Depths' Hum",
            "areas/void.png",
            new String[]{"A vibration thrums throughout your entire body, a force seemingly emanating from below the roiling waves.","As to whatever phenomena or automaton or beast slumbers below, all you gleam is that their snores are quite powerful. Gettin shakey round here. Wowzas. Speaking of Shakey, could really go for some mojos rn"},
            options,
            new Consequence[0],
            new String[0]
    );
    
    new Event(
            "tempVoidTwo",
            "A Call",
            "areas/void.png",
            new String[]{"Whispers echo through the thick darkness enveloping you.","A single thought enters your mind: 'You shouldn't be here.'","You're unsure if that thought is yours, or the void's."},
            options,
            new Consequence[0],
            new String[0]
    );
    new Event(
            "tempVoid",
            "An Invitation",
            "areas/void.png",
            new String[]{"As the shadows part for a short moment, a vast expanse of grey nothingness reveals itself before you.","But the darkness reforms, and you're returned to a familiar, alien darkness."},
            options,
            new Consequence[0],
            new String[0]
    );
  }
}
