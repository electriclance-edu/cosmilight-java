package cosmilight;

//Creation: 3/30/2022
/**
 * Represents all data associated with the Player, such as location and how many resources they have. Only one instance of this exists per Game.
 * @author Dementiabeans
*/

public class Player {
  //stats
  private int x, y;
  private int tilesExplored; //for Event aLonelyPath
  private ResourceList stockpile;
  
  public Player(boolean cheats) {
    x = 0;
    y = 0;
    
    stockpile = ResourceList.generateList(5);
  }
//  **********************
//  GETTERS
//  **********************
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  /**
  * Checks if the player has the given resources in their stockpile. Is skipped if cheats are enabled. 
  *
  * @author lance l.
  * @param comparison ResourceList to compare the stockpile with.
  * @return True if the player has the given resources.
  */
  public boolean hasResources(ResourceList comparison) {
    return Game.get().hasCheats() ? true : stockpile.satisfiesList(comparison);
  }
  public ResourceList getStockpile() {
    return stockpile;
  }
//  **********************
//  SETTERS
//  **********************
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
