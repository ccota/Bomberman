package bomberman.Gameobjects.blocks;

import bomberman.Gameobjects.blocks.Blocks;
import bomberman.Position;
import bomberman.gfx.simpleGfx.SimpleGfxGridPosition;
import bomberman.grid.position.GridPosition;

public class HardBlock extends Blocks {

    public HardBlock(GridPosition pos){
        super(pos, BlockType.HARDBLOCK);
    }


}
