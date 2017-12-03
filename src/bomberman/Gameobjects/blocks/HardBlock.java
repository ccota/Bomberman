package bomberman.Gameobjects.blocks;

import bomberman.grid.GridColor;
import bomberman.grid.position.GridPosition;

public class HardBlock extends Blocks {

    public HardBlock(GridPosition pos){
        super(pos, BlockType.HARDBLOCK);
        //pos.setColor(GridColor.RED);
    }

    @Override
    public void setDestroyed() {
        //this type of block never gets destroyed

    }
}
