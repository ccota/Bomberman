package bomberman;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Arrays;

public class Menu implements KeyboardHandler{
    private enum CurrentSelection {
        STARTGAME("bomb.png", "sapo.gif"),
        OPTIONS("bomb.png", "sapo.gif"),
        EXIT("bomb.png", "sapo.gif");
        private String unselected;
        private String selected;
        CurrentSelection(String unselected, String selected) {
            this.unselected = unselected;
            this.selected = selected;
        }
        public String getUnselected() {
            return unselected;
        }
        public String getSelected() {
            return selected;
        }

    }
    public static final int PADDING = 10;
    private int cols = 25;
    private int rows = 15;
    private int cellSize = 40;
    private int height;
    private int width;
    private int menuHeight;
    private int menuWidth;
    private Rectangle menuBackground;
    private Picture[][] arrayOfPictures;
    private int menuItemHeight = 70;
    private CurrentSelection currentSelection;
    private Keyboard keyboard;
  //  private String[] arrays = new String[] { "AA", "BBB", "CCC", "DD", "eee" ,"dsd"};
    public Menu() {
        currentSelection = CurrentSelection.STARTGAME;
        this.height = rows * cellSize;
        this.width = cols * cellSize;
        this.menuHeight = height / 2;
        this.menuWidth = width / 2;
    }

    public void launchMenu() {
        System.out.println("launchmenu: "+currentSelection.ordinal());
        menuBackground = new Rectangle(PADDING, PADDING, width, height);
        menuBackground.draw();

        arrayOfPictures = new Picture[3][2];

        for (int row = 0; row < arrayOfPictures.length; row++)
        {

            for (int column = 0; column < arrayOfPictures[row].length; column++)
            {
                System.out.println("row= "+ row +"colunm=" + column);

                String imgsrc = null;
                if ((column % 2) == 0) {
                    imgsrc= CurrentSelection.values()[row].getUnselected();


                } else {
                    imgsrc= CurrentSelection.values()[row].getSelected();


                }
                arrayOfPictures[row][column] = new Picture(
                        (menuWidth - menuWidth / 2), (menuHeight - menuHeight / 2) + (menuItemHeight * row),  imgsrc);

                if ((column % 2) == 0) {
                    arrayOfPictures[row][column].draw();
                }
            }
        }


            menuSelect();

        }

    public void menuSelect() {

        keyboard = new Keyboard(this);
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_UP);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_DOWN);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        KeyboardEvent event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_SPACE);
        event2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);
        keyboard.addEventListener(event1);
        keyboard.addEventListener(event2);
    }
    /* public boolean checkIfArrayAndEnumIsTheSame() {
         for (int i = 0; i < arrayTheRectangles.length; i++) {
             if (i == currentSelection.ordinal()) {
                 return true;
             }
         }
         return false;
     }
 */

    public boolean test (){
        return currentSelection.ordinal()>=0;
    }



    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_DOWN:




                /*int down = currentSelection.ordinal() + 1;
                if (down > CurrentSelection.values().length) {
                    break;
                }*/
                System.out.println("=========current up before add"+ currentSelection.ordinal() );
                int handler= currentSelection.ordinal() + 1;
                if (handler >= CurrentSelection.values().length) {
                    break;
                }
                System.out.println(handler);
                currentSelection = CurrentSelection.values()[currentSelection.ordinal() + 1];
                arrayOfPictures[handler-1][0].draw();
                arrayOfPictures[handler-1][1].delete();


                arrayOfPictures[handler][0].delete();
                arrayOfPictures[handler][1].draw();
                /* int handler= (currentSelection.ordinal()*2);

                currentSelection = CurrentSelection.values()[currentSelection.ordinal() + 1];

                System.out.println( "mycurrtenthandleris:" + handler);

                if (handler-1 >= 0){
                    arrayPictures[handler+1].draw();
                } else {
                    arrayPictures[handler].delete();
                    arrayPictures[handler+1].draw();
                } */

                System.out.println(currentSelection);

                break;
            case KeyboardEvent.KEY_UP:
               // System.out.println("HERE I'M PRESSING UP BEFORE" + currentSelection.ordinal() );

                int upHandler= currentSelection.ordinal() -1;

                System.out.println("HERE I'M PRESSING UP" + upHandler);
                if (upHandler <0) {
                    break;
                }
                System.out.println("up"+upHandler);
                currentSelection = CurrentSelection.values()[currentSelection.ordinal() - 1];
                //fix anterior images
                arrayOfPictures[upHandler+1][0].draw();
                arrayOfPictures[upHandler+1][1].delete();
                // draw new one on current choose
                arrayOfPictures[upHandler][0].delete();
                arrayOfPictures[upHandler][1].draw();
                /*int up = currentSelection.ordinal() - 1;
                if (up < 0) {
                    break;
                }
                currentSelection = CurrentSelection.values()[currentSelection.ordinal() - 1];

                System.out.println(currentSelection);
                System.out.println("EU CARRAGUEI PARA cima");*/
                System.out.println(currentSelection);

                break;
            case KeyboardEvent.KEY_SPACE:
                System.out.println("lets fly");
                break;
            default:
                break;
        }

    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}





