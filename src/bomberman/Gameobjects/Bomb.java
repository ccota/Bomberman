package bomberman.Gameobjects;

import bomberman.CollisionDetector;
import bomberman.Game;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.grid.position.GridPosition;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends GameObjects {
    private Player player;
    private int power;

    protected CollisionDetector collisionDetector;

    public Bomb (GridPosition pos, CollisionDetector collisionDetector,Player player){
        super(pos);
        this.player=player;
        this.collisionDetector = collisionDetector;
        this.power = player.getPower();

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
                player.increaseCurrentBomb();
                System.out.println("BOOOOM");
            }
        };

        Timer timer = new Timer();

        timer.schedule(myTimerTask,3000);

    }

    private void destroyObjects() {
        collisionDetector.destroyObjects(this, player.getGame());
    }

    public int getPower() {
        return power;
    }
}
