package cegepst.example.mastermind.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.contracts.ResultContract;
import cegepst.example.mastermind.models.MastermindGame;
import cegepst.example.mastermind.presenters.ResultPresenter;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {

    public static int REQUEST_CODE_ADD = 1;

    private ResultContract.Presenter presenter;
    private ResultAdapter resultAdapter;

    private MastermindGame mastermindGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (getIntent().hasExtra("game")) {
            mastermindGame = getIntent().getParcelableExtra("game");
        }

        presenter = new ResultPresenter(this, savedInstanceState);

        resultAdapter = new ResultAdapter(presenter, mastermindGame);

        RecyclerView colorAttempts = findViewById(R.id.colorCombinationList);
        colorAttempts.setAdapter(resultAdapter);
        colorAttempts.setLayoutManager(new LinearLayoutManager(this));
    }

    public void returnToGame(View view) {
        Intent gameActivity = new Intent(this, GameActivity.class);
        gameActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(gameActivity, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            if (!data.hasExtra("game")) {
                Toast.makeText(this, R.string.error_unexpected, Toast.LENGTH_SHORT).show();
                return;
            }

            presenter.addColorCombination(data.getParcelableExtra("game"));
            presenter.addCircles(data.getParcelableExtra("game"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveState(outState);
    }
}