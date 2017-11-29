package bomberman;

import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.grid.Grid;

public class Factory {





    public HardBlock hardBlocks(Grid grid,int x, int y){

        return new HardBlock(grid.makeGridPosition(x,y));


    }


}


