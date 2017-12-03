package bomberman.Gameobjects.blocks;

import bomberman.Gameobjects.blocks.Blocks;
import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

public class SoftBlock extends Blocks {
    private boolean destroyed; // blocks are destroyed after being hit by a bomb

    public SoftBlock(GridPosition pos){
        super(pos, BlockType.SOFTBLOCK);
        //pos.setColor(GridColor.GREEN);
    }



}
