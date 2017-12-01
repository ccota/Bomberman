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
    private boolean isActive = true;

    public Bomb (GridPosition pos, CollisionDetector collisionDetector){

        super(pos);
        pos.setColor(GridColor.RED);
        this.collisionDetector = collisionDetector;

    }

    public void explode() {


        TimerTask myTimerTask = new TimerTask() {
            @Override

            public void run() {
                setDestroyed();
                setActive();

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

    public void setActive() {
        isActive = false;

    }

    public boolean isActive() {
        return isActive;
    }
}
