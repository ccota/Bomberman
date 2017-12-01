package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.Gameobjects.movableobjects.enemys.Faustino;
import bomberman.grid.Grid;
import bomberman.grid.position.GridPosition;

public class Factory {


    // To do later passar para enum blockos
    /** Hard Block */
    public HardBlock hardBlocks(Grid grid,int x, int y){

        return new HardBlock(grid.makeGridPosition(x,y,"hardBlock.png"));
    }

    /** Soft Block */
    public SoftBlock softBlocks(Grid grid, int x, int y){
        return new SoftBlock(grid.makeGridPosition(x,y,"softBlock.png"));
    }

    /** Player */
    public Player generatePlayer (Grid grid, int x, int y, Game game){
        return new Player(grid.makeGridPosition(x,y,"player.gif"), game);
    }

    /**Enemies */
    public Faustino generateEnemies (Grid grid, int x, int y){
        return new Faustino(grid.makeGridPosition(x, y,"sapo.gif"));
    }

    /**Bombs */
    /*public Bomb generateBombs (Grid grid, int x, int y, CollisionDetector collisionDetector){
        return new Bomb(grid.makeGridPosition(x,y), collisionDetector);
    }*/



}


