package edu.augustanacsc490spring2020.augieathletics.ui.sports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.augustanacsc490spring2020.augieathletics.R;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private ArrayList<GameItems> parseResults;
    private Context context;

    public GameAdapter(ArrayList<GameItems> parseResults, Context context) {
        this.parseResults = parseResults;
        this.context = context;
    }

    @NonNull
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        GameItems parseItem = parseResults.get(position);
        holder.textRTeam1.setText(parseItem.getRtitle());
        holder.textRTeam2.setText(parseItem.getRtitle2());
        holder.textRscore1.setText(parseItem.getRscore1());
        holder.textRscore2.setText(parseItem.getRscore2());
        holder.textRDate.setText(parseItem.getRDate());
        holder.textRTime.setText(parseItem.getRTime());
        holder.textRlocation.setText(parseItem.getRlocation());
    }

    @Override
    public int getItemCount() {
        return parseResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView textRTeam1;
        TextView textRTeam2;
        TextView textRscore1;
        TextView textRscore2;
        TextView textRDate;
        TextView textRTime;
        TextView textRlocation;




        public ViewHolder(@NonNull View view) {
            super(view);
            //imageView = view.findViewById(R.id.imageViewFixtures);
            textRTeam1 = view.findViewById(R.id.textResultTeam1);
            textRTeam2 = view.findViewById(R.id.textResultTeam2);
            textRscore1= view.findViewById(R.id.textRScore1);
            textRscore2 = view.findViewById(R.id.textRScore2);
            textRDate = view.findViewById(R.id.textDateR);
            textRTime = view.findViewById(R.id.textTimeR);
            textRlocation = view.findViewById(R.id.textLocationR);


        }

    }
}