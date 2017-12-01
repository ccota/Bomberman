package bomberman.Gameobjects;

import bomberman.CollisionDetector;
import bomberman.Game;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.gfx.simpleGfx.SimpleGfxGrid;
import bomberman.gfx.simpleGfx.SimpleGfxGridPosition;
import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends GameObjects {
    private Player player;
    protected CollisionDetector collisionDetector;
    private boolean isActive = true;

    public Bomb (GridPosition pos, CollisionDetector collisionDetector,Player player){
        super(pos);
        this.player=player;
        this.collisionDetector = collisionDetector;

        //pos.setColor(GridColor.RED);
        explode();

    }

    public void explode() {


        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {
                // Turns destroyed true
                setDestroyed();


                destroyObjects();
                player.increseCurrentBomb();
                System.out.println("BOOOOM");
            }
        };

        Timer timer = new Timer();

        timer.schedule(myTimerTask,3000);

    }

    private void destroyObjects() {
        collisionDetector.destroyObjects(this, player.getGame());
    }

    public void setActive() {
        isActive = false;

    }

    public boolean isActive() {
        return isActive;
    }
}
