package cosmilight;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 4/18/2022
*/

public class InvalidResourceNameException extends Exception {
  public InvalidResourceNameException(String error) {
    super(error);
  }
}