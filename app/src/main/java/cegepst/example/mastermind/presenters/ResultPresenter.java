package cegepst.example.mastermind.presenters;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.contracts.ResultContract;
import cegepst.example.mastermind.models.MastermindGame;
import cegepst.example.mastermind.views.ResultActivity;

public class ResultPresenter implements ResultContract.Presenter {

    private WeakReference<ResultContract.View> viewRef;
    private MastermindGame mastermindGame;

    public ResultPresenter(ResultActivity view, Bundle savedInstanceState) {
        viewRef = new WeakReference<>((ResultContract.View) view);

        if (savedInstanceState != null && savedInstanceState.containsKey("game")) {
            mastermindGame = savedInstanceState.getParcelable("game");
        }
    }

    @Override
    public void onNewColorCombinationRow(ResultContract.ColorCombinationRow holder, int position) {
        holder.setPlayerCombination(mastermindGame.getPlayerColorCombination());
    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void addColorCombination(Parcelable game) {
        ResultContract.View view = viewRef.get();
    }

    @Override
    public void addCircles(Parcelable game) {

    }
}
