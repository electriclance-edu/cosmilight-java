package cosmilight;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.shape.Polygon;

//Creation: 3/31/2022
/**
 * Represents a single Tile in the World that the player can walk on. Is associated with a displayed Isotile, and can have one Construct.
* @author Dementiabeans
*/

public class Tile {
  private ArrayList<String> eventIds;
  private String biomeId;
  private boolean isGenerated = false;
  private boolean isDisplayed = false;
  private boolean isWalked = false;
  private boolean hasDevelopment = false;
  private boolean isAbyss = true;
  private Polygon isotile;
  private Construct construct;
  
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
  public boolean isAbyss() {
    return isAbyss;
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
  public Polygon getIsotile() {
    return isotile;
  }
  public Construct getConstruct() {
    return construct;
  }
//  **********************
//  SETTERS
//  **********************
  public void setDisplayed(boolean state) {
    isDisplayed = state;
  }
  public void setWalked(boolean state) {
    isWalked = state;
  }
  public void setAbyss(boolean state) {
    isAbyss = state;
  }
  public void setDevelopment(boolean state) {
    hasDevelopment = state;
  }
  public void setIsotile(Polygon tile) {
    isotile = tile;
  }
  public void setConstruct(Construct construct) {
    this.construct = construct;
  }
//  **********************
//  OTHERS
//  **********************
  public void removeLatestEvent() {
    eventIds.remove(0);
  }
}
