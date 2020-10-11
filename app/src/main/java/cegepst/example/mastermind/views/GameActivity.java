package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.models.MastermindGame;

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
            spinner.setId(i);
            linearLayout.addView(spinner);
        }
    }

    public void sendResults(View view) {
        mastermindGame
    }
}