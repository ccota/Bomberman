package bomberman.utilities;

import bomberman.SoundEffect;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Music;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;

public class Try {

    public static void main(String[] args) {
        SoundEffect.music();
        SoundEffect.enemySound();
        SoundEffect.bombSound();
        SoundEffect.enemySound();
        SoundEffect.itemSound();
        SoundEffect.gameOverSound();
    }

}
