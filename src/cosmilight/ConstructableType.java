package cosmilight;

import java.util.ArrayList;

/**
 * Represents a specific kind of ConstructionType that is constructable by the player, therefore having costs and biome restrictions.
* @author Dementiabeans
*/
//Creation: 6/3/2022

public class ConstructableType extends ConstructionType {
  private ResourceList cost;
  private String desc;
  private String category;
  private boolean isDisplayed = false;
  static private ArrayList<ConstructableType> constructableTypes = new ArrayList();
  //unused for now
  private Biome[] biomeInclusions;
  private Biome[] biomeExclusions;
  
  public ConstructableType(String id, String name, String areaTitle, String areaDesc, ResourceList cost, String desc) {
    super(id,name,areaTitle,areaDesc);
    
    this.cost = cost;
    this.desc = desc;
    this.category = "generic";
    
    constructableTypes.add(this);
  }
  public ConstructableType(String id, String name, String areaTitle, String areaDesc, ResourceList cost, String desc, String category) {
    super(id,name,areaTitle,areaDesc);
    
    this.cost = cost;
    this.desc = desc;
    this.category = category;
    
    constructableTypes.add(this);
  }
//  **********************
//  GETTERS
//  **********************
  public ResourceList getCost() {
    return cost;
  }
  public String getDesc() {
    return desc;
  }
  public String getCategory() {
    return category;
  }
  static public ConstructableType getById(String id) {
    for (ConstructableType ctype : constructableTypes) {
      if (ctype.getId().equals(id)) {
        return ctype;
      }
    }
    return constructableTypes.get(0);
  }
  static public ArrayList<ConstructableType> getAllConstructables() {
    return constructableTypes;
  }
//  **********************
//  OTHER
//  **********************
  static public void generateTemplates() {
    ResourceList genericCost = new ResourceList();
    genericCost.add("water",1);
    genericCost.add("metal",1);
    genericCost.add("seeds",1);
    genericCost.add("energy",1);
    
    //6-4-2022, there shouldnt be \n but javafx isnt cooperating with the vbox component, so it's hardcoded for now
    new ConstructableType(
            "well",
            "Deep Well",
            "A wellspring of water.",
            "A well filled with dark water from the depths.",
            genericCost,
            "Though the lands you wander seem to be\nsucked of water and life, below,\nin certain areas, water hides from the\ndarkness.\nAll you need to do is tear open\nthe earth, and reach for it."
    );
    new ConstructableType(
            "caves",
            "Ravine Mine",
            "A crack in the earth.",
            "A faint light emanates from a ravine in the ground, now a mine of precious metal.",
            genericCost,
            "This building produces metal."
    );
  }
}
