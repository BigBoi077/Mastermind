package cegepst.example.mastermind.contracts;

import android.os.Bundle;
import android.os.Parcelable;

import cegepst.example.mastermind.models.MastermindGame;

public interface ResultContract {
    interface  View {
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
    }
}
