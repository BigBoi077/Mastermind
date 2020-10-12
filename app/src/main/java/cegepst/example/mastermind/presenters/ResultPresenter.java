package cegepst.example.mastermind.presenters;

import android.os.Bundle;
import android.os.Parcelable;

import java.lang.ref.WeakReference;
import cegepst.example.mastermind.contracts.ResultContract;
import cegepst.example.mastermind.views.ResultActivity;

public class ResultPresenter implements ResultContract.Presenter {

    private WeakReference<ResultContract.View> viewRef;

    public ResultPresenter(ResultActivity view, Bundle savedInstanceState) {
        viewRef = new WeakReference<>((ResultContract.View) view);
    }

    @Override
    public void onNewColorCombinationRow(ResultContract.ColorCombinationRow holder, int position) {

    }

    @Override
    public void addColorCombination(Parcelable game) {

    }

    @Override
    public void saveState(Bundle outState) {

    }
}
