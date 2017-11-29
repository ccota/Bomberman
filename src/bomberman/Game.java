package bomberman;

import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;

import java.util.ArrayList;

public class Game {
    private Grid grid;
    private Factory factory;
    private Player myplayer;
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
    public void init() throws InterruptedException{
        grid = GridFactory.makeGrid(gridType, cols, rows);
        grid.init();


        factory = new Factory();

        myplayer =factory.generatePlayer(grid,0,0);
        myplayer.move();

        /* -------------| Hard Blocks |------------------ */
        for (int i = 1; i<grid.getCols(); i +=2){
            for (int j = 1 ; j < grid.getRows(); j += 2){
                objects.add(factory.hardBlocks(grid,i,j));
            }
        }



        for (int x = 0; x<grid.getCols(); x ++){
            for (int y = 0 ; y < grid.getRows(); y ++){
               // System.out.println(grid.getCols());
                /*if ((x == 0 && y == 5)  ){
                    System.out.println("entrou");
                    continue;

                }*/



                    /*if ((x == 0 && y == 0)  || (x ==0 && y == 1) || (x == 1 && y == 0) ||
                        (x == grid.getCols()-1 && y == 0) || (x == grid.getCols() && y == 0) || (x == grid.getCols() && y == 1) ||

                        (x == 0 && y == grid.getRows()-1) || (x == 0 && y == grid.getRows()) || (x == 1 && y == grid.getRows()) ||
                        (x == grid.getCols() -1 && y == grid.getRows()) || (x == grid.getCols() && y == grid.getRows()) || (x == grid.getCols() && y == grid.getRows() -1)){
                        System.out.println("entrou");
                        continue;
                    }*/
                    if (checkPosAvailable(x,y)){

                        objects.add(factory.softBlocks(grid,x,y));
                    }

            }
        }






    }

    private boolean checkPosAvailable(int col, int row) {
        // If isAvailable == True
        for (int i = 0; i < objects.size() ; i++) {
            if (objects.get(i).getPos().getCol() == col && objects.get(i).getPos().getRow()==row){
                return false;
            }
        }
        return true;
    }

    public void start() throws InterruptedException{

        System.out.println(objects);
        init();
    }

}
