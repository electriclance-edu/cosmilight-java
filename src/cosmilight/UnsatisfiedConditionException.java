package cosmilight;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 4/18/2022
*/

public class UnsatisfiedConditionException extends Exception {
  public UnsatisfiedConditionException(String error) {
    super(error);
  }
}