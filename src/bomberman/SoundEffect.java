package bomberman;

import bomberman.Gameobjects.movableobjects.Player;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Music;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;

public class SoundEffect {


    public static void music() {
        TinySound.init();
        Music music = TinySound.loadMusic("GTmusic.wav");
        music.setVolume(1);
        music.play(true);


    }


    public static void bombSound() {
        TinySound.init();
        Sound bombSound = TinySound.loadSound("bomb.wav");
        for (int i = 0; i < 1; i++) {
            bombSound.play(3);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }

    public static void enemySound() {
        TinySound.init();
        Sound enemyDead = TinySound.loadSound("enemyDead.wav");
        for (int i = 0; i < 1; i++) {
            enemyDead.play(20);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void itemSound() {
        TinySound.init();
        Sound grabItem = TinySound.loadSound("item.wav");
        for (int i = 0; i < 1; i++) {
            grabItem.play(20);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void gameOverSound() {
        Sound gameOver = TinySound.loadSound("PlayerDead.wav");
        for (int i = 0; i < 1; i++) {
            gameOver.play(4);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }

}

