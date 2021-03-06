package bomberman.Gameobjects.movableobjects.enemys;

import bomberman.Gameobjects.movableobjects.MovableObjects;
import bomberman.grid.GridDirection;
import bomberman.grid.position.GridPosition;

abstract public class Enemy extends MovableObjects {

    private GridDirection currentDirection;

    public Enemy(GridPosition pos) {
        super(pos);
        chooseDirection();
    }

    public void move() {

        if (isDestroyed()) {
            return;
        }

        chooseDirection();

        switch (currentDirection) {
            case LEFT:
                if (!collisionDetector.isUnSafe(getPos().getCol() - 1, getPos().getRow())) {
                    getPos().moveInDirection(GridDirection.LEFT, 1);
                    break;
                }
                break;
            case RIGHT:
                if (!collisionDetector.isUnSafe(getPos().getCol() + 1, getPos().getRow())) {
                    getPos().moveInDirection(GridDirection.RIGHT, 1);
                    break;
                }
                break;
            case UP:
                if (!collisionDetector.isUnSafe(getPos().getCol(), getPos().getRow() - 1)) {
                    getPos().moveInDirection(GridDirection.UP, 1);
                    break;
                }
                break;
            case DOWN:
                if (!collisionDetector.isUnSafe(getPos().getCol(), getPos().getRow() + 1)) {
                    getPos().moveInDirection(GridDirection.DOWN, 1);
                    break;
                }
                break;
            default:
        }
    }


    @Override
    public void setDestroyed() {

        super.setDestroyed();
    }

    public void  chooseDirection() {
        if (currentDirection == null){
            currentDirection = GridDirection.DOWN;
        }
        int randomNumber = (int) (Math.random() * 100);

        if (randomNumber >= 30) {
            currentDirection = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
        }
    }


}
