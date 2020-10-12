package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.models.MastermindGame;

public class MainActivity extends AppCompatActivity {

    private MastermindGame mastermindGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mastermindGame = new MastermindGame();
    }

    public void sendDifficulty(View view) {
        RadioGroup radioGroup = findViewById(R.id.difficultyGroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int index = radioGroup.indexOfChild(radioButton);
        RadioButton chosenRadioButton = (RadioButton) radioGroup.getChildAt(index);
        String selectedDifficulty = chosenRadioButton.getText().toString();
        mastermindGame.setDifficulty(selectedDifficulty);
    }

    public void startGame(View view) {
        if (mastermindGame.getDifficulty().equals("")) {
            Toast.makeText(this, R.string.select_difficulty_error, Toast.LENGTH_SHORT).show();
        } else {
            Intent gameIntent = new Intent(this , GameActivity.class);
            mastermindGame.setCombinationsByDifficulty();
            gameIntent.putExtra("game", mastermindGame);
            startActivity(gameIntent);
        }
    }
}