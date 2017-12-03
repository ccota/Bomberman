package bomberman.Gameobjects.movableobjects.enemys;

import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

public class EnemyBeing extends Enemy {
    public EnemyBeing(GridPosition pos) {
        super(pos);
        pos.setColor(GridColor.MAGENTA);
    }

}
