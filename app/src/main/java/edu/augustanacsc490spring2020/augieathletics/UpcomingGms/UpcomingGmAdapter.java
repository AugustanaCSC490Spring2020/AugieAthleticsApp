package edu.augustanacsc490spring2020.augieathletics.UpcomingGms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.augustanacsc490spring2020.augieathletics.R;

public class UpcomingGmAdapter extends RecyclerView.Adapter<UpcomingGmAdapter.ViewHolder> {

    private ArrayList<UpcomingGmItems> parseItems;
    private Context context;

    public UpcomingGmAdapter(ArrayList<UpcomingGmItems> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingGmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_gm_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingGmAdapter.ViewHolder holder, int position) {
        UpcomingGmItems parseItem = parseItems.get(position);
        holder.texttitleTeam1.setText(parseItem.getTitle());
        holder.texttitleTeam2.setText(parseItem.getTitle2());
        holder.textDate.setText(parseItem.getDate());
        holder.textTime.setText(parseItem.getTime());
        holder.textLocation.setText(parseItem.getLocation());
        //Picasso.get().load(parseItem.getImageurl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView texttitleTeam1;
        TextView texttitleTeam2;
        TextView textDate;
        TextView textTime;
        TextView textLocation;

        public ViewHolder(@NonNull View view) {
            super(view);
            //imageView = view.findViewById(R.id.imageViewFixtures);
            texttitleTeam1 = view.findViewById(R.id.textTitleFixturesTeam1);
            texttitleTeam2 = view.findViewById(R.id.textTitleFixturesTeam2);
            textDate = view.findViewById(R.id.textDate);
            textTime = view.findViewById(R.id.textTime);
            textLocation =view.findViewById(R.id.textLocation);
        }

    }
}