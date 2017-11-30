package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;

import java.util.ArrayList;

public class Game {


    private static Grid grid;
    private Factory factory;
    private Player myPlayer;
    private ArrayList<GameObjects> objects = new ArrayList<GameObjects>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private CollisionDetector collisionDetector;



    /**
	|--------------------------------------------------------------------------
	| Game Options
	|--------------------------------------------------------------------------
	 */
    private GridType gridType= GridType.SIMPLE_GFX;
    private int cols= 15;
    private int rows=15;
    private int delay=400;



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

        myPlayer =factory.generatePlayer(grid,0,0);
        myPlayer.move();

        /* -------------| Hard Blocks |------------------ */
        for (int i = 1; i<grid.getCols(); i +=2){
            for (int j = 1 ; j < grid.getRows(); j += 2){
                objects.add(factory.hardBlocks(grid,i,j));
            }
        }



        /* -------------| Soft Blocks |------------------ */
        for (int x = 0; x<grid.getCols(); x ++){
            System.out.println(grid.getCols());
            for (int y = 0 ; y < grid.getRows(); y ++){
                    if (x==0 && y==0 || x==1 && y==0 || x==0 && y==1 || // Canto superior esquero
                        x==grid.getCols()-1 && y==0 || x==grid.getCols()-2 && y==0 || x==grid.getCols()-1 && y==1 || //Canto superior direito
                        x==0 && y==grid.getRows()-1 || x==0 && y==grid.getRows()-2 || x==1 && y==grid.getRows()-1  ||  // Canto inferior esq
                        x==grid.getCols()-1 && y==grid.getRows()-1 || x==grid.getCols()-2 && y==grid.getRows()-1 || x==grid.getCols()-1 && y==grid.getRows()-2 ){

                        continue;
                    }

                    else if (checkPosAvailable(x,y)){
                        int randomNumber = (int) (Math.random()*100);

                        if (randomNumber <=60) {
                            objects.add(factory.softBlocks(grid, x, y));
                        }
                    }

            }
        }
         /* -------------| Enemies |------------------ */
        for (int x = 0; x<grid.getCols(); x ++){
            for (int y = 0 ; y < grid.getRows(); y ++){

                // todo: fix enemies instead of random
                int randomNumber = (int) (Math.random()*100);
                if (randomNumber <=4) {
                    if (checkPosAvailable(x, y)) {
                        enemies.add(factory.generateEnemies(grid, x, y));

                    }
                }
            }
        }

        collisionDetector = new CollisionDetector(objects);
        for (Enemy e : enemies){
            e.setColisionDetector(collisionDetector);
        }

        myPlayer.setColisionDetector(collisionDetector);




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

        init();

        while (true) {

            // Pause for a while
            Thread.sleep(delay);
            if (myPlayer.getDropOrder()){
                dropBomb(myPlayer);





            }
            // MOVE BITCH
            for (Enemy curInstance: enemies) {
                curInstance.move();
            }


            // Update screen
            //Field.draw(cars);

        }
    }

    public void dropBomb(Player player){
            Bomb bomb = factory.generateBombs(grid, player.getPos().getCol(), player.getPos().getRow(), collisionDetector);

            objects.add(bomb);
            bomb.explode();
            player.resetDropOrder();
            if (player.getBombCurrent() == 0) {
                player.resetDropCurrent();
            }
    }

    public static Grid getGrid() {
        return grid;
    }

}

