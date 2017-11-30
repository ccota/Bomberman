package bomberman.Gameobjects.movableobjects.enemys;

import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

public class Faustino extends Enemy {
    public Faustino(GridPosition pos) {
        super(pos);
        pos.setColor(GridColor.MAGENTA);
    }
}
