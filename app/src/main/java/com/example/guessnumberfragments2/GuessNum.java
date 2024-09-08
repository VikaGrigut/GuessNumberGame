package com.example.guessnumberfragments2;
import java.util.Random;

public class GuessNum {
    static public int rndCompNum(){
        int min = 10;
        int max = 99;
        Random random = new Random();
        //return random.nextInt(max - min + 1) + min;
        return 12;
    }

    static public int rndCompNum3(){
        int min = 100;
        int max = 999;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    static public int rndCompNum4(){
        int min = 1000;
        int max = 9999;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
