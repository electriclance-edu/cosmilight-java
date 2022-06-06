package cosmilight;

import java.util.ArrayList;

/**
 * Represents a single biome type that may exist in the game. Used in the display of Tiles as well as areaTitles and areaDescriptions.
* @author Dementiabeans
*/
//Creation: 3/31/2022

public class Biome {
  private static ArrayList<Biome> biomes = new ArrayList();
  
  private String id;
  private String hex; //color of tile
  private String areaTitle;
  private String areaDesc;
  
  public Biome(String id, String hex, String areaTitle, String areaDesc) {
    this.id = id;
    this.hex = hex;
    this.areaTitle = areaTitle;
    this.areaDesc = areaDesc;
    
    biomes.add(this);
  }
//  **********************
//  GETTERS
//  **********************
  public static Biome getBiomeById(String id) {
    Biome searchedBiome;
    
    for (Biome biome : Biome.biomes) {
      if (biome.getId() == id) {
        searchedBiome = biome;
        return searchedBiome;
      }
    }
    return null; //NOTE: use exceptions?? but idk how to avoid having try/catch statements everywhere this is used
  }
  public String getId() {
    return id;
  }
  public String getHex() {
    return hex;
  }
  public String getTitle() {
    return areaTitle;
  }
  public String getDesc() {
    return areaDesc;
  }
  /**
  * Generates templates of Biomes for debug. Biomes will not be generated like this in the final game. 
  *
  * @author lance l.
  */
  static public void generateTemplates() {
    new Biome("templains","#7CA82D","Missing textures and glitching rocks fill your view.","Spooky. You might want to tread lightly, who knows if you'll fall through a missing hitbox or find an InvalidPositionException.");
    new Biome("grove","#368230","A magical grove.","You wake up with a lamp over here. The only artificial light source in the world. No biggie.");
    new Biome("desert","#DFA120","Endless dunes.","A desert with sand. Shocking. What else, cacti?");
    new Biome("ocean","#2C76B3","A big lake.","Perhaps too big. If it were smaller, you might call it a puddle. But this... this is no puddle. This is a lake. Or even bigger. A sea perhaps.");
    new Biome("abyss","#222A35","The light doesn't reach here.","The darkness is impenetrable. You step, trudge, stumble on through unknown territory. Your senses seem to betray you, as a confusion thicker than the fog begins to set in. You aren't supposed to be here. But where is here, even?");
  }
}
