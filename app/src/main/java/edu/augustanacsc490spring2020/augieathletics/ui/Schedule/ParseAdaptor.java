package edu.augustanacsc490spring2020.augieathletics.ui.Schedule;

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

public class ParseAdaptor extends RecyclerView.Adapter<ParseAdaptor.ViewHolder> {
    private ArrayList<ParseItems> parseItems;
    private Context contextt;

    public ParseAdaptor(ArrayList<ParseItems> parseItems, Context contextt) {
        this.parseItems = parseItems;
        this.contextt = contextt;
    }

    @NonNull
    @Override
    public ParseAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parseitems,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdaptor.ViewHolder holder, int position) {
        ParseItems parseItem= parseItems.get(position);
        holder.txtV.setText(parseItem.getTitle());
        Picasso.get().load(parseItem.getImageurl()).into(holder.imgV);

    }

    @Override
    public int getItemCount()
    {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgV;
        TextView txtV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgV= itemView.findViewById(R.id.imageViewF);
            txtV= itemView.findViewById(R.id.textViewF);
        }
      }
    }

