package bomberman.Gameobjects.blocks;

import bomberman.Gameobjects.GameObjects;
import bomberman.grid.position.GridPosition;

abstract public class Blocks extends GameObjects {
    private GridPosition pos;
    private BlockType blockType;
    private boolean isDestroyed;

    public Blocks (GridPosition pos, BlockType blockType){
        this.pos = pos;
        this.blockType = blockType;

        // cores ou padr\oes bla bla

    }




}
