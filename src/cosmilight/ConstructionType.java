package cosmilight;

import java.util.ArrayList;

/**
 * Represents a single kind of Construct, used for storing data not associated with a single Construct.
* @author Dementiabeans
*/
//Creation: 3/31/2022

public class ConstructionType {
  private String id;
  private String name;
  private String areaTitle;
  private String areaDesc;
  static private ArrayList<ConstructionType> constructionTypes = new ArrayList();

  public ConstructionType(String id, String name, String areaTitle, String areaDesc) {
    this.id = id;
    this.name = name;
    this.areaTitle = areaTitle;
    this.areaDesc = areaDesc;
    
    constructionTypes.add(this);
  }
  
//  **********************
//  GETTERS
//  **********************
  //note: 6/3/2022 - WOAHHH U CAN ALT+INSERT THEN GENERATE SETTERS AND GETTERS, NETBEANS MAGICKKKK
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getAreaTitle() {
    return areaTitle;
  }
  public String getAreaDesc() {
    return areaDesc;
  }
  static public ConstructionType getById(String id) {
    for (ConstructionType ctype : constructionTypes) {
      if (ctype.getId().equals(id)) {
        return ctype;
      }
    }
    return constructionTypes.get(0);
  }
}
