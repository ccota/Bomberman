package bomberman.Gameobjects.gameitems;

import bomberman.Gameobjects.GameObjects;
import bomberman.gfx.simpleGfx.SimpleGfxGrid;
import bomberman.grid.position.GridPosition;

abstract public class GameItems extends GameObjects {

    public GameItems (GridPosition pos){
        super(pos);
    }

}
