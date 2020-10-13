package cegepst.example.mastermind.contracts;

import android.os.Bundle;
import android.os.Parcelable;

import cegepst.example.mastermind.models.MastermindGame;

public interface ResultContract {
    interface  View {

    }

    interface Presenter {
        void onNewColorCombinationRow(ColorCombinationRow holder, int position);

        void saveState(Bundle outState);

        void addColorCombination(Parcelable game);
        void addCircles(Parcelable game);
    }

    interface ColorCombinationRow {
        void setPlayerCombination(String text);
    }
}
