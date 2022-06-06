  package cosmilight;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
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
  @FXML Text areaDesc;
  @FXML Text developmentDesc;
  @FXML ImageView eventImage;
  @FXML Text eventHeader;
  @FXML VBox eventContentParent;
  @FXML VBox eventOptionsParent;
  @FXML FlowPane debugMenu;
  @FXML Text debugIsotiles;
  @FXML Button exploreButton;
  @FXML Button eventButton;
  @FXML Button constructButton;
  @FXML VBox constructionComponentParent;
  @FXML HBox waterSymbols;
  @FXML HBox metalSymbols;
  @FXML HBox seedsSymbols;
  @FXML HBox energySymbols;
  private Node[] menus = new Node[3];
  
//  **********************
//  BUTTON FUNCTIONS
//  **********************
  
  @FXML private void openConstruction() throws Exception {
    openWindow("constructionsMenu");
  }
  @FXML private void openPauseMenu() throws Exception {
    Game.get().getWorld().toggleTime(false);
    openWindow("pauseMenu");
  }
  @FXML private void hideDarkBg() {
    darkBg.setMouseTransparent(true);
    darkBg.setVisible(false);
  }
  @FXML private void hideDebug() {
    debugMenu.setMouseTransparent(true);
    debugMenu.setVisible(false);
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
  /**
  * Increments the player's resource with the given amount and ids, and updates the screen accordingly.
  *
  * @author lance l.
  * @param resourceId The resource to increment in the player's stockpile.
  * @param amount The amount to increment the given resource by.
  */
  public void incrementPlayerResource(String resourceId, int amount) {
    Game.get().getPlayer().getStockpile().increment(resourceId, amount);
    updateResource(resourceId);
  }
  /**
  * Returns the resource symbol parent corresponding to the resource with the given id.
  *
  * @author lance l.
  * @param id The id of the resource to retrieve the symbol parent of.
  * @return HBox The resource symbol parent.
  */
  public HBox getSymbolList(String id) {
    if (id.equals("water")) {
      return waterSymbols;
    } else if (id.equals("metal")) {
      return metalSymbols;
    } else if (id.equals("seeds")) {
      return seedsSymbols;
    } else {
      return energySymbols;
    }
  }
  /**
  * Updates the display of all resources.
  *
  * @author lance l.
  */
  public void updateResources() {
    updateResource("water");
    updateResource("metal");
    updateResource("seeds");
    updateResource("energy");
  }
  /**
  * Updates the display of a single resource at the top left, adding or removing resource symbols.
  *
  * @author lance l.
  * @param resourceId The id of the resource to be updated.
  */
  public void updateResource(String resourceId) {
    HBox symbols = getSymbolList(resourceId);
    symbols.getChildren().clear();
    
    int resourceAmount = Game.get().getPlayer().getStockpile().getAmountById(resourceId);
    
    Resource resourceType;
    try {
      resourceType = Resource.get(resourceId);
    } catch (Exception e) {
      System.out.println("updateResource(): No resource found with id '" + resourceId + "', defaulting to void");
      resourceType = Resource.createVoid();
    }
    for (int i = 0; i < resourceAmount; i++) {
      symbols.getChildren().add(DisplayManager.createResourceDot(resourceType));
    }
  }
  /**
  * Updates the "disable" state of the Explore, Event, and Construct buttons at the bottom left, depending on the state of the tile the player is on.
  *
  * @author lance l.
  */
  public void updateButtons() {
    Player player = Game.get().getPlayer();
    Tile tile = Game.get().getTile(player.getX(), player.getY());
    exploreButton.setDisable(tile.isExplored());
    eventButton.setDisable(true);
    constructButton.setDisable((!tile.isExplored() || tile.hasDevelopment()));
  }
  /**
  * Updates the Event Menu with the information of the given event. Generates EventOptionComponents for the event's EventOptions.
  *
  * @author lance l.
  * @param event The event to display on the EventMenu.
  */
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
  /**
  * Updates the Construction Menu with all ConstructableTypes initialized.
  *
  * @author lance l.
  */
  public void updateConstructionList() {
    ArrayList<ConstructableType> ctypes = ConstructableType.getAllConstructables();
    
    constructionComponentParent.getChildren().clear();
    for (ConstructableType ctype : ctypes) {
      VBox component = DisplayManager.createConstructionComponent(ctype);
              
      component.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          construct(((Node) event.getSource()).getId());
        }
      });
      constructionComponentParent.getChildren().add(component);
    }
  }
//  **********************
//  TILE RELATED FUNCTIONS
//  **********************
  
  /**
  * Attempts to construct a Construct on the tile the player is currently standing on.
  *
  * @author lance l.
  * @param ctypeId The id of the Construct.
  */
  public void construct(String ctypeId) {
    Player player = Game.get().getPlayer();
    Tile tile = Game.get().getTile(player.getX(), player.getY());
    ConstructableType ctype = ConstructableType.getById(ctypeId);
    if (player.hasResources(ctype.getCost())) {
      System.out.println("");
      //subtract resources
      player.getStockpile().subtract(ctype.getCost());
      updateResources();
      //construct
      tile.setConstruct(new Construct(ctypeId));
      tile.setDevelopment(true);
      reloadAreaData();
      updateButtons();
      hideDarkBg();
    }
  }
  /**
  * Attempts to explore the current tile, triggering the most recent Event (if there is).
  *
  * @author lance l.
  */
  @FXML private void exploreCurrentTile() {
    //get the current tile that the player is standing on
    Player player = Game.get().getPlayer();
    int x = player.getX();
    int y = player.getY();
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
  * Displays a given Tile as a void tile on the screen.
  *
  * @author lance l.
  * @param x The x coordinate of the tile to display.
  * @param y The y coordinate of the tile to display.
  */
  public void displayTile(int x, int y) {
    displayTile(x, y, true);
  }
  /**
  * Displays a given Tile as an Isotile, either as a void tile, or as a tile with the proper biome colors.
  *
  * @author lance l.
  * @param x The x coordinate of the tile to display.
  * @param y The y coordinate of the tile to display.
  * @param asAbyss Whether or not to display the tile as an abyss/void tile. False = the tile will be displayed with proper colors.
  */
  public void displayTile(int x, int y, boolean asAbyss) {
    Tile tile = Game.get().getTile(x, y);
    
    Polygon isotile;
    
    debugIsotiles.setText("Isotiles: " + DisplayManager.isotiles);
    if (asAbyss) {
      isotile = DisplayManager.createIsotile(Biome.getBiomeById("abyss").getHex());
    } else {
      tile.setAbyss(false);
      isotile = DisplayManager.createIsotile(tile.getBiome().getHex());
    }
    isotile.setId("tile" + x + "," + y);
    
    //get the coordinates of the tile for absolute positioning
    double isotileX = (x * 100.0) + (y * 100.0) + (DisplayManager.getScreenHeight()/2.0);
    double isotileY = (x * 50.0) + (y * -50.0) + (DisplayManager.getScreenWidth()/2.0);
    //set the position
    isotile.setLayoutX(isotileX);
    isotile.setLayoutY(isotileY);
    
    //set functionality, when the player clicks the tile they should move there
    isotile.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        //get coords from id
        Node elem = (Node) event.getSource();
        String[] id = elem.getId().substring(4).split(",");
        int x = Integer.parseInt(id[0]);
        int y = Integer.parseInt(id[1]);
        
        movePlayer(x, y);
    
        //if the tile is void, set to not void, change display to not void
        Tile tile = Game.get().getTile(x, y);
        if (tile.isAbyss()) {
          tile.setAbyss(false);
          DisplayManager.setIsotileColor((Polygon) elem, tile.getBiome().getHex());
        }
      }
    });
    
    //final touches
    tile.setDisplayed(true);
    tile.setIsotile(isotile);
    //add the isotile to display
    isotilePositioner.getChildren().add(isotile);
  }
  /**
  * [Broken, is disabled for now] Attempts to display all tiles around a given tile, if they aren't displayed already.
  *
  * @author lance l.
  * @param x The x coordinate of the tile to surround with displayed tiles.
  * @param y The y coordinate of the tile to surround with displayed tiles.
  */
  public void surround(int x, int y) {
    World world = Game.get().getWorld();
    Point[] coords = world.getSurroundings(x, y);
    
    Tile tile;
    for (Point coord : coords) { 
      tile = world.getTile(coord.x, coord.y);
      if (!tile.isDisplayed()) {
        displayTile(coord.x, coord.y, false);
      } else if (tile.isAbyss()) {
        tile.setAbyss(false);
        Polygon isotile = tile.getIsotile();
        DisplayManager.setIsotileColor(isotile, tile.getBiome().getHex());
      } 
    }
  }
  /**
  * [DEBUG FUNCTION] Outputs to console the given x,y coordinates, with a prefix before it.
  *
  * @author lance l.
  * @param prefix The string to prefix the coordinates with.
  * @param x The x coordinate to output.
  * @param y The y coordinate to output.
  */
  public void printXY(String prefix, int x, int y) {
    System.out.println(prefix + " x: " + x + " y: " + y);
  }
  /**
  * Returns the FXID of the Isotile with the given x,y coordinates.
  *
  * @author lance l.
  * @param x The x coordinate of the isotile.
  * @param y The y coordinate of the isotile.
  * @return The FXID of the isotile.
  */
  public String getIsotileFXID(int x, int y) {
    return ("tile" + x + "," + y);
  }
  /**
  * [DEBUG FUNCTION] Sets the color of the isotile with the given coordinates to red. Is broken with surround() for some reason (something to do with broken coordinates).
  *
  * @author lance l.
  * @param x The x coordinate of the tile to turn red.
  * @param y The y coordinate of the tile to turn red.
  */
  public void debugRat(int x, int y) {
    printXY("debugRat() ",x,y);
    Tile tile = Game.get().getTile(x,y);
    System.out.println("debugRat() [isotileId] " + tile.getIsotile().getId());
    DisplayManager.setIsotileColor(tile.getIsotile(), "#FF5B61");
  }
  /**
  * Moves the player to the given coordinates, updating area and map data with it.
  *
  * @author lance l.
  * @param x The x coordinate of the tile to move the player to.
  * @param y The y coordinate of the tile to move the player to.
  */
  public void movePlayer(int x, int y) {
    printXY("movePlayer(): ",x,y);
    Game.get().getPlayer().setPosition(x, y);
    
    debugAddBiomeResource(x, y);
    moveMapTo(x, y);
    reloadAreaData();
    exploreCurrentTile();
    surround(x, y);
    updateButtons();
  }
  /**
  * [DEBUG FUNCTION] Temporary, is the only way to gain resources as of currently. Adds resources to the player's stockpile depending on what biome they just walked on. Eg. water for oceans.
  *
  * @author lance l.
  * @param x The x coordinate of the tile to check.
  * @param y The y coordinate of the tile to check.
  */
  public void debugAddBiomeResource(int x, int y) {
    Tile tile = Game.get().getWorld().getTile(x, y);
    String biomeName = tile.getBiome().getId();
    
    String[] biomeNames = new String[]{"templains","grove","desert","ocean","abyss"};
    int index = Arrays.asList(biomeNames).indexOf(biomeName);
    String[] resourceIds = new String[]{"metal","seeds","metal","water","energy"};
    incrementPlayerResource(resourceIds[index],1);
    
    if (tile.hasDevelopment()) {
      String[] constructIds = new String[]{"well","caves"};
      index = Arrays.asList(constructIds).indexOf(tile.getConstruct().getType());
      String[] resources = new String[]{"water","metal"};
      incrementPlayerResource(resources[index],5);
    }
  }
  /**
  * Reloads the area title and description at the bottom left of the screen depending on the state of the tile the player is currently standing on.
  *
  * @author lance l.
  */
  public void reloadAreaData() {
    Player player = Game.get().getPlayer();
    int x = player.getX();
    int y = player.getY();
    Tile tile = Game.get().getWorld().getTile(x, y);
    Biome biome = tile.getBiome();
    
      
    areaDesc.setText("");
    areaDesc.getStyleClass().clear();
    developmentDesc.setText("");
    developmentDesc.getStyleClass().clear();
    if (!tile.hasDevelopment()) {
      areaHeader.setText(biome.getTitle());
      areaDesc.setText(biome.getDesc());
    } else {
      areaDesc.setText(biome.getDesc());
      areaDesc.getStyleClass().add("faded");
      
      ConstructionType ctype = ConstructionType.getById(tile.getConstruct().getType());
      areaHeader.setText(ctype.getAreaTitle());
      developmentDesc.setText(ctype.getAreaDesc());
    }
  }
  /**
  * Shifts all isotiles such that the isotile with the given coordinates is at the very center of the screen.
  *
  * @author lance l.
  * @param x The x coordinate of the isotile to center.
  * @param y The y coordinate of the isotile to center.
  */
  public void moveMapTo(int x, int y) {
    double positionX = (x * -100.0) + (y * -100.0);
    double positionY = (x * -50.0) + (y * 50.0);
    
    isotilePositioner.setLayoutX(positionX);
    isotilePositioner.setLayoutY(positionY);
  }
  

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    hideDarkBg();
    hideDebug();
    
    menus[0] = pauseMenu;
    menus[1] = constructionsMenu;
    menus[2] = eventsMenu;
    
    updateResources();
    updateConstructionList();
    
    int tempRadius = 5;
    for (int i = -tempRadius; i < tempRadius; i++) {
      for (int j = -tempRadius; j < tempRadius; j++) {
        displayTile(i,j);
      }
    }
    
    Point startPoint = new Point(0,0);
    displayTile(startPoint.x,startPoint.y,false);
    movePlayer(startPoint.x,startPoint.y);
  }
  
  /**
  * Returns the player to the Start screen.
  *
  * @author lance l.
  */
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
