package cosmilight;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
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
  
  static public Button createEventOptionComponent(EventOption option, String fxid) {
    Button optionComponent = new Button();
    optionComponent.setId(fxid);
    VBox content = new VBox();
    Text title = new Text(option.getTitle());
    title.getStyleClass().add("header");
    Text desc = new Text(option.getDesc());
    desc.getStyleClass().add("normalText");
    HBox cost = DisplayManager.createResourceListComponent(option.getCost());
    
    content.getChildren().add(title);
    content.getChildren().add(desc);
    content.getChildren().add(cost);
    
    optionComponent.setGraphic(content);
    return optionComponent;
  }
  static public HBox createResourceListComponent(ResourceList resources) {
    HBox costDisplay = new HBox();
    Text costs = new Text("Costs ");
    costDisplay.getChildren().add(costs);
    //loop over every resource type, append a Text element with the proper color 
    for (int i = 0; i < resources.length(); i++) {
      //in case the resource isnt found, default to a void resource
      Resource resource = Resource.createVoid();
      try {
        resource = resources.getResourceByIndex(i);
      } catch (InvalidIdException e) {
        System.out.println("DisplayManager.createResourceCost(): something went wrong, defaulted to void");
        System.out.println(e.getMessage());
      }
      
      //dangit why does this java not have String.repeat();
      for (int j = 0; j < resources.getAmountByIndex(i); j++) {
        costDisplay.getChildren().add(createResourceDot(resource, resources.getAmountByIndex(i)));
      }
    }
    
    return costDisplay;
  }
  static public Text createResourceDot(Resource resource, int amount) {
    Text resourceDots = new Text();
    
    resourceDots.getStyleClass().add("header");
    resourceDots.getStyleClass().add(resource.getStyleClass());
    resourceDots.setText(resource.getSymbol());
    
    return resourceDots;
  }
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
