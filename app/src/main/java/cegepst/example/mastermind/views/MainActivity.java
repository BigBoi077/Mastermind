package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import cegepst.example.mastermind.R;

public class MainActivity extends AppCompatActivity {

    private String chosenDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendDifficulty(View view) {
        RadioGroup radioGroup = findViewById(R.id.difficultyGroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int index = radioGroup.indexOfChild(radioButton);
        RadioButton chosenRadioButton = (RadioButton) radioGroup.getChildAt(index);
        chosenDifficulty = chosenRadioButton.getText().toString();
    }

    public void startGame(View view) {
        if (chosenDifficulty == null) {
            Toast.makeText(this, R.string.select_difficulty_error, Toast.LENGTH_SHORT).show();
        } else {
            Intent gameIntent = new Intent(this , ResultActivity.class);
            gameIntent.putExtra("difficulty", chosenDifficulty);
            startActivity(gameIntent);
        }
    }
}