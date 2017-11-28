package bomberman;

import bomberman.grid.Grid;
import bomberman.grid.GridFactory;
import bomberman.grid.GridType;

public class Main {
    public static void main(String[] args) /* throws InterruptedException */{
        Game game = new Game(GridType.SIMPLE_GFX, 25, 15, 200);
        game.init();
        //Teste um dois, teste
    }
}
