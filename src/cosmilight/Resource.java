package cosmilight;

import java.util.ArrayList;

/**
* @author Dementiabeans
*/
//Creation: 3/31/2022
//note 5/16/2022, lance
//  unicode is so weiiiiird, why is the water droplet 2 utf-8 strings

public class Resource {
  static private ArrayList<Resource> resources = new ArrayList<Resource>();
  private String id;
  private String name;
  private String symbol;
  //styleClass = "color-" + id;

  public Resource(String id, String name, String symbol) {
    this.id = id;
    this.name = name;
    this.symbol = symbol;
    
    resources.add(this);
  }
  public Resource(String id, String name, String symbol, boolean ignore) {
    this.id = id;
    this.name = name;
    this.symbol = symbol;
    
    if (!ignore) {
      resources.add(this);
    }
  }
//  **********************
//  GETTERS
//  **********************
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getSymbol() {
    return symbol;
  }
  public String getStyleClass() {
    return "color-" + id;
  }
  static public Resource createVoid() {
    return new Resource("void", "Void", "\u2B24", true);
  }  
  static public Resource get(String id) throws InvalidIdException {
    for (Resource resource : resources) {
      if (resource.getId().equals(id)) {
        return resource;
      }
    }
    throw new InvalidIdException("getResource(): no resource with id '" + id + "' found");
  }
//  **********************
//  TEMPLATE
//  **********************
  static public void generateTemplates() {
    new Resource("water", "Water", "\uD83C\uDF22");
    new Resource("metal", "Metal", "\u25C6");
    new Resource("seeds", "Seeds", "\uD83D\uDF89");
    new Resource("energy", "Energy", "\uD83D\uDFC6");
  }
}
