package cosmilight;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 3/30/2022
*/

import java.util.Random;
import java.util.ArrayList;

public class Game {
  
  private Random rand;
  private int seed = 100; //NOTE: test seed for now, change to randomized in future
  private World world;
  private Player player;
  static public ArrayList<Event> events;
  static public ArrayList<Resource> resources;
  static public ArrayList<Biome> biome;
  static public ArrayList<ConstructionType> constructionTypes;
  
  public Game(boolean cheats) {
    rand = new Random(seed);
    System.out.println(rand);
    
    world = new World(100); //NOTE: 100 is size of world, get user input instead of hardcoded?
    player = new Player(cheats);
    
    //generate events, resources, biome, constructiontypes from data
  }
  private void generateGameData() {
    
  } 
  public int randInt(int max) {
    return (int) Math.ceil(rand.nextDouble() * max);
  }
  public double randDouble(double max) {
    return rand.nextDouble() * max;
  }
  
}
