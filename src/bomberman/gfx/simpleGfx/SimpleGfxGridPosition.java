package bomberman.gfx.simpleGfx;

import bomberman.grid.GridColor;
import bomberman.grid.GridDirection;
import bomberman.grid.position.AbstractGridPosition;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import bomberman.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private Rectangle rectangle;
    private Picture picture;
    private SimpleGfxGrid simpleGfxGrid;

    /**
     * Simple graphics position constructor
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(SimpleGfxGrid grid){
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);
        this.simpleGfxGrid = grid;
        this.rectangle = new Rectangle(this.simpleGfxGrid.columnToX(getCol()),this.simpleGfxGrid.rowToY(getRow()),this.simpleGfxGrid.getCellSize(),this.simpleGfxGrid.getCellSize());


   // show();
    }

    /**
     * Simple graphics position constructor
     * @param col position column
     * @param row position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, SimpleGfxGrid grid, String img){
        super(col, row, grid);
        simpleGfxGrid = grid;
        //rectangle = new Rectangle(grid.columnToX(col) , grid.rowToY(row), grid.getCellSize(), grid.getCellSize());
        //rectangle.draw();
        this.picture= new Picture(grid.columnToX(col), grid.rowToY(row),img);
        picture.draw();
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        //rectangle.fill();
        //picture.draw();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {
       // rectangle.delete();
        picture.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {
        int prevRow = getRow();
        int prevCol = getCol();
        super.moveInDirection(direction, distance);

        picture.translate((getCol() - prevCol)*simpleGfxGrid.getCellSize(), (getRow() - prevRow)*simpleGfxGrid.getCellSize());


    }



    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {
        //super.setColor(color);
        //rectangle.setColor(SimpleGfxColorMapper.getColor(color));

    }
}
