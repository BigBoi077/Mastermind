package cegepst.example.mastermind.contracts;

import android.os.Bundle;
import android.os.Parcelable;

public interface ResultContract {
    interface  View {

    }

    interface Presenter {
        void onNewColorCombinationRow(ColorCombinationRow holder, int position);

        void addColorCombination(Parcelable game);
        void addCircles(Parcelable game);

        void saveState(Bundle outState);
    }

    interface ColorCombinationRow {
        void setPlayerCombination(String text);
    }
}
