package cosmilight;

import java.util.ArrayList;

/**
* @author The Dementiabeans (Lance Libatique, Anika Panopio, Lance Chiu)
* @date Creation: 3/30/2022
*/

public class World {
  private int time = 0; //NOTE: currentTick, originally age
  private Tile[][] tiles;
  private ArrayList<Event> randomEventsPool;
  private ArrayList<Event> queuedEvents;
  private ArrayList<Integer> queuedEventsTick; //WEIRD: wth why does it not integer
  
  public World() {
    int worldSize = 200;
    tiles = generateWorld(new Tile[worldSize][worldSize]);
    
    //TODO: initialize world
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
}
