package cosmilight;

import java.util.ArrayList;

/**
* @author Dementiabeans
*/
//Creation: 3/31/2022

public class ResourceList {
  private ArrayList<String> resourceIds;
  private ArrayList<Integer> resourceAmounts;
  
  public ResourceList() {
    resourceIds = new ArrayList<String>();
    resourceAmounts = new ArrayList<Integer>();
  }
//  **********************
//  GETTERS
//  **********************
  /**
  * Returns the amount of the resource associated with a specific id in the resource list.
  *
  * @author lance l
  * @param id The id of the resource to get the amount of.
  * @return The amount of the resource with the given id.
  */
  public int length() {
    return resourceIds.size();
  }
  public int getResourceAmountById(String id) {
    int index = resourceIds.indexOf(id);
    return resourceAmounts.get(index);
  }
  public Resource getResourceByIndex(int index) throws InvalidIdException {
    try {
      return Resource.get(resourceIds.get(index));
    } catch (InvalidIdException e) {
      throw new InvalidIdException("ResourceList.getResourceByIndex: resource with index '" + index + "' not found, invalid id '" + resourceIds.get(index) + "', wrongly formed ResourceList?");
    }
  }
  public int getAmountByIndex(int index) {
    return resourceAmounts.get(index);
  }
//  **********************
//  SETTERS
//  **********************
  /**
  * Adds a resource to the Resource List, with both id and amount.
  *
  * @author lance l
  * @param id The id of the new resource.
  * @param amount The new resource's amount.
  */
  public void addResource(String id, int amount) {
    resourceIds.add(id);
    resourceAmounts.add(amount);
  }
}
