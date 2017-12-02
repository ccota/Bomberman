package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.blocks.Blocks;
import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.Grid;
import bomberman.grid.position.GridPosition;
import bomberman.utilities.Random;

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

                   //return false;
                }
                return true;
            }

        }

        return false;

    }


    public void destroyObjects(Bomb bomb, Game game) {


        for (GameObjects o : objects) {
            int generateItemPercent = 100; //Random.generate(100);

            if ( !(o instanceof Blocks)) {
                for (int p = 1; p <= bomb.getPower() ; p++) {
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) + p)) {
                        if (!o.isDestroyed()){
                            o.setDestroyed();
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) - p)) {
                        if (!o.isDestroyed()){
                            o.setDestroyed();
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() + p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed()){
                            o.setDestroyed();
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() + p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed()){
                            o.setDestroyed();
                        }
                    }
                }
            }


            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == (bomb.getPos().getRow())) {
                o.setDestroyed();
            }

            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == (bomb.getPos().getRow() + 1)) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
                o.setDestroyed();
            }
            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == (bomb.getPos().getRow() - 1)) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
                o.setDestroyed();
            }
            if ((o.getPos().getCol() == bomb.getPos().getCol() + 1) && o.getPos().getRow() == (bomb.getPos().getRow())) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
                o.setDestroyed();
            }
            if ((o.getPos().getCol() == bomb.getPos().getCol() - 1) && o.getPos().getRow() == (bomb.getPos().getRow())) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
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
    public GameItems hasItem(int col, int row){
        for (GameObjects o : objects){
            if ((o.getPos().getCol() == col && o.getPos().getRow() == row) && o instanceof GameItems && !o.isDestroyed()){
                o.setDestroyed();
                return (GameItems) o;
            }
        }
        return null;
    }





}
