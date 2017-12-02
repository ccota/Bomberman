package bomberman;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class StageSelection {

    private Rectangle menuBackground;

    public static final int PADDING = 10;
    private int cols = 25;
    private int rows = 15;
    private int cellSize = 40;
    private int height;
    private int width;
    private int menuHeight;
    private int menuWidth;

    StageSelection(){
        this.height = rows * cellSize;
        this.width = cols * cellSize;
        this.menuHeight = height / 2;
        this.menuWidth = width / 2;
        menuBackground = new Rectangle(PADDING, PADDING, width, height);
        menuBackground.draw();

    }


    public void launchOptions(){

    }
}
