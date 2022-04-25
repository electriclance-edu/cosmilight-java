package cosmilight;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

/**
* @author Dementiabeans
*/
//date Creation: 3/30/2022
//thought 4/24/2022, Lance
//  should time be in a separate TimeManager.java file? 
//  cause i mean space-time is separate for a reason

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
    tiles = generateWorld(new Tile[worldSize][worldSize]);
    
    for (Tile[] tileRow : tiles) {
      Arrays.fill(tileRow, new Tile("abyss"));
    }
    
    //hardcoded world
    this.setTile(0,0,new Tile("grove"));
    this.setTile(0,1,new Tile("templains"));
    this.setTile(-1,0,new Tile("desert"));
    this.setTile(-1,-1,new Tile("desert"));
    this.setTile(0,-1,new Tile("ocean"));
    this.setTile(1,-1,new Tile("ocean"));
    
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
  public Point[] getSurroundings(int x, int y) {
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
  
  //NOTE: generateWorld() might get deleted cause time restraints
  private Tile[][] generateWorld(Tile[][] tilemap) {
    
//    ALGORITHM to generate a world
//    the world will be structured as follows:
//      a baseplate of plains, 4k tiles/40% of the world [hardcoded]
//      50 lines of mountains sprinkled atop, 40 tiles per line, total 2k tiles/20% [rng generated]
//      10 large blobs of water, ~300 tiles per blob, total 3k tiles/30% [rng generated]
//      10 smaller blobs of ruins, ~10 tiles each blob, total 100 tiles/1% [rng generated]
//    the world will contain extra structures, maybe:
//      5 craters, composing of a crater rim, metallic plains, and a crater center
//          craters have a radius of 5 tiles
//          crater rim will be speckled and partially composed of normal world
//          metallic plains will be 90% replaced w  normal plains
//          crater center will have alien shrine
//    [Code]
//    among these sections, randomly select a set of 20 points, and a set of 10 points
//    Ruins are small 
//    
//    
//    

    Tile[] tilerow;
    for (int y = 0; y < tilemap.length; y++) {
      tilerow = tilemap[y];
      for (int x = 0; x < tilerow.length; x++) {
        //TODO: for each tile, generate stuffs
      }
    }
    
    return tilemap;
  }
  
  public void tick() {
    //increment producers
    //attempt damage
    //check for queued events
  }
  
  //NOTE: false = paused, true = unpaused
  public void toggleTime(boolean state) {
    unpaused = state;
  }
}
