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
    public Options options = new Options();
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
    public Menu() {
        currentSelection = CurrentSelection.STARTGAME;
        this.height = rows * cellSize;
        this.width = cols * cellSize;
        this.menuHeight = height / 2;
        this.menuWidth = width / 2;
    }

    public void launchMenu() {
        menuBackground = new Rectangle(PADDING, PADDING, width, height);
        menuBackground.draw();

        arrayOfPictures = new Picture[3][2];
        for (int row = 0; row < arrayOfPictures.length; row++)
        {

            for (int column = 0; column < arrayOfPictures[row].length; column++)
            {
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



   // public


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_DOWN:

                int downHandler= currentSelection.ordinal() + 1;
                if (downHandler >= CurrentSelection.values().length) {
                    break;
                }
                System.out.println(downHandler);
                currentSelection = CurrentSelection.values()[currentSelection.ordinal() + 1];
                arrayOfPictures[downHandler-1][0].draw();
                arrayOfPictures[downHandler-1][1].delete();

                arrayOfPictures[downHandler][0].delete();
                arrayOfPictures[downHandler][1].draw();


                break;
            case KeyboardEvent.KEY_UP:

                int upHandler= currentSelection.ordinal() -1;
                if (upHandler <0) {
                    break;
                }

                currentSelection = CurrentSelection.values()[currentSelection.ordinal() - 1];
                arrayOfPictures[upHandler+1][0].draw();
                arrayOfPictures[upHandler+1][1].delete();

                arrayOfPictures[upHandler][0].delete();
                arrayOfPictures[upHandler][1].draw();

                break;
            case KeyboardEvent.KEY_SPACE:
                 switch (currentSelection){
                     case STARTGAME:

                         break;
                     case OPTIONS:
                         options.launchOptions();
                         break;
                     case EXIT:
                         System.exit(1);
                         break;
                 }

                break;
            default:
                break;
        }

    }
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}





