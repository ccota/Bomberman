package bomberman;

import bomberman.grid.Grid;
import jdk.nashorn.internal.ir.Block;

public class MapFactory {

        //private Block[] hardBlockArray;
        public void populateMap(Grid grid){

            for (int i = 1; i<grid.getCols(); i +=2){

                for (int j = 1 ; j < grid.getRows(); j += 2){
                    grid.makeGridPosition( i, j);

                }
            }


        }
}
