package bomberman.gfx.simpleGfx;

import bomberman.grid.Grid;
import bomberman.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class SimpleGfxGrid implements Grid {

    public static final int PADDING = 10;

    public static Rectangle rectangle;

    private int cols;
    private int rows;
    private int height;
    private int width;
    private int x;
    private int y;
    private int cellSize = 40;



    public SimpleGfxGrid(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
        this.height = rows * cellSize;
        this.width = cols * cellSize;
        this.y = PADDING;
        this.x  = PADDING;
    }

    /**
     * @see Grid#init()
     */
    @Override
    public void init() {

        rectangle = new Rectangle(PADDING,PADDING, width,height);
        rectangle.draw();





    }

    /**
     * @see Grid#getCols()
     */
    @Override
    public int getCols() {
        return cols;
    }

    /**
     * @see Grid#getRows()
     */
    @Override
    public int getRows() {
       return rows;
    }

    /**
     * Obtains the width of the grid in pixels
     * @return the width of the grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * Obtains the height of the grid in pixels
     * @return the height of the grid
     */
    public int getHeight() {
        return height;
    }

    /**
     * Obtains the grid X position in the SimpleGFX canvas
     * @return the x position of the grid
     */
    public int getX() {
        return x;
    }

    /**
     * Obtains the grid Y position in the SimpleGFX canvas
     * @return the y position of the grid
     */
    public int getY() {
        return y;
    }

    /**
     * Obtains the pixel width and height of a grid position
     * @return
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * @see Grid#makeGridPosition()
     */
    @Override
    public GridPosition makeGridPosition() {
        return new SimpleGfxGridPosition(this);
    }

    /**
     * @see Grid#makeGridPosition(int, int, String) (int, int)
     */
    @Override
    public GridPosition makeGridPosition(int col, int row, String img) {

        return  new SimpleGfxGridPosition(col, row, this,img);
    }

    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     * @param row index
     * @return y pixel value
     */
    public int rowToY(int row) {
        return PADDING+ cellSize*row;
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     * @param column index
     * @return x pixel value
     */
    public int columnToX(int column) {
        return PADDING + cellSize*column;
    }
}
