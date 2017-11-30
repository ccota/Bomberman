package bomberman.Gameobjects.movableobjects.enemys;

import bomberman.Gameobjects.movableobjects.MovableObjects;
import bomberman.grid.position.GridPosition;

abstract public class Enemy extends MovableObjects {
    public Enemy(GridPosition pos) {
        super(pos);
    }
}
