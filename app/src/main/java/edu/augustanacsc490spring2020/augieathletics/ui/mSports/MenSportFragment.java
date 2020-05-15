package edu.augustanacsc490spring2020.augieathletics.ui.mSports;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.augustanacsc490spring2020.augieathletics.R;

public class MenSportFragment extends Fragment {

    private MenSportViewModel menSportViewModel;
    private Button rosterButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_msport, container, false);
        rosterButton = root.findViewById(R.id.rosterBtn);
        goToRoster(root);

        return root;
    }

    private void goToRoster(final View view) {
        rosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RosterActivity.class);
                startActivity(intent);
            }
        });
    }


}
