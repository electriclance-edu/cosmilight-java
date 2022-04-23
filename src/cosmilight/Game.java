package cosmilight;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 3/30/2022
*/
//@note 4/16/2022 - i spent an hour trying to figure out how to parse JSON
//if there is a built-in Java JSON library I Will Cry but for now we have gson, thanks google overlords

import java.util.Random;
import java.io.FileReader;

public class Game {
  
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
    
    world = new World(); //NOTE: 500 is size of world, get user input instead of hardcoded?
    player = new Player(cheats);
    
    //generate events, resources, biome, constructiontypes from data
  }
  private void generateGameData() {
    
  } 
  private void generateEvents() throws InvalidDataException {
    String json = "";
    
    try {
      FileReader reader = new FileReader("/resources/json/Events.json");
      
    } catch (Exception e) {
      throw new InvalidDataException("Game.generateEvents || oopsie happened");
    }
  }
  public int randInt(int max) {
    return (int) Math.ceil(rand.nextDouble() * max);
  }
  public double randDouble(double max) {
    return rand.nextDouble() * max;
  }
  
}
