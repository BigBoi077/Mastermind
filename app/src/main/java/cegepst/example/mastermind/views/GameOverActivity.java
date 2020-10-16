package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cegepst.example.mastermind.R;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int nbrAttempts = getIntent().getIntExtra("nbrAttempts", 0);
        setContentView(R.layout.activity_game_over);
        TextView view = findViewById(R.id.nbrTurnsTaken);
        view.setText("You took " + nbrAttempts + " turns...");
    }

    public void startOver(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
}