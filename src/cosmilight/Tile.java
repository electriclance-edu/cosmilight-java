package cosmilight;

import java.util.ArrayList;
import java.util.Arrays;

/**
* @author Dementiabeans
*/
//Creation: 3/31/2022

public class Tile {
  private ArrayList<String> eventIds;
  private String biomeId;
  private boolean isGenerated = false;
  private boolean isDisplayed = false;
  private boolean isWalked = false;
  private boolean hasDevelopment = false;
  
  public Tile(String biomeId) {
    this.biomeId = biomeId;
    this.eventIds = new ArrayList<String>();
    
    if (Game.get().randInt(2) == 2) {
      if (biomeId.equals("ocean")) {
        eventIds.add("templateGrove");
      } else if (biomeId.equals("abyss")) {
        eventIds.add("templateVoid");
        eventIds.add("templateVoidTwo");
      } else {
        eventIds.add("templateForest");
      }
    }
    
    isGenerated = true;
  }
  public Tile(String biomeId, String[] eventIds) {
    this.biomeId = biomeId;
    //converts array to arraylist, ty https://stackoverflow.com/questions/157944/create-arraylist-from-array
    this.eventIds = new ArrayList<>(Arrays.asList(eventIds));
    
    isGenerated = true;
  }
//  **********************
//  GETTERS
//  **********************
  public boolean isGenerated() {
    return isGenerated;
  }
  public boolean isDisplayed() {
    return isDisplayed;
  }
  public boolean isExplored() {
    return eventIds.isEmpty();
  }
  public boolean isWalked() {
    return isWalked;
  }
  public boolean hasDevelopment() {
    return hasDevelopment;
  }
  public ArrayList<String> getEventIds() {
    return eventIds;
  }
  public Biome getBiome() {
    return Biome.getBiomeById(biomeId);
  }
//  **********************
//  SETTERS
//  **********************
  public void setDisplayed(boolean state) {
    isDisplayed = state;
  }
  public void isWalked(boolean state) {
    isWalked = state;
  }
//  **********************
//  OTHERS
//  **********************
  public void removeLatestEvent() {
    eventIds.remove(0);
  }
}
