package bomberman;

import bomberman.Gameobjects.Bomb;
import bomberman.Gameobjects.GameObjects;
import bomberman.Gameobjects.blocks.Blocks;
import bomberman.Gameobjects.blocks.HardBlock;
import bomberman.Gameobjects.blocks.SoftBlock;
import bomberman.Gameobjects.gameitems.GameItems;
import bomberman.Gameobjects.movableobjects.enemys.Enemy;
import bomberman.grid.GridDirection;
import bomberman.utilities.Random;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CollisionDetector {

    private ArrayList <GameObjects> objects;
    private Picture[] explosionsArray = new Picture[100];

    public CollisionDetector(ArrayList<GameObjects> objects) {
        this.objects = objects;
    }

    public boolean isUnSafe(int col, int row) {

        for (int c = 0; c < objects.size(); c++) {

            if ((objects.get(c).getPos().getCol() == col && objects.get(c).getPos().getRow() == row) && !objects.get(c).isDestroyed()) {
                if (objects.get(c) instanceof GameItems){
                   // return false;
                }
                return true;
            }
        }
        return false;
    }

    public GridDirection getSafeDirection(GridDirection prevDirection, int col, int row){

        GridDirection newDirection = prevDirection;
      switch (prevDirection){

          case UP:
              if (!isUnSafe(col , row - 1)){
                  return GridDirection.UP;
              }
              break;
          case DOWN:
              if (!isUnSafe(col , row + 1)){
                  return GridDirection.DOWN;
              }
              break;
          case LEFT:
              if (!isUnSafe(col - 1, row)){
                  return GridDirection.LEFT;
              }
              break;
          case RIGHT:
              if (!isUnSafe(col +1, row)){
                  return GridDirection.RIGHT;
              }
              break;
      }

        return newDirection;
    }





    public void destroyObjects(Bomb bomb, Game game) {


        boolean blockUP = false;
        boolean blockDOWN = false;
        boolean blockLEFT = false;
        boolean blockRIGHT = false;

        generateExplosionImg(bomb.getPos().getCol(), bomb.getPos().getRow(), bomb);

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
                if (!(o instanceof HardBlock)) {
                    generateExplosionImg(bomb.getPos().getCol(), bomb.getPos().getRow() + 1, bomb);
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
                if (!(o instanceof HardBlock)) {
                    generateExplosionImg(bomb.getPos().getCol(), bomb.getPos().getRow() - 1, bomb);
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
                if (!(o instanceof HardBlock)) {
                    generateExplosionImg(bomb.getPos().getCol() + 1, bomb.getPos().getRow(), bomb);
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
                if (!(o instanceof HardBlock)) {
                    generateExplosionImg(bomb.getPos().getCol() - 1, bomb.getPos().getRow(), bomb);
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
        for (int p = 1; p <= bomb.getPower(); p++) {

            if ( (!blockDOWN && ((bomb.getPos().getRow() +p) < bomb.getPos().getMaxRows() )) ) {
                generateExplosionImg(bomb.getPos().getCol(), bomb.getPos().getRow() + p, bomb);
            }
            if (!blockUP && ((bomb.getPos().getRow() - p) > 0) ) {
                generateExplosionImg(bomb.getPos().getCol(), bomb.getPos().getRow() - p, bomb);
            }
            if (!blockRIGHT && ((bomb.getPos().getCol() +p) <  bomb.getPos().getMaxCols() )) {
                generateExplosionImg(bomb.getPos().getCol() + p, bomb.getPos().getRow(), bomb);
            }
            if (!blockLEFT && ((bomb.getPos().getCol() - p) > 0 )) {
                generateExplosionImg(bomb.getPos().getCol() - p, bomb.getPos().getRow(), bomb);
            }
        }

        for(GameObjects o : objects){
            if ( !(o instanceof Blocks)) {
                for (int p = 1; p <= bomb.getPower() ; p++) {
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) + p)) {
                        if (!o.isDestroyed() && !blockDOWN) {
                            generateExplosionImg(bomb.getPos().getCol(), bomb.getPos().getRow() + p, bomb);
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol()) && (o.getPos().getRow() == (bomb.getPos().getRow()) - p)) {
                        if (!o.isDestroyed() && !blockUP) {
                            generateExplosionImg(bomb.getPos().getCol(), bomb.getPos().getRow() - p, bomb);
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() + p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed() && !blockRIGHT) {
                            generateExplosionImg(bomb.getPos().getCol() + p, bomb.getPos().getRow(), bomb);
                        }
                    }
                    if ((o.getPos().getCol() == bomb.getPos().getCol() - p) && (o.getPos().getRow() == (bomb.getPos().getRow()))) {
                        if (!o.isDestroyed() && !blockLEFT){
                            generateExplosionImg(bomb.getPos().getCol() - p, bomb.getPos().getRow(), bomb);
                        }
                    }
                }
            }
        }
        clearExplosion();

    }

    private void clearExplosion(){


        TimerTask timerTask =   new TimerTask() {


            @Override
            public void run() {
                for (int i = 0; i < explosionsArray.length; i++) {
                    if (explosionsArray[i] == null){
                        break;
                    }
                    explosionsArray[i].delete();
                    explosionsArray[i] = null;
                }
            }
        };

        Timer timer = new Timer();

        timer.schedule(timerTask,500);


    }

    private void generateExplosionImg(int col, int row, Bomb bomb){

        int x = bomb.getPos().getPadding() + bomb.getPos().getCellSize() * col;
        int y = bomb.getPos().getPadding() + bomb.getPos().getCellSize() * row;

        Picture picture = new Picture(x ,y , "explosion.png");
        for (int i = 0; i < explosionsArray.length; i++) {
            if (explosionsArray[i] == null){
                explosionsArray[i] = picture;
                break;
            }
        }
        picture.draw();
        /*
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        picture.delete();

        TimerTask timerTask =   new TimerTask() {



            @Override
            public void run() {
               picture.delete();

            }
        };

        Timer timer = new Timer();

        timer.schedule(timerTask,500);
*/


    }





}
