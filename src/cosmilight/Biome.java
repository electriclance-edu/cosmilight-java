package cosmilight;

import java.util.ArrayList;

/**
* @author Dementiabeans
* 
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
}
