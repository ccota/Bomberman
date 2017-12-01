package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.Grid;
import bomberman.grid.position.GridPosition;

import java.util.ArrayList;

public class CollisionDetector {

    private ArrayList <GameObjects> objects;

    public CollisionDetector(ArrayList<GameObjects> objects) {
        this.objects = objects;
    }

    public boolean isUnSafe(int col, int row) {

        for (GameObjects c : objects) {

            if ((c.getPos().getCol() == col && c.getPos().getRow() == row) && !c.isDestroyed()) {
                return true;
            }

        }

        return false;

    }


    public void destroyObjects(Bomb bomb) {

        for (GameObjects o : objects){
            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == ( bomb.getPos().getRow() + 1) ){
                o.setDestroyed();
            }
            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == ( bomb.getPos().getRow() - 1) ){
                o.setDestroyed();
            }
            if (( o.getPos().getCol() == bomb.getPos().getCol() +1) && o.getPos().getRow() == ( bomb.getPos().getRow()) ){
                o.setDestroyed();
            }
            if ((o.getPos().getCol() == bomb.getPos().getCol() -1) && o.getPos().getRow() == ( bomb.getPos().getRow()) ){
                o.setDestroyed();
            }
        }
    }


    public boolean hasEnemy(int col, int row){
        for (GameObjects o : objects){
            if ((o.getPos().getCol() == col && o.getPos().getRow() == row) && o instanceof Enemy && !o.isDestroyed()){

                return true;
            }
        }
        return false;
    }




}
