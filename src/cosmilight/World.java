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
  
  public World(int worldSize) {
    tiles = generateWorld(new Tile[worldSize][worldSize]);
    
    //TODO: initialize world
  }
  
  //NOTE: generateWorld() might get deleted cause time restraints
  private Tile[][] generateWorld(Tile[][] tilemap) {
    
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
