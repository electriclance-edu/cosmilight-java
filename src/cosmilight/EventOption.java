package cosmilight;

//Creation: 3/31/2022
/**
 * Represents an option that may be taken during an Event, with corresponding consequences and costs.
* @author Dementiabeans
* 
*/

public class EventOption {
  private String title;
  private String desc;
  private ResourceList cost;
  private Condition[] conditions;
  private Consequence[] consequences;
  
  public EventOption(String t, String d, ResourceList c, Condition[] cond, Consequence[] cons) {
    title = t;
    desc = d;
    cost = c;
    conditions = cond;
    consequences = cons;
  }
//  **********************
//  GETTERS
//  **********************
  public String getTitle() {
    return title;
  }
  public String getDesc() {
    return desc;
  }
  public ResourceList getCost() {
    return cost;
  }
//  **********************
//  OTHER
//  **********************
  public void trigger() {
    
  }
}