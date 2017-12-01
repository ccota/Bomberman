package bomberman.Gameobjects.movableobjects;

import bomberman.Game;
import bomberman.Gameobjects.Bomb;
import bomberman.grid.GridColor;
import bomberman.grid.GridDirection;
import bomberman.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Player extends MovableObjects implements KeyboardHandler{

    private Game game = null;
    private Bomb bomb;
    private int power=1;
    private int bombCapacty = 1;
    private int bombCurrent = 1;
    private boolean dropOrder;







    public Player(GridPosition pos, Game game)  {
        super(pos);
        this.game=game;
       pos.setColor(GridColor.BLUE);


    }

    public void move() throws InterruptedException{

        Keyboard k = new Keyboard(this);

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

        k.addEventListener(event);
        k.addEventListener(event1);
        k.addEventListener(event2);
        k.addEventListener(event3);
        k.addEventListener(event4);

    }


    public Game getGame() {
        return game;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        System.out.println("key pressed1");
        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_LEFT:
                System.out.println("key pressed1");
                if (!collisionDetector.isUnSafe(getPos().getCol() -1, getPos().getRow())) {
                    getPos().moveInDirection(GridDirection.LEFT, 1);
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (!collisionDetector.isUnSafe(getPos().getCol() +1, getPos().getRow())) {
                    getPos().moveInDirection(GridDirection.RIGHT, 1);

                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (!collisionDetector.isUnSafe(getPos().getCol(), getPos().getRow()+1)) {
                    getPos().moveInDirection(GridDirection.DOWN, 1);
                }
                    break;
            case KeyboardEvent.KEY_UP:
                if (!collisionDetector.isUnSafe(getPos().getCol() , getPos().getRow() -1)) {
                    getPos().moveInDirection(GridDirection.UP, 1);
                }
                break;
            case KeyboardEvent.KEY_SPACE:
                System.out.println(bombCurrent);
                if (bombCurrent > 0) {
                    game.add(new Bomb(Game.getGrid().makeGridPosition(this.getPos().getCol(), this.getPos().getRow(),"bomb.png"), collisionDetector, this));
                    bombCurrent--;
                }

                break;

            default:break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void increseCurrentBomb() {
            bombCurrent++;
    }

}
