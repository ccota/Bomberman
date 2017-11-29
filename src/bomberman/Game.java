package bomberman;

import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;

public class Game {
    private Grid grid;
    private Factory factory;
    private HardBlock[] hardBlock;



    /*
	|--------------------------------------------------------------------------
	| Game Options
	|--------------------------------------------------------------------------
	 */
    private GridType gridType= GridType.SIMPLE_GFX;
    private int cols= 20;
    private int rows=20;
    private int delay=20;



    public Game (){





    }

    /*
	|--------------------------------------------------------------------------
	| Init/Create all the shit
	|--------------------------------------------------------------------------
	 */
    public void init(){
        //Faz a grid
        grid = GridFactory.makeGrid(gridType, cols, rows);


        //Criar HardBlocks
        factory = new Factory();




        for (int i = 1; i<grid.getCols(); i +=2){
            for (int j = 1 ; j < grid.getRows(); j += 2){
                factory.hardBlocks(grid,i,j);
                grid.makeGridPosition( i, j);
            }
        }



    }

    public void start(){
        init();
    }

}
