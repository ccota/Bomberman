package bomberman.Gameobjects.blocks;

import bomberman.Gameobjects.GameObjects;
import bomberman.gfx.simpleGfx.SimpleGfxGrid;
import bomberman.grid.position.GridPosition;

abstract public class Blocks extends GameObjects {
    private BlockType blockType;

    public Blocks (GridPosition pos, BlockType blockType){
        super(pos);
        this.blockType = blockType;
    }

        // cores ou padr\oes bla bla



    /*Only soft blocks need to be destroyed. No need to inherit.
    public void setDestroyed() {
        isDestroyed = true ;
    }*/


    public BlockType getBlockType() {
        return blockType;
    }

}
