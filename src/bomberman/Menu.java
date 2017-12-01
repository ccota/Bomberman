package bomberman;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
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
    private Picture[] arrayPictures;
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


        arrayPictures = new Picture[6];
        int enumCounterby2Loops=0;
        for (int i = 0; i < arrayPictures.length; i++) {
            String imgsrc = null;
            if ((i % 2) == 0) {
                imgsrc= CurrentSelection.values()[enumCounterby2Loops].getUnselected();
                //System.out.println("UNSELECT: "+CurrentSelection.values()[enumCounterby2Loops]);

            } else {
                imgsrc= CurrentSelection.values()[enumCounterby2Loops].getSelected();
                //System.out.println("SELECT: " +CurrentSelection.values()[enumCounterby2Loops]);
                enumCounterby2Loops++;

            }
            arrayPictures[i] = new Picture((menuWidth - menuWidth / 2), (menuHeight - menuHeight / 2) + (menuItemHeight * i),imgsrc);
            if ((i % 2) == 0) {
                arrayPictures[i].draw();
            }

        }

            //arrayPictures[i] = new Picture((menuWidth - menuWidth / 2),
            //(menuHeight - menuHeight / 2) + (menuItemHeight * i),((i%2)==0)? CurrentSelection.values() : currentSelection.getSelected());
            // arrayPictures[i].draw();
            //}
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
                int handler= (currentSelection.ordinal()*2);
                currentSelection = CurrentSelection.values()[currentSelection.ordinal() + 1];
                // arrayPictures[currentSelection.ordinal()].setColor(Color.RED);
                System.out.println( "mycurrtenthandleris:" + handler);

                if (handler-1 >= 0){
                    arrayPictures[handler+1].draw();
                } else {
                    arrayPictures[handler].delete();
                    arrayPictures[handler+1].draw();
                }



                break;
            case KeyboardEvent.KEY_UP:
                int up = currentSelection.ordinal() - 1;
                if (up < 0) {
                    break;
                }
                currentSelection = CurrentSelection.values()[currentSelection.ordinal() - 1];
                // arrayPictures[currentSelection.ordinal()].setColor(Color.RED);
                System.out.println(currentSelection);
                System.out.println("EU CARRAGUEI PARA cima");
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





