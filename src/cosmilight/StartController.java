package cosmilight;

/**
* @author Dementiabeans
*/
//Creation: 3/30/2022

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartController implements Initializable {
  
  @FXML ImageView cheatsIcon;
  @FXML ImageView musicIcon;
  @FXML Button startButton;
  
  Image[] musicIcons = new Image[2];
  Image[] cheatsIcons = new Image[2];
  
  private boolean music = false, cheats = false;
  
  @FXML private void startGame(ActionEvent event) throws Exception {
    new Game(cheats);
    
    Node source = (Node) event.getSource(); //get elem
    Scene oldScene = (Scene) source.getScene(); //get scene from elem
    Stage stage = (Stage) oldScene.getWindow(); //get window from elem
    FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/Game.fxml")); //load fxml
    Scene scene = loader.load(); //generate Game scene
    
    DisplayManager.gameScene = scene;
    DisplayManager.gameStage = stage; //set window for DisplayManager
    DisplayManager.gameController = loader.getController(); //set controller for DisplayManager
    
    stage.setScene(scene);
    stage.show();
  }
  @FXML private void openTutorial() throws Exception {
    new DisplayManager().openWindow("resources/fxml/Tutorial.fxml","Cosmilight",true);
  }
  @FXML private void toggleMusic(MouseEvent event) {
    music = music ? false : true;
    musicIcon.setImage(musicIcons[music ? 1 : 0]);
  }
  @FXML private void toggleCheats(MouseEvent event) {
    cheats = cheats ? false : true;
    cheatsIcon.setImage(cheatsIcons[cheats ? 1 : 0]);
    if (cheats) {
      startButton.setText("~Start~");
    } else {
      startButton.setText("Start");
    }
  }
  
  @Override
  public void initialize(URL url, ResourceBundle rb){
    initializeIcons();
  }  
  private void initializeIcons() {
    musicIcons[0] = new Image(getClass().getResourceAsStream("resources/img/music_disabled.png"));
    musicIcons[1] = new Image(getClass().getResourceAsStream("resources/img/music_enabled.png"));
    cheatsIcons[0] = new Image(getClass().getResourceAsStream("resources/img/cheat_disabled.png"));
    cheatsIcons[1] = new Image(getClass().getResourceAsStream("resources/img/cheat_enabled.png"));
  }
  
}
