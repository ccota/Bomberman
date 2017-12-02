package bomberman.Windows;

import bomberman.Game;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu extends Window implements KeyboardHandler{

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

    private Keyboard keyboard;
    private KeyboardEvent event;
    private KeyboardEvent event1;
    private KeyboardEvent event2;
    private int menuHeight;
    private int menuWidth;
    private int menuItemHeight;
    private CurrentSelection currentSelection;
    private Picture[][] arrayOfPictures;
    private Game game;



    public Menu(int menuHeight, int menuWidth, int menuItemHeight,Game game){
        currentSelection = CurrentSelection.STARTGAME;
        this.menuHeight=menuHeight;
        this.menuWidth=menuWidth;
        this.menuItemHeight=menuItemHeight;
        this.game=game;
    }

    @Override
    public void launch() {
      //  this.game=game;
        //menuBackground = new Rectangle(PADDING, PADDING, width, height);
        //menuBackground.draw();

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

        event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_UP);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_DOWN);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_SPACE);
        event2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(event);
        keyboard.addEventListener(event1);
        keyboard.addEventListener(event2);

    }

    public void delete(){
        for (int row = 0; row < arrayOfPictures.length; row++) {
            for (int column = 0; column < arrayOfPictures[row].length; column++) {
                arrayOfPictures[row][column].delete();
            }
        }
        keyboard.removeEventListener(event);
        keyboard.removeEventListener(event1);
        keyboard.removeEventListener(event2);
    }

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
                        try {
                            //game.setWindow(null);
                            delete();
                            game.init();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case OPTIONS:
                       // windowHowToPlay.launchOptions();
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




/*


public class WindowGameOver {

        private Rectangle menuBackground;
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
    public WindowHowToPlay windowHowToPlay = new WindowHowToPlay();
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
    private WindowMenu.CurrentSelection currentSelection;
    private Keyboard keyboard;
    private KeyboardEvent event;
    private KeyboardEvent event1;
    private KeyboardEvent event2;
    private Game game;
    public WindowMenu() {
        currentSelection = WindowMenu.CurrentSelection.STARTGAME;
        this.height = rows * cellSize;
        this.width = cols * cellSize;
        this.menuHeight = height / 2;
        this.menuWidth = width / 2;
    }

    public void launchMenu(Game game) {
        this.game=game;
        menuBackground = new Rectangle(PADDING, PADDING, width, height);
        menuBackground.draw();

        arrayOfPictures = new Picture[3][2];
        for (int row = 0; row < arrayOfPictures.length; row++)
        {

            for (int column = 0; column < arrayOfPictures[row].length; column++)
            {
                String imgsrc = null;
                if ((column % 2) == 0) {
                    imgsrc= WindowMenu.CurrentSelection.values()[row].getUnselected();


                } else {
                    imgsrc= WindowMenu.CurrentSelection.values()[row].getSelected();


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

        event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_UP);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_DOWN);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_SPACE);
        event2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(event);
        keyboard.addEventListener(event1);
        keyboard.addEventListener(event2);

    }



    public void delete(){
        menuBackground.delete();
        for (int row = 0; row < arrayOfPictures.length; row++) {
            for (int column = 0; column < arrayOfPictures[row].length; column++) {
                arrayOfPictures[row][column].delete();
            }
        }
        keyboard.removeEventListener(event);
        keyboard.removeEventListener(event1);
        keyboard.removeEventListener(event2);
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_DOWN:

                int downHandler= currentSelection.ordinal() + 1;
                if (downHandler >= WindowMenu.CurrentSelection.values().length) {
                    break;
                }
                System.out.println(downHandler);
                currentSelection = WindowMenu.CurrentSelection.values()[currentSelection.ordinal() + 1];
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

                currentSelection = WindowMenu.CurrentSelection.values()[currentSelection.ordinal() - 1];
                arrayOfPictures[upHandler+1][0].draw();
                arrayOfPictures[upHandler+1][1].delete();

                arrayOfPictures[upHandler][0].delete();
                arrayOfPictures[upHandler][1].draw();

                break;
            case KeyboardEvent.KEY_SPACE:
                switch (currentSelection){
                    case STARTGAME:
                        try {
                            delete();
                            game.init();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case OPTIONS:
                        windowHowToPlay.launchOptions();
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

 */
