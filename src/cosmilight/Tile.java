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
  private boolean hasDevelopment = false;
  
  public Tile(String biomeId) {
    this.biomeId = biomeId;
    this.eventIds = new ArrayList<String>();
    
    isGenerated = true;
  }
  public Tile(String biomeId, String[] eventIds) {
    this.biomeId = biomeId;
    //converts array to arraylist, ty https://stackoverflow.com/questions/157944/create-arraylist-from-array
    this.eventIds = new ArrayList<>(Arrays.asList(eventIds));
    
    isGenerated = true;
  }
  public boolean isGenerated() {
    return isGenerated;
  }
  public boolean isDisplayed() {
    return isDisplayed;
  }
  public boolean hasDevelopment() {
    return hasDevelopment;
  }
  public void setDisplayed(boolean state) {
    isDisplayed = state;
  }
  public Biome getBiome() {
    return Biome.getBiomeById(biomeId);
  }
}
