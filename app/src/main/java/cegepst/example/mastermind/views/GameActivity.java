package cegepst.example.mastermind.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import cegepst.example.mastermind.R;

public class GameActivity extends AppCompatActivity {

    private final String[] colorsArray = new String[]{"B", "W", "Y", "R", "BL", "G"};
    private static int REQUEST_CODE_ADD_COLOR = 1;
    private int nbrColorCombination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        nbrColorCombination = getIntent().getIntExtra("nbrColorCombination", 4);
        placeSpinners();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void placeSpinners() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        for (int i = 0; i < nbrColorCombination; i++) {
            Spinner spinner = new Spinner(this);
            spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, colorsArray));

           RelativeLayout.LayoutParams spinnerSize = new RelativeLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            spinner.setLayoutParams(spinnerSize);
            spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            spinner.setSelection(0);

            switch (i) {
                case 0:
                    spinner.setId(R.id.spinner1);
                    break;

                case 2:
                    spinner.setId(R.id.spinner2);
                    break;

                case 3:
                    spinner.setId(R.id.spinner3);
                    break;

                case 4:
                    spinner.setId(R.id.spinner4);
                    break;

                case 5:
                    spinner.setId(R.id.spinner5);
                    break;
            }
            if (spinner.getId() == -1) {
                spinner.setId(R.id.spinner);
            }
            linearLayout.addView(spinner);
        }
    }

    public void onSendResults(View view) {
        String[] playerColorArray = setColorCombination();

        Intent intent = new Intent();
        intent.putExtra("playerColorArray", playerColorArray);
        setResult(RESULT_OK, intent);
        finish();
    }

    private String getSpinnerText(int resId) {
        Spinner spinner = findViewById(resId);
        return spinner.getSelectedItem().toString();
    }

    public String[] setColorCombination() {
        String[] playerColorArray = new String[nbrColorCombination];

        playerColorArray[0] = getSpinnerText(R.id.spinner1);

        playerColorArray[1] = getSpinnerText(R.id.spinner);

        playerColorArray[2] =  getSpinnerText(R.id.spinner2);

        playerColorArray[3] =  getSpinnerText(R.id.spinner3);

        if (nbrColorCombination > 4) {
            playerColorArray[4] =  getSpinnerText(R.id.spinner4);
        }

        return playerColorArray;
    }
}