package bomberman;
import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.Gameobjects.gameitems.ExtraBomb;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.gameitems.PowerUp;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.Gameobjects.movableobjects.enemys.EnemyBeing;
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
    /** Hard Block */
    public HardBlock hardBlocks(Grid grid,int x, int y,String background){
        return new HardBlock(grid.makeGridPosition(x,y,background));
    }
    /** Soft Block */
    public SoftBlock softBlocks(Grid grid, int x, int y, String background){
        return new SoftBlock(grid.makeGridPosition(x,y,background));
    }
    /** Player */
    public Player generatePlayer (Grid grid, int x, int y, Game game){
        return new Player(grid.makeGridPosition(x,y,"player.gif"), game);
    }
    /**Enemies */
    public EnemyBeing generateEnemies (Grid grid, int x, int y, String background){
        return new EnemyBeing(grid.makeGridPosition(x, y,background));
    }

    public static GameItems generateRandomItem (int x, int y){

        switch (Itemtype.random()){
            case BOMB:
                return new ExtraBomb(Game.getGrid().makeGridPosition(x,y,"item_bomb.png"));
            case POWER:
                return new PowerUp(Game.getGrid().makeGridPosition(x,y,"item_power.png"));
        }
        return null;
    }
}