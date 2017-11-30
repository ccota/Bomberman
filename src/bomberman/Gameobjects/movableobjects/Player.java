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
import java.util.Timer;
import java.util.TimerTask;

public class Player extends MovableObjects implements KeyboardHandler{



    private Bomb bomb;
    private int power=1;
    private int bombCapacty = 1;
    private int bombCurrent = 1;






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

        KeyboardEvent event4 = new KeyboardEvent();
        event4.setKey(KeyboardEvent.KEY_SPACE);
        event4.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        k.addEventListener(event);
        k.addEventListener(event1);
        k.addEventListener(event2);
        k.addEventListener(event3);
        k.addEventListener(event4);

    }



    public void releaseBomb(){

        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("entrou");
                getPos().hide();
                //explodir a bombinha

            }
        };

        Timer timer = new Timer();

        timer.schedule(myTimerTask,2000);

        System.out.println("BOOOOM");
        Bomb bomb = new Bomb(Game.getGrid().makeGridPosition(getPos().getCol(),getPos().getRow()));


        //game.makeBomb(0,0);
       // bombCurrAmount--;
       // Bomb bomb = new Bomb(power, this);
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
            case KeyboardEvent.KEY_SPACE:
                releaseBomb();
                break;

            default:break;

        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
