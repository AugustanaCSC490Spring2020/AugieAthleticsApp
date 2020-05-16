package edu.augustanacsc490spring2020.augieathletics.ui.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.augustanacsc490spring2020.augieathletics.R;

public class fixturesAdapter extends RecyclerView.Adapter<fixturesAdapter.ViewHolder> {

    private ArrayList<fixturesItems> parseItems;
    private Context context;

    public fixturesAdapter(ArrayList<fixturesItems> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public fixturesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parseitems, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull fixturesAdapter.ViewHolder holder, int position) {
        fixturesItems parseItem = parseItems.get(position);
        holder.textView1.setText(parseItem.getTitle());
        holder.textView2.setText(parseItem.getTitle2());
        holder.textView3.setText(parseItem.getDate());
        holder.textView4.setText(parseItem.getTime());
        holder.textView5.setText(parseItem.getLocation());
        //Picasso.get().load(parseItem.getImageurl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;

        public ViewHolder(@NonNull View view) {
            super(view);
            //imageView = view.findViewById(R.id.imageViewFixtures);
            textView1 = view.findViewById(R.id.textTitleFixturesTeam1);
            textView2 = view.findViewById(R.id.textTitleFixturesTeam2);
            textView3 = view.findViewById(R.id.textDate);
            textView4 = view.findViewById(R.id.textTime);
            textView5 =view.findViewById(R.id.textLocation);
        }

    }
}