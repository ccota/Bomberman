package bomberman;

import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.Gameobjects.gameitems.ExtraBomb;

import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.gameitems.PowerUp;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.Gameobjects.movableobjects.enemys.Faustino;
import bomberman.grid.Grid;
import bomberman.utilities.Random;


public class Factory {
    private enum Itemtype {
        BOMB,
        POWER;


        public static Itemtype random(){
            int randomShoot = Random.generate(Itemtype.values().length);
            return Itemtype.values()[randomShoot];

        }
    }

    // To do later passar para enum blockos
    /** Hard Block */
    public HardBlock hardBlocks(Grid grid,int x, int y){

        return new HardBlock(grid.makeGridPosition(x,y,"hardBlock1.gif"));
    }

    /** Soft Block */
    public SoftBlock softBlocks(Grid grid, int x, int y){
        return new SoftBlock(grid.makeGridPosition(x,y,"softBlock1.png"));
    }

    /** Player */
    public Player generatePlayer (Grid grid, int x, int y, Game game){
        return new Player(grid.makeGridPosition(x,y,"player.gif"), game);
    }

    /**Enemies */
    public Faustino generateEnemies (Grid grid, int x, int y){
        return new Faustino(grid.makeGridPosition(x, y,"enemigo3.png"));
    }



//http://randomhoohaas.flyingomelette.com/bomb/mob/2014/game.html
    /**Items  ---FIX THIS LATER*/
    public static GameItems generateRandomItem (int x, int y){

        //GameItems myitem;
        switch (Itemtype.random()){
            case BOMB:
                return new ExtraBomb(Game.getGrid().makeGridPosition(x,y,"item_bomb.png"));
               // myitem = new ExtraBomb(Game.getGrid().makeGridPosition(x,y,"item_bomb.png"));
                //break;
            case POWER:
                return new PowerUp(Game.getGrid().makeGridPosition(x,y,"item_power.png"));
                //myitem = new PowerUp(Game.getGrid().makeGridPosition(x,y,""));
                //break;
        }
        return null;

    }



    /**Bombs */
    /*public Bomb generateBombs (Grid grid, int x, int y, CollisionDetector collisionDetector){
        return new Bomb(grid.makeGridPosition(x,y), collisionDetector);
    }*/



}


