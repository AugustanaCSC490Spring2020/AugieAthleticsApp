package edu.augustanacsc490spring2020.augieathletics.ui.teams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import edu.augustanacsc490spring2020.augieathletics.R;
import edu.augustanacsc490spring2020.augieathletics.data.user.User;

public class FavoriteTeamFragment extends Fragment {

    private FavoriteTeamViewModel teamViewModel;
    private Spinner favoriteTeamSpinner;
    private TextView favoriteTeamTextView;
    private Activity rosterActivity;
    private EditText editText;
    private Button button;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        teamViewModel =
                ViewModelProviders.of(this).get(FavoriteTeamViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite_team, container, false);

        favoriteTeamSpinner = root.findViewById(R.id.favoriteListSpinner);
        favoriteTeamTextView = root.findViewById(R.id.favoriteListTextView);
        editText = root.findViewById(R.id.editText);
        button = root.findViewById(R.id.addToDatabaseTest);
        addBtnListener(root, button);
        return root;
    }

    private void addBtnListener(final View root, Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               user.addFavoriteSport(editText.getText().toString());
            }
        });
    }


    public void addToDatabase(View view) {
        user.getDatabaseReference().child(user.getFirebaseUser().getEmail().toString().replace('.',','))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //ArrayAdapter aa =new ArrayAdapter(this, android.R.layout.simple_spinner_item, user.getFavoriteSportsList());
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG2: ELECTRIC BOOGALOO", "Failed to read value.", error.toException());
                    }
                });
    }
}
