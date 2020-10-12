package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cegepst.example.mastermind.R;

public class VictoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);
    }

    public void startOver(View view) {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
}