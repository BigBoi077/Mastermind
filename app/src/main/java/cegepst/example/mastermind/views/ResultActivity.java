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
import cegepst.example.mastermind.presenters.ResultPresenter;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {

    public static int REQUEST_CODE_ADD = 1;
    private int[] colorIndexes;
    private ResultContract.Presenter presenter;
    private ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String chosenDifficulty = getIntent().getStringExtra("difficulty");

        if (chosenDifficulty.equals("Difficult")) {
            colorIndexes = new int[]{0, 0, 0, 0, 0};
        } else {
            colorIndexes = new int[]{0, 0, 0, 0};
        }

        presenter = new ResultPresenter(this, savedInstanceState, chosenDifficulty);

        resultAdapter = new ResultAdapter(presenter);

        RecyclerView colorAttempts = findViewById(R.id.colorCombinationList);
        colorAttempts.setAdapter(resultAdapter);
        colorAttempts.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onStartAttempt(View view) {
        Intent gameActivity = new Intent(this, GameActivity.class);
        gameActivity.putExtra("nbrColorCombination", presenter.getNbrColorCombination());
        gameActivity.putExtra("colorIndexes", colorIndexes);
        startActivityForResult(gameActivity, REQUEST_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        colorIndexes = data.getIntArrayExtra("playerColorChoice");
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
            if (!data.hasExtra("playerColorArray")) {
                Toast.makeText(this, R.string.error_unexpected, Toast.LENGTH_SHORT).show();
                return;
            }
            presenter.addColorCombination(data.getStringArrayExtra("playerColorArray"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("mastermindGame", presenter.getMastermindGame());
        presenter.saveState(outState);
    }

    @Override
    public void notifyAttemptAdd(int position) {
        resultAdapter.notifyItemInserted(position);
    }

    @Override
    public void onWinner() {
        Intent intent = new Intent(this, VictoryActivity.class);
        intent.putExtra("nbrAttempts", presenter.getNbrAttempts());
        startActivity(intent);
    }

    @Override
    public void onGameOver() {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("nbrAttempts", presenter.getNbrAttempts());
        startActivity(intent);
    }
}