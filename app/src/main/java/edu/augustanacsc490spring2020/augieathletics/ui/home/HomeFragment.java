package edu.augustanacsc490spring2020.augieathletics.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import edu.augustanacsc490spring2020.augieathletics.R;
import edu.augustanacsc490spring2020.augieathletics.Results.Results;
import edu.augustanacsc490spring2020.augieathletics.UpcomingGms.UpcomingGms;

public class HomeFragment extends Fragment {

    private Button eventsBtn;
    private Button resultsBtn;
    private Button webpageBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        eventsBtn =root.findViewById(R.id.btnUpComingEvents);
        resultsBtn = root.findViewById(R.id.btnResults);

        addOnClickListeners(UpcomingGms.class, eventsBtn);
        addOnClickListeners(Results.class, resultsBtn);

        webpageBtn = root.findViewById(R.id.websiteLinkBtn);
        webpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://athletics.augustana.edu/"));
                startActivity(intent);
            }
        });
        return root;
    }

    /**
     * Adds on click listeners to buttons
     * @param activityClass - the activity that the button is supposed to send the view to
     * @param btn - the button being clicked
     */
    public void addOnClickListeners(final Class activityClass, Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), activityClass);
                startActivity(intent);
            }
        });
    }
}
