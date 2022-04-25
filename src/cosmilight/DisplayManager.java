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
* @author Dementiabeans
* 
*/
//Creation: 4/23/2022

public class DisplayManager {
  public static Stage gameStage;
  public static GameController gameController;
  private static double screenHeight = 1250.0;
  private static double screenWidth = 625.0;
  
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
  public static Polygon createIsotile() {
    return createIsotile("#222a35");
  };
  public static Polygon createIsotile(String color) {
    Polygon isotile = new Polygon(new double[]{
      0.0, -45.5, 
      -90.0, 0.0, 
      0.0, 45.0, 
      90.0, 0
    });
    isotile.setFill(Paint.valueOf(color));
    
    return isotile;
  }
  public static double getScreenHeight() {
    return screenHeight;
  }
  public static double getScreenWidth() {
    return screenWidth;
  }
}
