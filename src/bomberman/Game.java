package bomberman;

import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;

public class Game {
    private Grid grid;

    private int delay;




    public Game (GridType gridType, int cols, int rows, int delay){
        grid = GridFactory.makeGrid(gridType, cols, rows);
        this.delay = delay;

    }

    public void init(){
        grid.init();
        MapFactory m = new MapFactory();
        m.populateMap(grid);

    }

}
