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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.augustanacsc490spring2020.augieathletics.R;
import edu.augustanacsc490spring2020.augieathletics.ui.sports.GenericSportFragment;

import static edu.augustanacsc490spring2020.augieathletics.MainActivity.user;

public class FavoriteTeamFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FavoriteTeamViewModel teamViewModel;
    private Spinner favoriteTeamSpinner;
    private TextView favoriteTeamTextView;

    private List<String> list = new ArrayList<>(Arrays.asList("Men's Basketball", "option2", "option3"));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        teamViewModel =
                ViewModelProviders.of(this).get(FavoriteTeamViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite_team, container, false);


        favoriteTeamSpinner = root.findViewById(R.id.favoriteListSpinner);
        favoriteTeamTextView = root.findViewById(R.id.favoriteListTextView);


//      Setting the ArrayAdapter data on the Spinner
        ArrayAdapter aa = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, user.getFavoriteSportsAsList());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        favoriteTeamSpinner.setAdapter(aa);
        return root;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        favoriteTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                favoriteTeamTextView.setText(user.getFavoriteSportsAsList().get(position));
                Log.d("boogaloo", user.getFavoriteSportsAsList().get(position));
                Toast.makeText(getContext(), "item selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity().getApplicationContext(), "item selected", Toast.LENGTH_SHORT).show();
                Log.d("bogaloo2", "nothing selected");
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getActivity().getApplicationContext(), "item selected", Toast.LENGTH_SHORT).show();
        Log.d("bogaloo2", "nothing selected");
    }
}
