package cosmilight;

/**
* @author Dementiabeans
* 
*/
//Creation: 3/31/2022

public class Event {
  public static Event currentlyDisplayed;
  
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
  }
  
  public String getId() {
    return id;
  }
  public String getTitle() {
    return title;
  }
  public String[] getContent() {
    return content;
  }
  public void trigger() {
    
  }
}
