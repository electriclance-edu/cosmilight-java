  package cosmilight;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

/**
* @author Dementiabeans
*/
// Creation: 4/23/2022

public class GameController implements Initializable {
  @FXML Pane darkBg, eventContent;
  @FXML VBox pauseMenu;
  @FXML StackPane constructionsMenu;
  @FXML ScrollPane eventsMenu;
  @FXML AnchorPane isotilePositionerPositioner;
  @FXML AnchorPane isotilePositioner;
  @FXML Text areaHeader;
  @FXML Text areaFaded;
  @FXML Text areaContent;
  @FXML ImageView eventImage;
  @FXML Text eventHeader;
  @FXML VBox eventContentParent;
  @FXML VBox eventOptionsParent;
  private Node[] menus = new Node[3];
  
//  **********************
//  BUTTON FUNCTIONS
//  **********************
  
  @FXML private void openConstruction() throws Exception {
    openWindow("constructionsMenu");
  }
  @FXML private void openPauseMenu() throws Exception {
    openWindow("pauseMenu");
  }
  @FXML private void hideDarkBg() {
    darkBg.setMouseTransparent(true);
    darkBg.setVisible(false);
  }
  private void openWindow(String windowId) {
    darkBg.setMouseTransparent(false);
    darkBg.setVisible(true);
    for (Node menu : menus) {
      if (menu.getId().equals(windowId)) {
        menu.setVisible(true);
      } else {
        menu.setVisible(false);
      }
    }
  }
  
//  **********************
//  TILE RELATED FUNCTIONS
//  **********************
  
  /**
  * Attempts to explore a given tile.
  *
  * @author lance
  * @param x The x coordinate of the tile to be displayed.
  * @param y The y coordinate of the tile to be displayed.
  */
  @FXML private void exploreCurrentTile() {
    //get the current tile that the player is standing on
    Player player = Game.get().getPlayer();
    int x = player.getX();
    int y = player.getY();
    System.out.println("x: " + x + " y:" + y);
    Tile tile = Game.get().getTile(x, y);
    //if the tile contains an event, trigger that event and remove it from the list
    if (!tile.isExplored()) {
      String eventId = tile.getEventIds().get(0);
      tile.removeLatestEvent();
      Event event;
      try {
        event = Event.getEvent(eventId);
        Event.setDisplayed(event);
        displayEvent(event);
        openWindow("eventsMenu");
      } catch (InvalidIdException e) {
        System.out.println(e.getMessage());
      }
    }
  }
  /**
  * Creates an isotile on the Game screen based on the corresponding tile in World.tiles.
  *
  * @author lance
  * @param x The x coordinate of the tile to be displayed.
  * @param y The y coordinate of the tile to be displayed.
  */
  public void displayTile(int x, int y) {
    Tile tile = Game.get().getWorld().getTile(x, y);
    tile.setDisplayed(true);
    
    Polygon isotile = DisplayManager.createIsotile(tile.getBiome().getHex());
    isotile.setId("tile" + x + "," + y);
    
    //pixel x is calculated by getting the raw X of the tile (eg. -1, 1) and multiplying it by -100 to get the absolute postion in pixels.
    //pixel x is also influenced by the raw Y of the tile, so that is added after being multiplied by 100.
    double isotileX = (x * 100.0) + (y * 100.0) + (DisplayManager.getScreenHeight()/2.0);
    //pixel y has a similar deal to x
    double isotileY = (x * 50.0) + (y * -50.0) + (DisplayManager.getScreenWidth()/2.0);
    
    isotile.setLayoutX(isotileX);
    isotile.setLayoutY(isotileY);
    
    isotile.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Node elem = (Node) event.getSource();
        String[] id = elem.getId().substring(4).split(",");
        int x = Integer.parseInt(id[0]);
        int y = Integer.parseInt(id[1]);
        movePlayer(x, y);
      }
    });
    
    isotilePositioner.getChildren().add(isotile);
  }
  /**
  * Updates the Player's position, displaying proper data based on what tile the player will be on.
  *
  * @author lance
  * @param x The x coordinate of the player's new position.
  * @param y The y coordinate of the player's new position.
  */
  public void movePlayer(int x, int y) {
    Game.get().getPlayer().setPosition(x, y);
    
    moveMapTo(x, y);
    displayAreaData(x, y);
    Tile currentTile = Game.get().getCurrentTile();
    
    if (currentTile.isExplored()) {
      
    }
    exploreCurrentTile();
    //surround(x, y);
  }
  /**
  * [BROKEN] Displays tiles around the 
  *
  * @author lance
  * @param x The x coordinate of the player's new position.
  * @param y The y coordinate of the player's new position.
  */
  public void surround(int x, int y) {
    World world = Game.get().getWorld();
    Point[] coords = world.getSurroundings(x, y);
    
    for (Point coord : coords) {
      Tile tile = world.getTile(coord.x, coord.y);
      if (!tile.isDisplayed()) {
        displayTile(coord.x, coord.y);
      }
    }
  }
  public void displayAreaData(int x, int y) {
    Tile tile = Game.get().getWorld().getTile(x, y);
    Biome biome = tile.getBiome();
    
    areaHeader.setText(biome.getTitle());
      
    if (tile.hasDevelopment()) {
      areaFaded.setText(biome.getDesc());
      areaContent.setText("");
    } else {
      areaFaded.setText("");
      areaContent.setText(biome.getDesc());
    }
  }
  public void moveMapTo(int x, int y) {
    double positionX = (x * -100.0) + (y * -100.0);
    double positionY = (x * -50.0) + (y * 50.0);
    
    isotilePositioner.setLayoutX(positionX);
    isotilePositioner.setLayoutY(positionY);
  }
  
  public void displayEvent(Event event) {
    Image image = new Image(Main.class.getResourceAsStream("resources/img/" + event.getImageUrl()));
    eventImage.setImage(image);
    
    eventContentParent.getChildren().clear();
    eventHeader.setText(event.getTitle());
    for (String paragraph : event.getContent()) {
      Text text = new Text(paragraph);
      text.setWrappingWidth(370);
      VBox.setMargin(text, new Insets(0.0, 0.0, 20.0, 0.0));
      eventContentParent.getChildren().add(text);
    }
    eventOptionsParent.getChildren().clear();
    int i = 0;
    for (EventOption option : event.getOptions()) {
      Button optionComponent = DisplayManager.createEventOptionComponent(option, Integer.toString(i));
    
      optionComponent.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          //trigger on eventoption
          hideDarkBg();
          
          //insert code for eventoption trigger, supposed to get from displayedEvent
        }
      });
      
      eventOptionsParent.getChildren().add(optionComponent);
      i++;
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    menus[0] = pauseMenu;
    menus[1] = constructionsMenu;
    menus[2] = eventsMenu;
    
    //hardcoded for now
    displayTile(0,0);    
    displayTile(0,1);    
    displayTile(-1,0);    
    displayTile(-1,-1);    
    displayTile(0,-1);    
    displayTile(1,-1);
    //abyss
    displayTile(1,0);    
    displayTile(1,1);    
    displayTile(0,2);    
    displayTile(-1,1);   
    displayTile(2,-1);   
    displayTile(1,-2);   
    displayTile(0,-2);   
    displayTile(-1,-2);   
    displayTile(-2,0);   
    displayTile(-2,-1);   
    
    moveMapTo(0,0);
    displayAreaData(0,0);
    hideDarkBg();
  }
  
  @FXML private void clickQuit(ActionEvent event) throws Exception {
        Node source = (Node) event.getSource();
        Scene oldScene = (Scene) source.getScene();
        Stage stage = (Stage) oldScene.getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/Start.fxml"));
        Scene scene = loader.load();

        stage.setScene(scene);
        stage.show();
    }
}
