package bomberman.Gameobjects;
import bomberman.grid.position.GridPosition;


public abstract class GameObjects {
    private boolean destroyed;
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

    public void WinGame(){
        destroyed = true;
        this.getPos().hide();
    }
}
