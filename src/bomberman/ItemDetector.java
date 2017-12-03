package bomberman;

import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.gameitems.GameItems;

import java.util.ArrayList;

public class ItemDetector {
    private ArrayList<GameItems> items;
    private ArrayList <GameObjects> objects;

    public ItemDetector(ArrayList<GameItems> items, ArrayList<GameObjects> objects) {
        this.items = items;
        this.objects = objects;
    }

    public GameItems hasItem(int col, int row) {

        for (GameItems i : items) {

            if ((i.getPos().getCol() == col && i.getPos().getRow() == row) && !i.isDestroyed()) {
                i.setDestroyed();
                return i;
                }

        }

        return null;

    }






}
