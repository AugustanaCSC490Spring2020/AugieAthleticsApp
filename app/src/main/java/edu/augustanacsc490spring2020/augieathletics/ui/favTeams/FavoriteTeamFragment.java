package edu.augustanacsc490spring2020.augieathletics.ui.favTeams;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.augustanacsc490spring2020.augieathletics.R;
import edu.augustanacsc490spring2020.augieathletics.data.user.User;

public class FavoriteTeamFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FavoriteTeamViewModel teamViewModel;
    private Spinner favoriteTeamSpinner;
    private TextView favoriteTeamTextView;
    private User user;

    private List<String> list = new ArrayList<>(Arrays.asList("Men's Basketball", "option2", "option3"));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        teamViewModel =
                ViewModelProviders.of(this).get(FavoriteTeamViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite_team, container, false);

        favoriteTeamSpinner = root.findViewById(R.id.favoriteListSpinner);
        favoriteTeamTextView = root.findViewById(R.id.favoriteListTextView);
        user = new User(FirebaseAuth.getInstance().getCurrentUser());

//        for (String sport : list) {
//            user.addFavoriteSport(sport);
//        }

//      Setting the ArrayAdapter data on the Spinner
        ArrayAdapter aa = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, user.getFavoriteSportsList());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Log.d("bogaloo2", "got here");
        favoriteTeamSpinner.setAdapter(aa);

        return root;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        favoriteTeamTextView.setText(user.getFavoriteSportsList().get(position));
        Log.d("boogaloo", user.getFavoriteSportsList().get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("bogaloo2", "nothing selected");
    }
}
