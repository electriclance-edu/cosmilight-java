package cosmilight;

/**
 * Represents a construct that is on a tile, and is interacting with the world.
* @author Dementiabeans
*/
//Creation: 6/4/2022
public class Construct {
  private String ctypeId;
  
  //unused for now
  private ResourceList stockpile;
  private int health;
  
  public Construct(String ctypeId) {
    this.ctypeId = ctypeId;
    this.health = 100;
  }
  
  public String getType() {
    return ctypeId;
  }
}
