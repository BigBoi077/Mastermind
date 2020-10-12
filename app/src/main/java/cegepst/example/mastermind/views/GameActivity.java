package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.models.MastermindGame;
import cegepst.example.mastermind.models.Random;

public class GameActivity extends AppCompatActivity {

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

    private void placeSpinners() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        String[] colors = mastermindGame.getColorsArray();
        for (int i = 0; i < mastermindGame.getNbrColorCombination(); i++) {
            Spinner spinner = new Spinner(this);
            spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, colors));

            RelativeLayout.LayoutParams spinnerSize = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            spinner.setLayoutParams(spinnerSize);
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
        isWinner(playerColorArray);
    }

    private void isWinner(String[] playerColorArray) {
    }

    public String[] setColorCombination() {
        String playerColorCombination = "";
        String[] playerColorArray = new String[mastermindGame.getNbrColorCombination()];

        playerColorCombination += getSpinnerText(R.id.spinner1);
        playerColorArray[0] = getSpinnerText(R.id.spinner1);

        playerColorCombination += getSpinnerText(R.id.spinner);
        playerColorArray[1] = getSpinnerText(R.id.spinner);

        playerColorCombination += getSpinnerText(R.id.spinner2);
        playerColorArray[2] =  getSpinnerText(R.id.spinner2);

        playerColorCombination += getSpinnerText(R.id.spinner3);
        playerColorArray[3] =  getSpinnerText(R.id.spinner3);

        if (mastermindGame.getDifficulty().equals("Difficult")) {
            playerColorCombination += getSpinnerText(R.id.spinner4);
            playerColorArray[4] =  getSpinnerText(R.id.spinner4);
        }

        Log.d("salut jeremy", "Allo");

        mastermindGame.setPlayerColorCombination(playerColorCombination);
        return playerColorArray;
    }

    private String getSpinnerText(int resId) {
        Spinner spinner = (Spinner)findViewById(resId);
        String text = "";
        try {
            text = spinner.getSelectedItem().toString();
            return text;
        } catch (Exception e) {
            Toast.makeText(this, "Please select a color before submiting", Toast.LENGTH_SHORT).show();
        }
        return text;
    }
}