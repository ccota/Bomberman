package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.Windows.Window;
import bomberman.Windows.WindowsType;
import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;
import bomberman.utilities.Random;

import java.util.ArrayList;


public class Game {

    private static Grid grid;
    private Factory factory;
    private Player myPlayer;
    private ArrayList<GameObjects> objects = new ArrayList<GameObjects>();
    private ArrayList<GameItems> items = new ArrayList<GameItems>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Bomb> activeBombs = new ArrayList<>();
    private CollisionDetector collisionDetector;
    private ItemDetector itemDetector;
    private Window window;
    private int currentStage=1;





    /**
	|--------------------------------------------------------------------------
	| Game WindowHowToPlay
	|--------------------------------------------------------------------------
	 */
    private GridType gridType= GridType.SIMPLE_GFX;
    private int cols= 25;
    private int rows=15;
    private int delay=100;

    private int cellSize = 40;
    private int height = rows * cellSize;
    private int width = cols * cellSize;
    private int menuHeight= height / 2;;
    private int menuWidth = width / 2;
    private int menuItemHeight = 70;



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


       // factory = new Factory();

        myPlayer =factory.generatePlayer(grid,0,0,this);
        objects.add(myPlayer);
        myPlayer.move();

        /* -------------| Hard Blocks |------------------ */
        for (int i = 1; i<grid.getCols(); i +=2){
            for (int j = 1 ; j < grid.getRows(); j += 2){
                objects.add(factory.hardBlocks(grid,i,j));
            }
        }

         /* -------------| Enemies |------------------ */
         /** Adjust this code later to be more flexible*/
        Enemy enemy;
        int counter = 0;
        int maxenimes=5;
        while (counter!=maxenimes){
            int randomX = Random.generate(4,grid().getCols());
            int randomY = Random.generate(4,grid().getRows());

            if (checkPosAvailable(randomX, randomY)) {
                enemy = factory.generateEnemies(grid, randomX ,randomY);
                enemies.add(enemy);
                objects.add(enemy);
                counter++;
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


        collisionDetector = new CollisionDetector(objects);
        for (Enemy e : enemies){
            e.setColisionDetector(collisionDetector);
        }
        itemDetector = new ItemDetector(items, objects);

        myPlayer.setColisionDetector(collisionDetector);
        myPlayer.setItemDetector(itemDetector);




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

    public void setWindow(Window window) {
        this.window = window;
    }

    public void start() throws InterruptedException{
        factory = new Factory();
        window=factory.generateWindow(WindowsType.STARTMENU,height,width,menuItemHeight,this);
        window.launch();



       /*init();

        while (true) {

            // Pause for a while
            Thread.sleep(delay);


            // Move the enemies
            for (Enemy curInstance: enemies) {
                if (!curInstance.isDestroyed()) {
                    curInstance.move();
                }
            }





        } */
    }


    public static Grid grid() {
        return grid;
    }


    public void add(GameObjects object) {
        objects.add(object);
    }

    public void addItem(GameItems object) {
        items.add(object);
    }



    public static Grid getGrid() {
        return grid;
    }

}

