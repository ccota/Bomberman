package bomberman.Gameobjects;
import bomberman.grid.position.GridPosition;


public abstract class GameObjects {
    private boolean destroyed; //object is destroyed if hit by a bomb
    private GridPosition pos;

    public GameObjects(GridPosition pos) {
        this.pos = pos;
    }

    public void setDestroyed() {
        this.destroyed = true;
        this.getPos().hide();
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public GridPosition getPos() {
        return pos;
    }
}
