package cosmilight;

/**
* @author Dementiabeans
*/
//Creation: 3/30/2022
//note 4/16/2022, lance
//  i spent an hour trying to figure out how to parse JSON
//  if there is a built-in Java JSON library I Will Cry but for now we have gson, thanks google overlords
//note 4/24/2022, lance
//  JOY EUPHORIA HAPPINESS YAY TURNS OUT JAVA HAS A BUILT IN XML PARSER
//  THANK U JDK OVERLORDS

import java.util.Random;
import java.io.FileReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Game {
  
  private static Game currentGame;
  private Random rand;
  private int seed = 100; //NOTE: test seed for now, change to randomized in future
  private World world;
  private Player player;
  static public Event[] events;
  static public Resource[] resources;
  static public Biome[] biome;
  static public ConstructionType[] constructionTypes;
  
  public Game(boolean cheats) {
    rand = new Random(seed);
    System.out.println(rand);
    
    world = new World();
    player = new Player(cheats);
    
    //generate events, resources, biome, constructiontypes from data
    
    currentGame = this;
  }
  public static Game get() {
    return currentGame;
  }
  public Player getPlayer() {
    return player;
  }
  public World getWorld() {
    return world;
  }
  
  private void generateGameData() {
    
  } 
  private void generateEvents() throws InvalidDataException {
    String json = "";
    
    try {
      FileReader reader = new FileReader("/resources/json/Events.json");
    } catch (Exception e) {}
  }
  
  public int randInt(int max) {
    return (int) Math.ceil(rand.nextDouble() * max);
  }
  public double randDouble(double max) {
    return rand.nextDouble() * max;
  }
}
