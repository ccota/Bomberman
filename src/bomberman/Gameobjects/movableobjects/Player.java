package bomberman.Gameobjects.movableobjects;

import bomberman.Gameobjects.Bomb;
import bomberman.grid.Grid;
import bomberman.grid.GridColor;
import bomberman.grid.GridDirection;
import bomberman.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Player extends MovableObjects implements KeyboardHandler{

    private Bomb bomb;
    private int power=1;






        //event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        //k.addEventListener(event);

    public Player(GridPosition pos)  {
        super(pos);
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

        k.addEventListener(event);
        k.addEventListener(event1);
        k.addEventListener(event2);
        k.addEventListener(event3);

    }


    public void releaseBomb(){

    }




    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        System.out.println("key pressed1");
        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_LEFT:
                System.out.println("key pressed1");
                getPos().moveInDirection(GridDirection.LEFT,1);
                break;
            case KeyboardEvent.KEY_RIGHT:
                getPos().moveInDirection(GridDirection.RIGHT,1);
                break;
            case KeyboardEvent.KEY_DOWN:
                getPos().moveInDirection(GridDirection.DOWN,1);
                break;
            case KeyboardEvent.KEY_UP:
                getPos().moveInDirection(GridDirection.UP,1);
                break;

            default:break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
