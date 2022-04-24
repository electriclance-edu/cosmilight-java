package cosmilight;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 4/23/2022
*/

public class DisplayManager {
  public static Stage gameStage;
  public static GameController gameController;
  
  public void openWindow(String url, String title, boolean hasToolbar) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
    Stage stage = new Stage();
    Scene scene = loader.load();
    stage.setScene(scene);
    stage.setTitle(title);
    
    if (!hasToolbar) {
      stage.initStyle(StageStyle.TRANSPARENT);
    }
    
    stage.show();
  }
}
