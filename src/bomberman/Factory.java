package bomberman;

import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.grid.Grid;

public class Factory {


    // To do later passar para enum blockos
    /** Hard Block */
    public HardBlock hardBlocks(Grid grid,int x, int y){

        return new HardBlock(grid.makeGridPosition(x,y));
    }

    /** Hard Block */
    public SoftBlock softBlocks(Grid grid, int x, int y){
        return new SoftBlock(grid.makeGridPosition(x,y));
    }




}


