package bomberman.Gameobjects;

import bomberman.gfx.simpleGfx.SimpleGfxGrid;
import bomberman.gfx.simpleGfx.SimpleGfxGridPosition;
import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends GameObjects {

    public Bomb (GridPosition pos){
        super(pos);
        pos.setColor(GridColor.GREEN);

       TimerTask myTimerTask = new TimerTask() {
            @Override

            public void run() {
                System.out.println("entrou");
                getPos().hide();
                setDestroyed();
                //explodir a bombinha

            }
        };

        Timer timer = new Timer();

        timer.schedule(myTimerTask,3000);

        System.out.println("BOOOOM");


        //game.makeBomb(0,0);
        // bombCurrAmount--;
        // Bomb bomb = new Bomb(power, this);

    }
}
