package bomberman;

import bomberman.Gameobjects.GameObjects;
import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;

import java.util.ArrayList;

public class Game {
    private Grid grid;
    private Factory factory;
    private ArrayList<GameObjects> objects = new ArrayList<GameObjects>();




    /**
	|--------------------------------------------------------------------------
	| Game Options
	|--------------------------------------------------------------------------
	 */
    private GridType gridType= GridType.SIMPLE_GFX;
    private int cols= 15;
    private int rows=15;
    private int delay=20;



    public Game (){


    }




    /**
	|--------------------------------------------------------------------------
	| Init/Create all the shit
	|--------------------------------------------------------------------------
	 */
    public void init(){

        grid = GridFactory.makeGrid(gridType, cols, rows);

        factory = new Factory();

        /* -------------| Hard Blocks |------------------ */
        for (int i = 1; i<grid.getCols(); i +=2){
            for (int j = 1 ; j < grid.getRows(); j += 2){
                objects.add(factory.hardBlocks(grid,i,j));
            }
        }

        //for ()



    }

    public void start(){
        System.out.println(objects);
        init();
    }

}
