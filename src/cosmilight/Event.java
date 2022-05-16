package cosmilight;

import java.util.ArrayList;

/**
* @author Dementiabeans
* 
*/
//Creation: 3/31/2022s

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
    String[] templateContent = new String[2];
    templateContent[0] = "This is the first paragraph of the template event.";
    templateContent[1] = "This is the second paragraph of the template event.";
            
    ResourceList cost = new ResourceList();
    cost.addResource("water",3);
    cost.addResource("metal",5);
    cost.addResource("seeds",1);
    cost.addResource("energy",1);
    cost.addResource("error",1);
    
    EventOption[] options = new EventOption[1];
    options[0] = new EventOption(
            "Template option one.",
            "This is the description of the option.",
            cost,
            new Condition[0],
            new Consequence[0]
    );
    
    new Event(
            "templateForest",
            "A template event.",
            "areas/forest.png",
            templateContent,
            options,
            new Consequence[0],
            new String[0]
    );
    
    String[] templateTwoContent = new String[2];
    templateTwoContent[0] = "This is a grove event.";
    templateTwoContent[1] = "Super cool, desu ne.";
    new Event(
            "templateGrove",
            "A second template event.",
            "areas/grove.png",
            templateTwoContent,
            options,
            new Consequence[0],
            new String[0]
    );
    
    String[] templateThreeContent = new String[2];
    templateThreeContent[0] = "Whispers echo through the thick darkness enveloping you.";
    templateThreeContent[1] = "You really shouldn't be here.";
    new Event(
            "templateVoid",
            "A third template event.",
            "areas/void.png",
            templateThreeContent,
            options,
            new Consequence[0],
            new String[0]
    );
    
    String[] templateFourContent = new String[2];
    templateFourContent[0] = "A vast expanse of nothingness stretches out before you.";
    templateFourContent[1] = "At least, for a moment, before the shadows reform and you see something wholly novel, yet equally desolate.";
    new Event(
            "templateVoidTwo",
            "A fourth template event.",
            "areas/void.png",
            templateFourContent,
            options,
            new Consequence[0],
            new String[0]
    );
  }
}
