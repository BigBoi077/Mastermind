package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cegepst.example.mastermind.R;

public class VictoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
        int nbrAttempts = getIntent().getIntExtra("nbrAttempts", 0);
        TextView view = findViewById(R.id.nbrTurnsTaken);
        if (nbrAttempts == 0) {
            view.setText("You got it on your first attempt! Good job!");
        } else {
            view.setText("You took " + nbrAttempts + " turns!");
        }

    }

    public void startOver(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
}