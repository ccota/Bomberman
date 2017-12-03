package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;
import bomberman.utilities.Random;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;


public class Game implements KeyboardHandler {


    private static Grid grid;
    private Factory factory;
    private Player myPlayer;
    private ArrayList<GameObjects> objects = new ArrayList<GameObjects>();
    private ArrayList<GameItems> items = new ArrayList<GameItems>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Bomb> activeBombs = new ArrayList<>();
    private CollisionDetector collisionDetector;
    private ItemDetector itemDetector;
    private Keyboard keyboard;




    private GameStatus state= GameStatus.MENU;




    /**
	|--------------------------------------------------------------------------
	| Game Options
	|--------------------------------------------------------------------------
	 */
    private GridType gridType= GridType.SIMPLE_GFX;
    private int cols= 25;
    private int rows=15;
    private int delay =500;

    private int cellSize = 40;
    private int height = rows * cellSize;
    private int width = cols * cellSize;
    private int menuItemHeight = 105;



    public Game (){


    }




    /**
	|--------------------------------------------------------------------------
	| Init/Create all the shit
	|--------------------------------------------------------------------------
	 */
    public void init() throws InterruptedException{

        this.state = GameStatus.BATTLE;

        Picture bg = new Picture(10,10,"stage2bg.jpg");
        bg.draw();



        factory = new Factory();

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
        int maxenimes = 5;
        while (counter!=maxenimes){
            System.out.println("entro no while");
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

    public void start() throws InterruptedException{
        grid = GridFactory.makeGrid(gridType, cols, rows);
        grid.init();


        createKeyboard();
        menuLaunch();

       SoundEffect.music();


        while (true) {

            // Pause for a while
            Thread.sleep(delay);

            // checks if player want to drop a bomb
           /* if (myPlayer.getDropOrder()){
                dropBomb(myPlayer);
            }*/


            // Move the enemies
            for (Enemy curInstance: enemies) {
                if (!curInstance.isDestroyed()) {
                    curInstance.move();
                }
            }






        }
    }


    /**
     |--------------------------------------------------------------------------
     | MENU
     |--------------------------------------------------------------------------
     */


    private enum CurrentSelection {
        STARTGAME("start.gif", "startHover.gif"),
        OPTIONS("guide.gif", "guideHover.gif"),
        EXIT("exit.gif", "exitHover.gif");
        private String unselected;
        private String selected;
        CurrentSelection(String unselected, String selected) {
            this.unselected = unselected;
            this.selected = selected;
        }
        public String getUnselected() {
            return unselected;
        }
        public String getSelected() {
            return selected;
        }

    }


    private CurrentSelection currentSelection;
    private Picture[][] arrayOfPictures;
    private Picture launchBG;
    public void menuLaunch() {
        launchBG = new Picture(10,10,"backgroundInit.gif");
        launchBG.draw();
        currentSelection = CurrentSelection.STARTGAME;
        arrayOfPictures = new Picture[3][2];
        for (int row = 0; row < arrayOfPictures.length; row++) {

            for (int column = 0; column < arrayOfPictures[row].length; column++) {
                String imgsrc = null;
                if ((column % 2) == 0) {
                    imgsrc = CurrentSelection.values()[row].getUnselected();


                } else {
                    imgsrc = CurrentSelection.values()[row].getSelected();


                }
                arrayOfPictures[row][column] = new Picture(
                        (width / 2 - 173), (height / 2 - 50) + (menuItemHeight * row), imgsrc);

                if ((column % 2) == 0) {
                    arrayOfPictures[row][column].draw();
                }
            }
        }

        // Force 1st item to be selected
        arrayOfPictures[0][0].delete();
        arrayOfPictures[0][1].draw();


    }
    public void menuLaunchDelete() {
        for (int row = 0; row < arrayOfPictures.length; row++) {
            for (int column = 0; column < arrayOfPictures[row].length; column++) {
                arrayOfPictures[row][column].delete();
            }
        }
        launchBG.delete();

    }



    public void menuGuide () {
        System.out.println("ENTROU NO MENU GUIDE");
        Picture bg = new Picture(10,10,"controls.jpg");
        bg.draw();
        state=GameStatus.SHOW;

    }


    public void createKeyboard() {

        keyboard = new Keyboard(this);

        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_UP);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_DOWN);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_SPACE);
        event2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(event);
        keyboard.addEventListener(event1);
        keyboard.addEventListener(event2);

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


    public GameStatus getState() {
        return state;
    }
    public static Grid getGrid() {
        return grid;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        /** If on LaunchMenu */
        if (state == GameStatus.MENU) {
            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_DOWN:

                    int downHandler = currentSelection.ordinal() + 1;
                    if (downHandler >= CurrentSelection.values().length) {
                        break;
                    }
                    System.out.println(downHandler);
                    currentSelection = CurrentSelection.values()[currentSelection.ordinal() + 1];
                    arrayOfPictures[downHandler - 1][0].draw();
                    arrayOfPictures[downHandler - 1][1].delete();

                    arrayOfPictures[downHandler][0].delete();
                    arrayOfPictures[downHandler][1].draw();

                    break;

                case KeyboardEvent.KEY_UP:

                    int upHandler = currentSelection.ordinal() - 1;
                    if (upHandler < 0) {
                        break;
                    }

                    currentSelection = CurrentSelection.values()[currentSelection.ordinal() - 1];
                    arrayOfPictures[upHandler + 1][0].draw();
                    arrayOfPictures[upHandler + 1][1].delete();

                    arrayOfPictures[upHandler][0].delete();
                    arrayOfPictures[upHandler][1].draw();

                    break;

                case KeyboardEvent.KEY_SPACE:
                    switch (currentSelection) {
                        case STARTGAME:
                            try {

                                menuLaunchDelete();
                                init();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        case OPTIONS:
                            menuGuide();
                            return;

                        case EXIT:
                            System.exit(1);
                            break;

                    }

                    break;
                default:
                    break;
            }
        }

        /** Waiting mode for: GUIDE && GAMEOVER */
        if (state == GameStatus.SHOW) {
            switch (keyboardEvent.getKey()) {

                case KeyboardEvent.KEY_DOWN:

                    break;
                case KeyboardEvent.KEY_UP:

                    break;

                case KeyboardEvent.KEY_SPACE:

                    state = GameStatus.MENU;
                    menuLaunch();

                    break;
                default:
                    break;
            }
        }



    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {


    }
}

