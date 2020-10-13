package cegepst.example.mastermind.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cegepst.example.mastermind.R;
import cegepst.example.mastermind.contracts.ResultContract;
import cegepst.example.mastermind.models.MastermindGame;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    ResultContract.Presenter presenter;

    public ResultAdapter(ResultContract.Presenter presenter) {
        this.presenter = presenter;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements ResultContract.ColorCombinationRow {

        TextView playerColorCombination;
        FrameLayout circles;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            playerColorCombination = itemView.findViewById(R.id.playerColorCombination);
            circles = itemView.findViewById(R.id.correctAnswerCircle);
        }

        @Override
        public void setPlayerCombination(String text) {
            playerColorCombination.setText(text);
        }
    }

    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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
