package bomberman.Gameobjects.movableobjects;

import bomberman.CollisionDetector;
import bomberman.Gameobjects.GameObjects;
import bomberman.grid.position.GridPosition;

abstract public class MovableObjects extends GameObjects {
    protected CollisionDetector collisionDetector;


    public MovableObjects(GridPosition pos) {
        super(pos);
    }


    public void setColisionDetector(CollisionDetector collisionDetector){
        this.collisionDetector = collisionDetector;
    }
}
