/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosmilight;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 4/23/2022
*/

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController {
  @FXML private void openConstruction() throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/ConstructionMenu.fxml"));
    Stage stage = new Stage();
    Scene scene = loader.load();
    stage.setScene(scene);
    stage.setTitle("Game Controller");
    stage.show();
  }
  
  @FXML private void openMenu() throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/pauseMenu.fxml"));
    Stage stage = new Stage();
    Scene scene = loader.load();
    stage.setScene(scene);
    stage.setTitle("Pause Menu");
    stage.show();
  }
}
