package cegepst.example.mastermind.presenters;

import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cegepst.example.mastermind.contracts.ResultContract;
import cegepst.example.mastermind.models.MastermindGame;
import cegepst.example.mastermind.views.ResultActivity;

public class ResultPresenter implements ResultContract.Presenter {

    private WeakReference<ResultContract.View> viewRef;
    private MastermindGame mastermindGame;
    private String combination;

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

    public int getNbrAttempts() {
        return mastermindGame.getNbrAttempts();
    }

    public int getNbrColorCombination() {
        return mastermindGame.getNbrColorCombination();
    }

    public int getNbrPlayerAttempts() {
        return mastermindGame.getNbrAttempts();
    }

    @Override
    public void onNewColorCombinationRow(ResultContract.ColorCombinationRow holder, int position) {
        holder.setPlayerCombination(combination);
        holder.addCircles(mastermindGame.getPlayerColorArray(), mastermindGame.getRandomColorArray());
    }


    @Override
    public void saveState(Bundle outState) {
        outState.putParcelable("mastermindGame", mastermindGame);
    }

    @Override
    public void addColorCombination(String[] playerColorCombination) {
        ResultContract.View view = viewRef.get();

        mastermindGame.setPlayerColorCombination(makeString(playerColorCombination));
        mastermindGame.setPlayerColorArray(playerColorCombination);

        view.notifyAttemptAdd(mastermindGame.getNbrAttempts());

        if (isWinner(playerColorCombination)) {
            if (view == null) {
                return;
            }
            viewRef.get().onWinner();
        }

        mastermindGame.setNbrAttempts(mastermindGame.getNbrAttempts() + 1);

        if (mastermindGame.isMaxedAttempts()) {
            if (view == null) {
                return;
            }
            viewRef.get().onGameOver();
        }
    }

    @Override
    public MastermindGame getMastermindGame() {
        return mastermindGame;
    }

    private String makeString(String[] playerColorCombination) {
        String playerEntry = "";
        combination = "";
        for (int i = 0; i < playerColorCombination.length; i++) {
            combination += " " + playerColorCombination[i];
            playerEntry += playerColorCombination[i];
        }
        Log.d("Your entry : ", playerEntry);
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
