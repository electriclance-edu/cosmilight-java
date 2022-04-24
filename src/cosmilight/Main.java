package cosmilight;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 3/30/2022
* @note 3/31/2022, Lance
* IT FEELS SO REAL NOW :OOO WE'RE MAKING A GAME YAY
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    stage.setResizable(false);
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/Start.fxml"));
    Scene scene = loader.load();
    stage.setScene(scene);
    stage.setTitle("Cosmilight");
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
