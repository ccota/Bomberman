package bomberman.Gameobjects.blocks;

import bomberman.Gameobjects.GameObjects;
import bomberman.grid.position.GridPosition;

abstract public class Blocks extends GameObjects {
    private BlockType blockType;

    public Blocks (GridPosition pos, BlockType blockType){
        super(pos);
        this.blockType = blockType;
    }



}
