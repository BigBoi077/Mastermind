package cegepst.example.mastermind.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.contracts.ResultContract;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    ResultContract.Presenter presenter;
    private Context context;

    public ResultAdapter(ResultContract.Presenter presenter) {
        this.presenter = presenter;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements ResultContract.ColorCombinationRow {

        TextView playerColorCombination;
        ImageView circle1;
        ImageView circle2;
        ImageView circle3;
        ImageView circle4;
        ImageView circle5;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            playerColorCombination = itemView.findViewById(R.id.playerColorCombination);
            circle1 = itemView.findViewById(R.id.circle1);
            circle2 = itemView.findViewById(R.id.circle2);
            circle3 = itemView.findViewById(R.id.circle3);
            circle4 = itemView.findViewById(R.id.circle4);
            circle5 = itemView.findViewById(R.id.circle5);
        }

        @Override
        public void setPlayerCombination(String text) {
            playerColorCombination.setText(text);
        }

        private int checkIfCorrect(String[] playerColorArray, String[] randomColorArray, int index) {
            if (playerColorArray[index].equals(randomColorArray[index])) {
                return R.drawable.ic_full_circle;
            }
            for (int i = 0; i < randomColorArray.length; i++) {
                if (playerColorArray[index].equals(randomColorArray[i])) {
                    return R.drawable.ic_empty_cirlce;
                }
            }
            return 0;
        }


        @Override
        public void addCircles(String[] playerColorArray, String[] randomColorArray) {
            for (int i = 0; i < randomColorArray.length; i++) {
                switch (i) {
                    case 0:
                        circle1.setImageResource(checkIfCorrect(playerColorArray, randomColorArray, i));
                        break;

                    case 1:
                        circle2.setImageResource(checkIfCorrect(playerColorArray, randomColorArray, i));
                        break;

                    case 2:
                        circle3.setImageResource(checkIfCorrect(playerColorArray, randomColorArray, i));
                        break;

                    case 3:
                        circle4.setImageResource(checkIfCorrect(playerColorArray, randomColorArray, i));
                        break;

                    case 4:
                        circle5.setImageResource(checkIfCorrect(playerColorArray, randomColorArray, i));
                        break;
                }
            }
        }
    }

    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View singleContactView = inflater.inflate(R.layout.color_combination_single_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(singleContactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        presenter.onNewColorCombinationRow(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getNbrPlayerAttempts();
    }
}
