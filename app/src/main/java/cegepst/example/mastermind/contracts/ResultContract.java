package cegepst.example.mastermind.contracts;

import android.os.Bundle;

public interface ResultContract {
    interface  View {
        void notifyAttemptAdd(int position);
        void onWinner();
        void onGameOver();
    }

    interface Presenter {
        void onNewColorCombinationRow(ColorCombinationRow holder, int position);

        void saveState(Bundle outState);

        void addColorCombination(String[] playerColorCombination);

        int getNbrColorCombination();
        int getNbrPlayerAttempts();
    }

    interface ColorCombinationRow {
        void setPlayerCombination(String text);
        void addCircles(String[] playerColorArray, String[] randomColorArray);
    }
}
