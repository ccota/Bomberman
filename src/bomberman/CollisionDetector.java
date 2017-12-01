package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.utilities.Random;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.gameitems.PowerUp;

import java.util.ArrayList;


public class CollisionDetector {

    private ArrayList <GameObjects> objects;

    public CollisionDetector(ArrayList<GameObjects> objects) {
        this.objects = objects;
    }

    public boolean isUnSafe(int col, int row) {

        for (GameObjects c : objects) {

            if ((c.getPos().getCol() == col && c.getPos().getRow() == row) && !c.isDestroyed()) {
                if (c instanceof GameItems){
                    return false;
                }
                return true;
            }

        }

        return false;

    }


    public void destroyObjects(Bomb bomb, Game game) {
        /*for (int i =0; i <objects.size(); i++) {

            if (objects[i] instanceof SoftBlock) {

            }

        }*/

        for (GameObjects o : objects){
            int randomItemCalculator = bomberman.utilities.Random.generate(100);
            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == ( bomb.getPos().getRow() + 1) ){
                o.setDestroyed();
                if (o instanceof SoftBlock && randomItemCalculator >=80){
                    objects.add(Factory.generateRandomItem(o.getPos().getCol(),o.getPos().getRow()));
                }

            }
            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == ( bomb.getPos().getRow() - 1) ){
                o.setDestroyed();
                if (o instanceof SoftBlock && randomItemCalculator >=80){
                    objects.add(Factory.generateRandomItem(o.getPos().getCol(),o.getPos().getRow()));
                }
            }
            if (( o.getPos().getCol() == bomb.getPos().getCol() +1) && o.getPos().getRow() == ( bomb.getPos().getRow()) ){
                if (o instanceof SoftBlock && randomItemCalculator >=80){
                    objects.add(Factory.generateRandomItem(o.getPos().getCol(),o.getPos().getRow()));
                }
            }
            if ((o.getPos().getCol() == bomb.getPos().getCol() -1) && o.getPos().getRow() == ( bomb.getPos().getRow()) ){
                if (o instanceof SoftBlock && randomItemCalculator >=80){
                    objects.add(Factory.generateRandomItem(o.getPos().getCol(),o.getPos().getRow()));
                }
            }
        }



    }
}
