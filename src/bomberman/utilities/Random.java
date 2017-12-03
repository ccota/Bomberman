package bomberman.utilities;

public class Random {


    public static int generate(int max){

        int randomNumber = (int) (Math.random()*max);
        return randomNumber;
    }


    public static int generate (int min, int max){

        int range = max-min;
        int randomNumber = (int) (Math.random()*range+min);
        return randomNumber;
    }
}

