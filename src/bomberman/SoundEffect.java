package bomberman;

import org.academiadecodigo.bootcamp.kuusisto.tinysound.Music;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;

public class SoundEffect {


    public static void menuMusic(){
        TinySound.init();
        Music music = TinySound.loadMusic("menuMusic.wav");
        music.setVolume(2);
        music.play(true);
    }

    public static void stageOneMusic() {

        TinySound.init();
        Music music = TinySound.loadMusic("FirstStageMusic.wav");
        music.setVolume(1);
        music.play(true);
    }

    public static void stageTwoMusic(){

        TinySound.init();
        Music music = TinySound.loadMusic("SecondStageMusic.wav");
        music.setVolume(2);
        music.play(true);
    }

    public static void stageThreeMusic(){

        TinySound.init();
        Music music = TinySound.loadMusic("ThirdStageMusic.wav");
        music.setVolume(2);
        music.play(true);
    }

    public static void stopMusic(){
        TinySound.shutdown();
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

    public static void gameOverSound() {

        TinySound.init();
        Sound gameOver = TinySound.loadSound("PlayerDead.wav");
        for (int i = 0; i < 1; i++) {
            gameOver.play(4);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }

    public static void creditsSound(){

        TinySound.init();
        Music music = TinySound.loadMusic("credits.wav");
        music.setVolume(1);
        music.play(true);
    }
}

