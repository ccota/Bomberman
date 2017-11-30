package bomberman.Gameobjects;

import bomberman.CollisionDetector;
import bomberman.gfx.simpleGfx.SimpleGfxGrid;
import bomberman.gfx.simpleGfx.SimpleGfxGridPosition;
import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends GameObjects {

    protected CollisionDetector collisionDetector;

    public Bomb (GridPosition pos, CollisionDetector collisionDetector){

        super(pos);
        pos.setColor(GridColor.RED);
        this.collisionDetector = collisionDetector;




        //game.makeBomb(0,0);
        // bombCurrAmount--;
        // Bomb bomb = new Bomb(power, this);

    }

    public void explode() {
        TimerTask myTimerTask = new TimerTask() {
            @Override

            public void run() {
                System.out.println("entrou");
                setDestroyed();



                //Destroy objects

                destroyObjects();








            }
        };

        Timer timer = new Timer();

        timer.schedule(myTimerTask,3000);

        System.out.println("BOOOOM");
    }

    private void destroyObjects() {
        collisionDetector.destroyObjects(this);
    }
}
