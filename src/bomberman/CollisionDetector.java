package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.blocks.Blocks;
import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.movableobjects.Player;
import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.Grid;
import bomberman.grid.position.GridPosition;
import bomberman.utilities.Random;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CollisionDetector {

    private ArrayList <GameObjects> objects;

    public CollisionDetector(ArrayList<GameObjects> objects) {
        this.objects = objects;
    }

    public boolean isUnSafe(int col, int row) {

        for (GameObjects c : objects) {

            if ((c.getPos().getCol() == col && c.getPos().getRow() == row) && !c.isDestroyed()) {
                if (c instanceof GameItems){
                   // return false;
                }
                return true;
            }

        }

        return false;

    }


    public void destroyObjects(Bomb bomb, Game game) {


        boolean blockUP = false;
        boolean blockDOWN = false;
        boolean blockLEFT = false;
        boolean blockRIGHT = false;

        generateExplosion(bomb.getPos().getCol(), bomb.getPos().getRow(), bomb);

        for (GameObjects o : objects) {
            int generateItemPercent = Random.generate(100);

            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == (bomb.getPos().getRow())) {
                o.setDestroyed();
            }
            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == (bomb.getPos().getRow() + 1)) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
                if (o instanceof Blocks && !o.isDestroyed()) {
                    blockDOWN = true;
                }
                o.setDestroyed();
            }
            if (o.getPos().getCol() == bomb.getPos().getCol() && o.getPos().getRow() == (bomb.getPos().getRow() - 1)) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
                if (o instanceof Blocks && !o.isDestroyed()) {
                    blockUP = true;
                }
                o.setDestroyed();
            }
            if ((o.getPos().getCol() == bomb.getPos().getCol() + 1) && o.getPos().getRow() == (bomb.getPos().getRow())) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
                if (o instanceof Blocks && !o.isDestroyed()) {
                    blockRIGHT = true;
                }
                o.setDestroyed();
            }
            if ((o.getPos().getCol() == bomb.getPos().getCol() - 1) && o.getPos().getRow() == (bomb.getPos().getRow())) {
                if (o instanceof SoftBlock && generateItemPercent >= 84 && !o.isDestroyed()) {
                    game.addItem(Factory.generateRandomItem(o.getPos().getCol(), o.getPos().getRow()));
                }
                if (o instanceof Blocks && !o.isDestroyed()) {
                    blockLEFT = true;
                }
                o.setDestroyed();
            }
        }
        for(GameObjects o : objects){
                for (int p = 1; p <= bomb.getPower() ; p++) {
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) + p)) {

                        if (!o.isDestroyed() && !blockDOWN) {
                            o.setDestroyed();
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) - p)) {
                        if (!o.isDestroyed() && !blockUP) {
                            o.setDestroyed();
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() + p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed() && !blockRIGHT) {
                            o.setDestroyed();
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() - p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed() && !blockLEFT){
                            o.setDestroyed();
                        }
                    }
                }
            }

        explosionInit(bomb, blockDOWN,blockUP,blockRIGHT,blockLEFT);


    }



    public boolean hasEnemy(int col, int row){
        for (GameObjects o : objects){
            if ((o.getPos().getCol() == col && o.getPos().getRow() == row) && o instanceof Enemy && !o.isDestroyed()){

                return true;
            }
        }
        return false;
    }

    private void explosionInit(Bomb bomb, boolean blockDOWN, boolean blockUP, boolean blockRIGHT, boolean blockLEFT){
        for (int p = 1; p < bomb.getPower(); p++) {

            if ( (!blockDOWN && ((bomb.getPos().getRow() +p) < bomb.getPos().getMaxRows() )) ) {
                generateExplosion(bomb.getPos().getCol(), bomb.getPos().getRow() + p, bomb);
            }
            if (!blockUP && ((bomb.getPos().getRow() - p) > 0) ) {
                generateExplosion(bomb.getPos().getCol(), bomb.getPos().getRow() - p, bomb);
            }
            if (!blockRIGHT && ((bomb.getPos().getCol() +p) <  bomb.getPos().getMaxCols() )) {
                generateExplosion(bomb.getPos().getCol() + p, bomb.getPos().getRow(), bomb);
            }
            if (!blockLEFT && ((bomb.getPos().getCol() - p) > 0 )) {
                generateExplosion(bomb.getPos().getCol() - p, bomb.getPos().getRow(), bomb);
            }
        }

        for(GameObjects o : objects){
            if ( !(o instanceof Blocks)) {
                for (int p = 1; p <= bomb.getPower() ; p++) {
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) + p)) {
                        if (!o.isDestroyed() && !blockDOWN) {
                            generateExplosion(bomb.getPos().getCol(), bomb.getPos().getRow() + p, bomb);
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) - p)) {
                        if (!o.isDestroyed() && !blockUP) {
                            generateExplosion(bomb.getPos().getCol(), bomb.getPos().getRow() - p, bomb);
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() + p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed() && !blockRIGHT) {
                            generateExplosion(bomb.getPos().getCol() + p, bomb.getPos().getRow(), bomb);
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() - p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed() && !blockLEFT){
                            generateExplosion(bomb.getPos().getCol() - p, bomb.getPos().getRow(), bomb);
                        }
                    }
                }
            }
        }

    }


    private void generateExplosion (int col, int row, Bomb bomb){

        int x = bomb.getPos().getPadding() + bomb.getPos().getCellSize() * col;
        int y = bomb.getPos().getPadding() + bomb.getPos().getCellSize() * row;

        Picture picture = new Picture(x ,y , "explosion.png");
        picture.draw();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        picture.delete();
/*
        TimerTask timerTask =   new TimerTask() {



            @Override
            public void run() {
               picture.delete();

            }
        };

        Timer timer = new Timer();

        timer.schedule(timerTask,500); */



    }





}
