package bomberman.Gameobjects.movableobjects;

import bomberman.Gameobjects.Bomb;
import bomberman.grid.position.GridPosition;

public class Player extends MovableObjects {

    private Bomb bomb;
    private int power;
    private GridPosition position;

    public Player(GridPosition pos, Bomb bomb, int power, GridPosition position) {
        super(pos);
        this.bomb = bomb;
        this.power = power;
        this.position = position;
    }


    public void move(){

    }


    public void releaseBomb(){

    }



}
