package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.models.Game;

public class GameActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("game")) {
            game = getIntent().getParcelableExtra("game");
        }
        game.generateRandomColorArray();
        setContentView(R.layout.activity_game);
        placeSpinners();
    }

    private void placeSpinners() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        String[] strings={"1","2","3"};
        for (int i = 0; i < game.getNbrColorCombination(); i++) {
            Spinner spinner = new Spinner(this);
            spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, strings));
            linearLayout.addView(spinner);
        }
    }
}