package cosmilight;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//Creation: 4/23/2022
/**
 * Class dedicated to miscellaneous display functions that aren't associated with the game itself, separated to reduce clutter.
* @author Dementiabeans
*/

public class DisplayManager {
  public static Scene gameScene;
  public static Stage gameStage;
  public static GameController gameController;
  private static double screenHeight = 1250.0;
  private static double screenWidth = 625.0;
  public static int isotiles = 0;
  private static int constructionComponents = 0;
  
  static public VBox createConstructionComponent(ConstructableType ctype) {
    constructionComponents++;
    
    VBox component = new VBox();
    component.setId(ctype.getId());
    component.setMaxWidth(372);
    component.getStyleClass().add("constructionTile");
    VBox.setMargin(component, new Insets(0,0,15,0));
    
    
    Text header = new Text(ctype.getName());
    header.getStyleClass().add("smallHeader");
    component.getChildren().add(header);
    
    if (ctype.getCategory().equals("producer")) {
//      Text subHeaderProduces = new Text("Produces:");
//      subHeaderProduces.getStyleClass().add("faded");
//      subHeaderProduces.getStyleClass().add("tinyHeader");
//      component.getChildren().add(subHeaderProduces);
//      HBox productionList = createResourceListComponent(((ProducerConstructionType) ctype).getResourcesProduced());
//      component.getChildren().add(productionList);
//      
//      Text subtextSpeed = new Text("every " + ((ProducerConstructionType) ctype).getSpeed() + " moves.");
//      subtextSpeed.getStyleClass().add("faded");
//      component.getChildren().add(subtextSpeed);
    } else if (ctype.getCategory().equals("illuminator")) {
//      Text subtextLuminance = new Text("Illuminates a circle of x radius.");
//      subtextLuminance.getStyleClass().add("faded");
//      component.getChildren().add(subtextLuminance);
    }
    
    Text subHeaderCost = new Text("Costs:");
    subHeaderCost.getStyleClass().add("faded");
    subHeaderCost.getStyleClass().add("tinyHeader");
    component.getChildren().add(subHeaderCost);
    HBox costList = createResourceListComponent(ctype.getCost());
    component.getChildren().add(costList);
    
    Text desc = new Text(ctype.getDesc());
    component.getChildren().add(desc);
    
    return component;
  }
  static public Button createEventOptionComponent(EventOption option, String fxid) {
    Button optionComponent = new Button();
    optionComponent.setId(fxid);
    VBox content = new VBox();
    Text title = new Text(option.getTitle());
    title.getStyleClass().add("header");
    Text desc = new Text(option.getDesc());
    desc.getStyleClass().add("normalText");
    HBox cost = DisplayManager.createResourceListComponent(option.getCost());
    Text costs = new Text("Costs ");
    cost.getChildren().add(0, costs);
    
    content.getChildren().add(title);
    content.getChildren().add(desc);
    content.getChildren().add(cost);
    
    optionComponent.setGraphic(content);
    return optionComponent;
  }
  static public HBox createResourceListComponent(ResourceList resources) {
    HBox costDisplay = new HBox();
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
        costDisplay.getChildren().add(createResourceDot(resource));
      }
    }
    
    return costDisplay;
  }
  static public Text createResourceDot(Resource resource) {
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
    isotiles++;
    Polygon isotile = new Polygon(new double[]{
      0.0, -45.5, 
      -90.0, 0.0, 
      0.0, 45.0, 
      90.0, 0
    });
    isotile.setFill(Paint.valueOf(color));
    
    return isotile;
  }
  public static void setIsotileColor(Polygon isotile, String color) {
    isotile.setFill(Paint.valueOf(color)); 
  }
  public static double getScreenHeight() {
    return screenHeight;
  }
  public static double getScreenWidth() {
    return screenWidth;
  }
}
