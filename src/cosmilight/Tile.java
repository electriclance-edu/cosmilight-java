package cosmilight;

import java.util.ArrayList;

/**
* @author Dementiabeans
*/
//Creation: 3/31/2022

public class Tile {
  private ArrayList<Event> events;
  private int x;
  private int y;
  private boolean isDisplayed = false;
  
  public void setDisplayed(boolean state) {
    isDisplayed = state;
  }
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
}
