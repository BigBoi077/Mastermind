package cegepst.example.mastermind.models;

import android.util.Log;

public class Random {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String[] generateRandomArray(MastermindGame mastermindGame) {
        String[] gameArray = mastermindGame.getColorsArray();
        int arrayLength = mastermindGame.getNbrColorCombination();
        String[] array = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = gameArray[getRandomNumber(0,6)];
        }
        for (int i = 0; i < arrayLength; i++) {
            Log.d("Array", array[i]);
        }
        return array;
    }
}
