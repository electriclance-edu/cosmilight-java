package cosmilight;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 3/31/2022
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
}