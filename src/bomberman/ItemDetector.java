package bomberman;

import bomberman.Gameobjects.gameitems.GameItems;

import java.util.ArrayList;

public class ItemDetector {
    private ArrayList<GameItems> items;

    public ItemDetector(ArrayList<GameItems> items) {
        this.items = items;
    }

    public GameItems hasItem(int col, int row) {

        for (GameItems i : items) {

            if ((i.getPos().getCol() == col && i.getPos().getRow() == row) && !i.isDestroyed()) {
                i.setDestroyed();
                return i;

                    // return false;
                }

        }

        return null;

    }






}
