package cosmilight;

/**
* @author Dementiabeans
*/
//Creation: 3/30/2022

public class Player {
  //stats
  private int x, y;
  private int tilesExplored; //for Event aLonelyPath
  
  public Player(boolean cheats) {
    x = 0;
    y = 0;
    
    if (cheats) {
      //generate player with 10k resources
    }
  }
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
}
