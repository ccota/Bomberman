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

    private enum GameStage {
         //string order == backgroung, hardBlock, softBlock, enemy, enemy quantity, percentage of softBlocks
        STAGE1 ("stage1bg.jpg", "hardBlockS1.gif", "softBlockS1.gif", "enemyS1.png", 3, 10),
        STAGE2("stage2bg.jpg", "hardBlockS2.gif", "softBlockS2.png", "enemyS2.png", 5, 50),
        STAGE3("stage3bg.jpg", "hardBlockS3.gif", "softBlockS3.gif" ,"enemyS3.png" , 7, 60);


        String background;
        String hardBlock;
        String softBlock;
        String enemy;

        int enemyNumber;
        int blockPercent;

        GameStage(String background, String hardBlock, String softBlock,String enemy, int enemyNumber, int blockpercent){
            this.background = background;
            this.hardBlock = hardBlock;
            this.softBlock = softBlock;
            this.enemy = enemy;
            this.enemyNumber = enemyNumber;
            this.blockPercent = blockpercent;
        }

        public String getBackground() {
            return background;
        }

        public String getHardBlock() {
            return hardBlock;
        }

        public String getSoftBlock() {
            return softBlock;
        }

        public String getEnemy() {
            return enemy;
        }

        public int getEnemyNumber() {
            return enemyNumber;
        }

        public int getBlockPercent() {
            return blockPercent;
        }
    }


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
    private GameStage currentStage = GameStage.STAGE1;




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

    private String backgroungImg;
    private String hardBlockImg;
    private String softBlockImg;
    private String enemyImg;
    private int numberOfStageEnemies;
    private int blockPercent;




    public Game (){


    }




    /**
	|--------------------------------------------------------------------------
	| Init/Create all the shit
	|--------------------------------------------------------------------------
	 */
    public void init() throws InterruptedException{

        switch (currentStage){

            case STAGE1:
                backgroungImg = GameStage.values()[0].getBackground();
                hardBlockImg = GameStage.values()[0].getHardBlock();
                softBlockImg = GameStage.values()[0].getSoftBlock();
                enemyImg = GameStage.values()[0].getEnemy();
                numberOfStageEnemies = GameStage.values()[0].getEnemyNumber();
                blockPercent = GameStage.values()[0].getBlockPercent();

                break;
            case STAGE2:
                backgroungImg = GameStage.values()[1].getBackground();
                hardBlockImg = GameStage.values()[1].getHardBlock();
                softBlockImg = GameStage.values()[1].getSoftBlock();
                enemyImg = GameStage.values()[1].getEnemy();
                numberOfStageEnemies = GameStage.values()[1].getEnemyNumber();
                blockPercent = GameStage.values()[1].getBlockPercent();
                break;
            case STAGE3:
                backgroungImg = GameStage.values()[2].getBackground();
                hardBlockImg = GameStage.values()[2].getHardBlock();
                softBlockImg = GameStage.values()[2].getSoftBlock();
                enemyImg = GameStage.values()[2].getEnemy();
                numberOfStageEnemies = GameStage.values()[2].getEnemyNumber();
                blockPercent = GameStage.values()[2].getBlockPercent();
                break;
        }


        Picture background = new Picture(10,10, backgroungImg);
        background.draw();



        factory = new Factory();

        myPlayer =factory.generatePlayer(grid,0,0,this);
        objects.add(myPlayer);
        myPlayer.move();

        /* -------------| Hard Blocks |------------------ */
        for (int i = 1; i<grid.getCols(); i +=2){
            for (int j = 1 ; j < grid.getRows(); j += 2){
                objects.add(factory.hardBlocks(grid,i,j,hardBlockImg));
            }
        }

         /* -------------| Enemies |------------------ */
         /** Adjust this code later to be more flexible*/
        Enemy enemy;
        int counter = 0;
        int maxenimes= numberOfStageEnemies;
        while (counter!=maxenimes){
            System.out.println("entro no while");
            int randomX = Random.generate(4,grid().getCols());
            int randomY = Random.generate(4,grid().getRows());

            if (checkPosAvailable(randomX, randomY)) {
                enemy = factory.generateEnemies(grid, randomX ,randomY, enemyImg);
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

                        if (randomNumber <=blockPercent) {
                            objects.add(factory.softBlocks(grid, x, y, softBlockImg));
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

        this.state = GameStatus.BATTLE;


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





        while (true) {
            System.out.println("o rodrigo está meuitio ste");
            // Pause for a while
            Thread.sleep(delay);

            // SoundEffect.music();
            // checks if player want to drop a bomb
           /* if (myPlayer.getDropOrder()){
                dropBomb(myPlayer);
            }*/


            // Move the enemies
            if (state==GameStatus.BATTLE){
                for (Enemy curInstance : enemies) {
                    if (!curInstance.isDestroyed()) {
                        curInstance.move();
                    }
                }
            }
            if (allEnemysDead() && state ==GameStatus.BATTLE) {
                System.out.println("check for casualties");
                resetGame();
                if (currentStage.ordinal() < GameStage.values().length) {
                    currentStage = currentStage.values()[currentStage.ordinal() + 1];
                    init();
                }else {
                    menuGuide();
                }

            }





        }
    }

    private boolean allEnemysDead() {
        int deadenemies = 0;
        for (Enemy e  :enemies) {
            if (e.isDestroyed()) {
                deadenemies++;
            }
        }
        if (deadenemies == enemies.size()){
            return true;
        }
        return false;
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
        Picture bg = new Picture(10,10,"controls.jpg");
        bg.draw();
        state=GameStatus.SHOW;

    }

    public void menuGameOver () {
        resetGame();
        state=GameStatus.SHOW;
        Picture bg = new Picture(10,10,"gameover.jpg");
        bg.draw();
        state=GameStatus.SHOW;

    }
    public void resetGame(){
        myPlayer = null;
        objects.clear();
        enemies.clear();
        items.clear();
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
                    state=GameStatus.MENU;
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

