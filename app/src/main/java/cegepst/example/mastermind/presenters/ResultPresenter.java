package cegepst.example.mastermind.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.contracts.ResultContract;
import cegepst.example.mastermind.models.MastermindGame;
import cegepst.example.mastermind.views.GameOverActivity;
import cegepst.example.mastermind.views.ResultActivity;
import cegepst.example.mastermind.views.VictoryActivity;

public class ResultPresenter implements ResultContract.Presenter {

    private WeakReference<ResultContract.View> viewRef;
    private MastermindGame mastermindGame;

    public ResultPresenter(ResultActivity view, Bundle savedInstanceState, String chosenDifficulty) {
        viewRef = new WeakReference<>((ResultContract.View) view);

        if (savedInstanceState != null && savedInstanceState.containsKey("game")) {
            mastermindGame = savedInstanceState.getParcelable("game");
        } else {
            mastermindGame = new MastermindGame();
            mastermindGame.setDifficulty(chosenDifficulty);
            mastermindGame.setCombinationsByDifficulty();
            mastermindGame.generateRandomColorArray();
        }
    }

    public int getNbrColorCombination() {
        return mastermindGame.getNbrColorCombination();
    }

    public int getNbrPlayerAttempts() {
        return mastermindGame.getNbrAttempts();
    }

    @Override
    public void onNewColorCombinationRow(ResultContract.ColorCombinationRow holder, int position) {
        holder.setPlayerCombination("JOSHUA");
    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void addColorCombination(String[] playerColorCombination) {
        mastermindGame.setPlayerColorCombination(makeString(playerColorCombination));

        if (isWinner(playerColorCombination)) {
            if (viewRef.get() == null) {
                return;
            }
            viewRef.get().onWinner();
        }

        mastermindGame.setNbrAttempts(mastermindGame.getNbrAttempts() + 1);

        if (mastermindGame.isMaxedAttempts()) {
            if (viewRef.get() == null) {
                return;
            }
            viewRef.get().onGameOver();
        }
    }

    private String makeString(String[] playerColorCombination) {
        String playerEntry = "";
        for (int i = 0; i < playerColorCombination.length; i++) {
            playerEntry += playerColorCombination[i];
        }
        Log.d("HEYYYYY", playerEntry);
        return playerEntry;
    }

    private boolean isWinner(String[] playerColorArray) {
        String[] correctAnswerArray = mastermindGame.getRandomColorArray();
        int arrayLength = mastermindGame.getRandomColorArray().length;
        int wantedCorrectAnswers = mastermindGame.getNbrColorCombination();
        int nbrCorrectAnswers = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (playerColorArray[i].equals(correctAnswerArray[i])) {
                nbrCorrectAnswers++;
            }
            if (nbrCorrectAnswers == wantedCorrectAnswers) {
                return true;
            }
        }
        return false;
    }
}
