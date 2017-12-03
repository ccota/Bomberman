package bomberman.Gameobjects.movableobjects;

import bomberman.CollisionDetector;
import bomberman.Gameobjects.GameObjects;
import bomberman.ItemDetector;
import bomberman.grid.position.GridPosition;

abstract public class MovableObjects extends GameObjects {
    protected CollisionDetector collisionDetector;
    protected ItemDetector itemDetector;


    public MovableObjects(GridPosition pos) {
        super(pos);
    }


    public void setColisionDetector(CollisionDetector collisionDetector){
        this.collisionDetector = collisionDetector;
    }

    public void setItemDetector(ItemDetector itemDetector) {
        this.itemDetector = itemDetector;
    }
}
