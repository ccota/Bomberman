package bomberman.Gameobjects.movableobjects.enemys;

import bomberman.Gameobjects.movableobjects.MovableObjects;
import bomberman.grid.GridDirection;
import bomberman.grid.position.GridPosition;

abstract public class Enemy extends MovableObjects {

    private GridDirection currentDirection;

    public Enemy(GridPosition pos) {
        super(pos);
        currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
    }

    public void move() {
        chooseDirection();

        switch (currentDirection) {
            case LEFT:
                getPos().moveInDirection(GridDirection.LEFT,1);
                break;
            case RIGHT:
                getPos().moveInDirection(GridDirection.RIGHT,1);
                break;
            case UP:
                getPos().moveInDirection(GridDirection.UP,1);
                break;
            case DOWN:
                getPos().moveInDirection(GridDirection.DOWN,1);
                break;
        }
    }


    public void /*GridDirection*/ chooseDirection() {


        int randomNumber = (int) (Math.random()*100);

        if(randomNumber>=80){
            currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
        }



    }


}
