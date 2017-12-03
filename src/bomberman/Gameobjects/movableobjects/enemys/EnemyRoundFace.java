package bomberman.Gameobjects.movableobjects.enemys;

import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

public class EnemyRoundFace extends Enemy {
    public EnemyRoundFace(GridPosition pos) {
        super(pos);
        pos.setColor(GridColor.MAGENTA);
    }

}
