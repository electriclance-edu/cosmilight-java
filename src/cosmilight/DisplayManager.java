package cosmilight;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
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
  //overloaded, default value
  public static Polygon generateIsotile() {
    return generateIsotile("#222a35");
  };
  public static Polygon generateIsotile(String color) {
    Polygon isotile = new Polygon(new double[]{
      -360.0, -135.0,
      -450.0, -90.0,
      -360.0, -45.0,
      -270.0, -90.0
    });
    isotile.setFill(Paint.valueOf(color));
    HBox.setMargin(isotile,new Insets(0.0, 10.0, 0.0, 10.0));
    
    return isotile;
  }
}
