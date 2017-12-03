package bomberman.Gameobjects.movableobjects;

import bomberman.Game;
import bomberman.GameStatus;
import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.gameitems.ExtraBomb;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.gameitems.PowerUp;
import bomberman.grid.GridDirection;
import bomberman.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Player extends MovableObjects implements KeyboardHandler{

    private Game game = null;
    private int power=1;
    private int bombCapacity = 1;
    private int bombCurrent = bombCapacity;
    private Keyboard keyboard;




    public Player(GridPosition pos, Game game)  {
        super(pos);
        this.game=game;

    }

    public void  move() throws InterruptedException{

       keyboard = new Keyboard(this);

        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_UP);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_DOWN);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_LEFT);
        event2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent event3 = new KeyboardEvent();
        event3.setKey(KeyboardEvent.KEY_RIGHT);
        event3.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent event4 = new KeyboardEvent();
        event4.setKey(KeyboardEvent.KEY_SPACE);
        event4.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        keyboard.addEventListener(event);
        keyboard.addEventListener(event1);
        keyboard.addEventListener(event2);
        keyboard.addEventListener(event3);
        keyboard.addEventListener(event4);


    }







    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (game.getState() == GameStatus.BATTLE) {
            if (isDestroyed()) {
                return;
            }
            GameItems item;


            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_LEFT:


                    if (collisionDetector.hasEnemy(getPos().getCol() - 1, getPos().getRow())) {
                        this.setDestroyed();
                    }
                    if (!collisionDetector.isUnSafe(getPos().getCol() - 1, getPos().getRow())) {
                        getPos().moveInDirection(GridDirection.LEFT, 1);


                    }
                    break;
                case KeyboardEvent.KEY_RIGHT:
                    if (collisionDetector.hasEnemy(getPos().getCol() + 1, getPos().getRow())) {
                        this.setDestroyed();
                    }
                    if (!collisionDetector.isUnSafe(getPos().getCol() + 1, getPos().getRow())) {
                        getPos().moveInDirection(GridDirection.RIGHT, 1);
                    }
                    break;
                case KeyboardEvent.KEY_DOWN:
                    if (collisionDetector.hasEnemy(getPos().getCol(), getPos().getRow() + 1)) {
                        this.setDestroyed();
                    }
                    if (!collisionDetector.isUnSafe(getPos().getCol(), getPos().getRow() + 1)) {
                        getPos().moveInDirection(GridDirection.DOWN, 1);
                    }
                    break;
                case KeyboardEvent.KEY_UP:
                    if (collisionDetector.hasEnemy(getPos().getCol(), getPos().getRow() - 1)) {
                        this.setDestroyed();
                    }
                    if (!collisionDetector.isUnSafe(getPos().getCol(), getPos().getRow() - 1)) {
                        getPos().moveInDirection(GridDirection.UP, 1);
                    }
                    break;
                case KeyboardEvent.KEY_SPACE:
                    System.out.println("space pressed");
                    System.out.println(bombCurrent);
                    System.out.println(power);
                    if (bombCurrent > 0) {
                        game.add(new Bomb(Game.getGrid().makeGridPosition(this.getPos().getCol(), this.getPos().getRow(), "bomb.png"), collisionDetector, this));
                        bombCurrent--;
                    }

                    break;

                default:
                    break;

            }

            item = itemDetector.hasItem(getPos().getCol(), getPos().getRow());
            if (item instanceof ExtraBomb) {
                increseBombCapacity((ExtraBomb) item);
            } else if (item instanceof PowerUp) {
                increasePower((PowerUp) item);
            }

        }
    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void increaseCurrentBomb() {
            bombCurrent++;
    }

    private void increseBombCapacity(ExtraBomb extraBomb){  //just to make sure that the player really got an extra bomb
        bombCapacity ++;
        bombCurrent = bombCapacity;


    }

    private void increasePower(PowerUp powerUp){
        power ++;
        System.out.println("Power = " + power);
    }

    public Game getGame() {
        return game;
    }

    public int getPower() {
        return power;
    }


}
