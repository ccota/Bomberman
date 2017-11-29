package bomberman.Gameobjects.movableobjects;

import bomberman.Gameobjects.GameObjects;
import bomberman.grid.position.GridPosition;

abstract public class MovableObjects extends GameObjects {
    public MovableObjects(GridPosition pos) {
        super(pos);
    }
}
