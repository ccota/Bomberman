package bomberman.Gameobjects.movableobjects;

import bomberman.Gameobjects.Bomb;
import bomberman.grid.position.GridPosition;

public class Player extends MovableObjects {

    private Bomb bomb;
    private int power;
    private GridPosition position;

    public Player(GridPosition position){
        this.position = position;
        this.bomb = new Bomb();
    }


    public void move(){

    }


    public void releaseBomb(){

    }



}
