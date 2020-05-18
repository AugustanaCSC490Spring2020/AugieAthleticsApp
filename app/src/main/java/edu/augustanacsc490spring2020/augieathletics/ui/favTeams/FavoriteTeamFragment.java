package edu.augustanacsc490spring2020.augieathletics.ui.favTeams;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import edu.augustanacsc490spring2020.augieathletics.R;

import static edu.augustanacsc490spring2020.augieathletics.MainActivity.user;

public class FavoriteTeamFragment extends Fragment {

    private Spinner favoriteTeamSpinner;
    private TextView favoriteTeamTextView;
    private TextView tempTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite_team, container, false);

        tempTextView = root.findViewById(R.id.tempTextView);
        favoriteTeamSpinner = root.findViewById(R.id.favoriteListSpinner);
        favoriteTeamTextView = root.findViewById(R.id.favoriteListTextView);


//      Sets up the ArrayAdapter for the Spinner to display the favorite teams
        ArrayAdapter aa = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, user.getFavoriteSportsAsList());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        favoriteTeamSpinner.setAdapter(aa);
        addSpinnerListener();
        return root;
    }

    /**
     * adds a listener to the Spinner to recognize when the item is being changed.
     */
    public void addSpinnerListener() {
        favoriteTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = "Thanks for adding " + user.getFavoriteSportsAsList().get(position)
                        + " to your favorites list.";
                tempTextView.setText(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("bogaloo2", "nothing selected");
            }
        });
    }
}
