package cosmilight;

import java.util.ArrayList;

//Creation: 3/31/2022
/**
 * Represents a list of resources, for containing resource ids and amounts associated with them.
 * @author Dementiabeans
*/
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
  public int length() {
    return resourceIds.size();
  }
  public int getAmountById(String id) {
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
  public String getIdByIndex(int index) {
    return resourceIds.get(index);
  }
  public int getAmountByIndex(int index) {
    return resourceAmounts.get(index);
  }
//  **********************
//  SETTERS
//  **********************
  public void add(String id, int amount) {
    resourceIds.add(id);
    resourceAmounts.add(amount);
  }
  public void increment(String id, int amount) {
    int index = resourceIds.indexOf(id);
    resourceAmounts.set(index, resourceAmounts.get(index) + amount);
  }
  /**
  * Subtracts the amounts of the given resource list from the list.
  *
  * @author lance l
  * @param toSubtract the list to subtract.
  */
  public void subtract(ResourceList toSubtract) {
    int indexOfEquivalent;
    for (int i = 0; i < toSubtract.length(); i++) {
      indexOfEquivalent = resourceIds.indexOf(toSubtract.getIdByIndex(i));
      resourceAmounts.set(indexOfEquivalent, resourceAmounts.get(indexOfEquivalent) - toSubtract.getAmountByIndex(i));
    }
  }
  /**
  * Generates a list of all four resources with the given amounts.
  *
  * @author lance l
  * @param initialAmt The amount of each resource.
  * @return The generated ResourceList.
  */
  public static ResourceList generateList(int initialAmt) {
    ResourceList list = new ResourceList();
    list.add("water",initialAmt);
    list.add("metal",initialAmt);
    list.add("seeds",initialAmt);
    list.add("energy",initialAmt);
    return list;
  }
//  **********************
//  OTHER
//  **********************
  public boolean satisfiesCost(String id, int amount) {
    return getAmountById(id) >= amount;
  }
  /**
  * Compares a given resource list with the list, returning true if the resource amounts in this list are all greater than the given list's.
  *
  * @author lance l
  * @param comparison The resource list to compare with.
  * @return If the given comparison list has amounts all lower than this list, returns true.
  */
  public boolean satisfiesList(ResourceList comparison) {
    boolean satisfies = true;
    
    String comparedId;
    for (int i = 0; i < length(); i++) {
      comparedId = comparison.getIdByIndex(i);
      if (getAmountById(comparedId) >= comparison.getAmountByIndex(i)) {
        continue;
      } else {
        satisfies = false;
        break;
      }
    }
    return satisfies;
  }
}
