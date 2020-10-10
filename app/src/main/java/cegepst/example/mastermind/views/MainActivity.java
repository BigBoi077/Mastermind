package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.models.Game;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();
    }

    public void sendDifficulty(View view) {
        RadioGroup radioGroup = findViewById(R.id.difficultyGroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int index = radioGroup.indexOfChild(radioButton);
        RadioButton chosenRadioButton = (RadioButton) radioGroup.getChildAt(index);
        String selectedDifficulty = chosenRadioButton.getText().toString();
        game.setDifficulty(selectedDifficulty);
    }

    public void startGame(View view) {
        if (game.getDifficulty().equals("")) {
            Toast.makeText(this, "Please select a difficulty before preceding", Toast.LENGTH_SHORT).show();
        } else {
            Intent gameIntent = new Intent(this , GameActivity.class);
            game.setCombinationsByDifficulty();
            gameIntent.putExtra("game", game);
            startActivity(gameIntent);
        }
    }
}