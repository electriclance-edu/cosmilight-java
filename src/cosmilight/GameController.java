/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosmilight;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 4/23/2022
*/

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameController implements Initializable {
  @FXML Pane darkBg;
  @FXML VBox pauseMenu;
  @FXML StackPane constructionsMenu;
  @FXML ScrollPane eventsMenu;
  @FXML HBox amogus;
  private Node[] menus = new Node[3];
  
  @FXML private void openConstruction() throws Exception {
    new DisplayManager().openWindow("resources/fxml/ConstructionMenu.fxml","Construction",false);
  }
  
  @FXML private void openPauseMenu() throws Exception {
    new DisplayManager().openWindow("resources/fxml/pauseMenu.fxml","Pause Menu",false);
  }
  
  private void openWindow(String windowId) {
    darkBg.setVisible(true);
    for (Node menu : menus) {
      if (menu.getId().equals(windowId)) {
        menu.setVisible(true);
      } else {
        menu.setVisible(false);
      }
    }
  }
  private void hideDarkBg() {
    darkBg.setVisible(false);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    amogus.getChildren().add(DisplayManager.generateIsotile());
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
  }
}
