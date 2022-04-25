package cosmilight;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
//Creation: 4/23/2022

public class GameController implements Initializable {
  @FXML Pane darkBg, eventContent;
  @FXML VBox pauseMenu;
  @FXML StackPane constructionsMenu;
  @FXML ScrollPane eventsMenu;
  @FXML AnchorPane isotilePositionerPositioner;
  @FXML AnchorPane isotilePositioner;
  @FXML Text textOne, textTwo, textThree;
  @FXML Text areaHeader;
  @FXML Text areaFaded;
  @FXML Text areaContent;
  private Node[] menus = new Node[3];
  
  @FXML private void openConstruction() throws Exception {
    openWindow("constructionsMenu");
  }
  @FXML private void openPauseMenu() throws Exception {
    openWindow("pauseMenu");
  }
  @FXML private void openExploreMenu() throws Exception {
    openWindow("eventsMenu");
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
  
  @FXML private void hideDarkBg() {
    darkBg.setMouseTransparent(true);
    darkBg.setVisible(false);
  }
  
  public void displayTile(int x, int y) {
    Tile tile = Game.get().getWorld().getTile(x, y);
    tile.setDisplayed(true);
    
    Polygon isotile = DisplayManager.createIsotile(tile.getBiome().getHex());
    isotile.setId("tile" + x + "," + y);
    
    //x is calculated by getting the raw X of the tile (eg. -1, 1) and multiplying it by -100 to get the absolute postion in pixels.
    //x is also influenced by the raw Y of the tile, so that is added after bieng multiplied by 100.
    double isotileX = (x * 100.0) + (y * 100.0) + (DisplayManager.getScreenHeight()/2.0);
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
        moveMapTo(x, y);
        displayAreaData(x, y);
        //surround(x, y);
      }
    });
    
    isotilePositioner.getChildren().add(isotile);
  }
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    new Biome("templains","#7CA82D","Missing textures and glitching rocks fill your view.","Spooky. You might want to tread lightly, who knows if you'll fall through a missing hitbox or find an InvalidPositionException.");
    new Biome("grove","#ffffff","A magical grove.","You wake up with a lamp over here. The only artificial light source in the world. No biggie.");
    new Biome("desert","#DFA120","Endless dunes.","A desert with sand. Shocking. What else, cacti?");
    new Biome("ocean","#2C76B3","A big lake.","Perhaps too big. If it were smaller, you might call it a puddle. But this... this is no puddle. This is a lake. Or even bigger. A sea perhaps.");
    new Biome("abyss","#222A35","The light doesn't reach here.","The darkness is impenetrable. You step, trudge, stumble on through unknown territory. Your senses seem to betray you, as a confusion thicker than the fog begins to set in. You aren't supposed to be here. But where is here, even?");
    
    menus[0] = pauseMenu;
    menus[1] = constructionsMenu;
    menus[2] = eventsMenu;
    
    String[] templateContent = new String[2];
    templateContent[0] = "This is the first line of the template event. Stellaris offers a wide array of mechanics to entertain the player over hundreds of years of in-game time, or hours of real life time, but my favorite mechanic is the Crises. Not because they're particularly creative or unique or interesting-in their simplest form they're simply the bad guys that the player needs to throw fleets at-but because they represent an idealogy within the game's design, to give the player freedom to do whatever they want within the game.";
    templateContent[1] = "To explain a little further about this 'freedom' of the player, we have to learn what exactly Stellaris is. Some people call it a micromanagement game where you control a space empire, and yes, it's that, but to me specifically, it is a roleplaying game. A game where you can imagine the machinations of a massive gestalt mind, or the actions of a megacorporation turned interstellar, or the successes of a technocratic nation. The game is about furthering your own fantasy of what you want. Cool.";
    EventOption[] options = new EventOption[1];
    options[0] = new EventOption(
            "Template option one.",
            "You purchase the game Stellaris.",
            new ResourceList(),
            new Condition[0],
            new Consequence[0]
    );
    
    Event.currentlyDisplayed = new Event(
            "template",
            "A template event.",
            "areas/forest.png",
            templateContent,
            new EventOption[0],
            new Consequence[0],
            new String[0]
    );
    
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
