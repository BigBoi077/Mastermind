package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.contracts.ResultContract;
import cegepst.example.mastermind.models.MastermindGame;
import cegepst.example.mastermind.models.Random;

public class GameActivity extends AppCompatActivity {

    private static int REQUEST_CODE_ADD_COLOR = 1;
    private MastermindGame mastermindGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("game")) {
            mastermindGame = getIntent().getParcelableExtra("game");
        }
        mastermindGame.generateRandomColorArray();
        setContentView(R.layout.activity_game);
        placeSpinners();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void placeSpinners() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        String[] colors = mastermindGame.getColorsArray();
        for (int i = 0; i < mastermindGame.getNbrColorCombination(); i++) {
            Spinner spinner = new Spinner(this);
            spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, colors));

           RelativeLayout.LayoutParams spinnerSize = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            spinner.setLayoutParams(spinnerSize);
            spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            spinner.setSelection(Random.getRandomNumber(0, 6));
            switch (i) {
                case 0:
                    spinner.setId(R.id.spinner1);
                    break;

                case 2:
                    spinner.setId(R.id.spinner2);
                    break;

                case 3:
                    spinner.setId(R.id.spinner3);
                    break;

                case 4:
                    spinner.setId(R.id.spinner4);
                    break;

                case 5:
                    spinner.setId(R.id.spinner5);
                    break;
            }

            if (spinner.getId() == -1) {
                spinner.setId(R.id.spinner);
            }
            linearLayout.addView(spinner);
        }
    }

    public void sendResults(View view) {
        String[] playerColorArray = setColorCombination();

        if (isWinner(playerColorArray)) {
            Intent intent = new Intent(this, VictoryActivity.class);
            intent.putExtra("game", mastermindGame);
            startActivity(intent);
        }

        mastermindGame.setNbrAttempts(mastermindGame.getNbrAttempts() + 1);

        if (mastermindGame.isMaxedAttempts()) {
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("game", mastermindGame);
            startActivity(intent);
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("game", mastermindGame);
        startActivityForResult(intent, REQUEST_CODE_ADD_COLOR);
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

    private String getSpinnerText(int resId) {
        Spinner spinner = findViewById(resId);
        return spinner.getSelectedItem().toString();
    }

    public String[] setColorCombination() {
        String playerColorCombination = "";
        String[] playerColorArray = new String[mastermindGame.getNbrColorCombination()];

        playerColorCombination += " " + getSpinnerText(R.id.spinner1);
        playerColorArray[0] = getSpinnerText(R.id.spinner1);

        playerColorCombination += " " + getSpinnerText(R.id.spinner);
        playerColorArray[1] = getSpinnerText(R.id.spinner);

        playerColorCombination += " " + getSpinnerText(R.id.spinner2);
        playerColorArray[2] =  getSpinnerText(R.id.spinner2);

        playerColorCombination += " " + getSpinnerText(R.id.spinner3);
        playerColorArray[3] =  getSpinnerText(R.id.spinner3);

        if (mastermindGame.getDifficulty().equals("Difficult")) {
            playerColorCombination += " " + getSpinnerText(R.id.spinner4);
            playerColorArray[4] =  getSpinnerText(R.id.spinner4);
        }

        mastermindGame.setPlayerColorCombination(playerColorCombination);
        return playerColorArray;
    }
}