package cosmilight;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

//date Creation: 3/30/2022
//thought 4/24/2022, Lance
//  should time be in a separate TimeManager.java file? 
//  cause i mean space-time is separate for a reason
/**
 * Represents a single game World and data associated with that, such as the Tile list, the (as-of-yet unimplemented) Day-Night cycle, and queued events. Only one instance exists per Game.
 * @author Dementiabeans
*/
public class World {
  private Tile[][] tiles;
  private ArrayList<Event> randomEventsPool;
  private ArrayList<Event> queuedEvents;
  private ArrayList<Integer> queuedEventsTick;
  
  private int worldSize;
  
  private int time = 0;
  private boolean unpaused = false;
  
  public World() {
    worldSize = 200;
    generateWorld(new Tile[worldSize][worldSize]);
    
    unpaused = true;
  }
  public Tile getTile(int x, int y) {
    return getTile(x,y,true);
  }
  public Tile getTile(int x, int y, boolean relativeToCenter) {
    int offset = (relativeToCenter ? worldSize/2 : 0);
    return tiles[x + offset][y + offset];
  }
  public void setTile(int x, int y, Tile newValue) {
    setTile(x,y,newValue,true);
  }
  public void setTile(int x, int y, Tile newValue, boolean relativeToCenter) {
    int offset = (relativeToCenter ? worldSize/2 : 0);
    tiles[x + offset][y + offset] = newValue;
  }
  /**
  * Returns an array of coordinates of all 8 points surrounding the given point, including diagonals. 
  *
  * @author lance l.
  * @param x The x coordinate of the point to get the surroundings of.
  * @param y The y coordinate of the point to get the surroundings of.
  * @return A list of the coordinates of all 8 points surrounding the given point.
  */
  public static Point[] getSurroundings(int x, int y) {
    Point[] translations = {
      new Point(0,1),
      new Point(1,1),
      new Point(1,0),
      new Point(1,-1),
      new Point(0,-1),
      new Point(-1,-1),
      new Point(-1,0),
      new Point(-1,1)
    };
    
    Point[] points = new Point[8];
    
    for (int i = 0; i < translations.length; i++) {
      points[i] = new Point(x + translations[i].x, y + translations[i].y);
    }
    
    return points;
  }
  
  /**
  * Fills the given 2d array of tiles with a procedurally generated world. 
  *
  * @author lance l.
  * @param tilemap The tilemap to fill with a procedurally generated world.
  */
  private void generateWorld(Tile[][] tilemap) {
    
//    ALGORITHM to generate a world
//    the world will be structured as follows:
//      a baseplate of plains, 4k tiles/40% of the world [hardcoded]
//      10 large blobs of water, ~300 tiles per blob, total 3k tiles/30% [rng generated]
//      10 smaller blobs of ruins, ~10 tiles each blob, total 100 tiles/1% [rng generated]
//      50 lines of mountains sprinkled atop, 40 tiles per line, total 2k tiles/20% [rng generated]
//    the world will contain extra structures, maybe:
//      5 craters, composing of a crater rim, metallic plains, and a crater center
//          craters have a radius of 5 tiles
//          crater rim will be speckled and partially composed of normal world
//          metallic plains will be 90% replaced w  normal plains
//          crater center will have alien shrine
//    [Code]
//    among these sections, randomly select a set of 20 points, and a set of 10 points
//    Ruins are small 

    //initialize tilemap
    Tile[] tilerow;
    for (int y = 0; y < tilemap.length; y++) {
      tilerow = tilemap[y];
    }
    tiles = tilemap;
    
    
    //generate baseplate
    for (Tile[] tileRow : tiles) {
      int rand = Game.get().randInt(3);
      if (rand == 1) { //abyss for now
        Arrays.fill(tileRow, new Tile("abyss",new String[]{"tempVoid"}));
      } else if (rand == 2) {
        Arrays.fill(tileRow, new Tile("abyss",new String[]{"tempVoidTwo"}));
      } else {
        Arrays.fill(tileRow, new Tile("abyss")); 
      }
    }
    
    //temporary world gen, worldgen will Not work like this
    int tempRadius = 5;
    String[] biomes = {"grove","templains","desert","ocean","ocean"};
    String[] events = {"wah","tempGrass","tempDesert","tempSea","tempSea"};
    for (int i = -tempRadius; i < tempRadius; i++) {
      for (int j = -tempRadius; j < tempRadius; j++) {
        int index = Game.get().randInt(biomes.length) - 1;
        
        if (biomes[index].equals("grove")) {
          this.setTile(i,j,new Tile(biomes[index],new String[]{"tempGrass","tempGrove","tempVoid"}));
        } else if (Game.get().randInt(5) == 1) {
          this.setTile(i,j,new Tile(biomes[index],new String[]{events[index]}));
        } else {
          this.setTile(i,j,new Tile(biomes[index]));
        }
      }
    }
    
    
  }
  
  /**
  * Simulates a single timestep of the game, incrementing producer constructs, damaging illuminators, and attempting to trigger random and queued events. 
  *
  * @author lance l.
  */
  public void tick() {
    //increment producers
    //attempt damage
    //check for queued events
  }
  
  /**
  * Pauses or unpauses time in the game. 
  *
  * @author lance l.
  * @param state The new state of time in the game. False = paused.
  */
  public void toggleTime(boolean state) {
    unpaused = state;
  }
}
